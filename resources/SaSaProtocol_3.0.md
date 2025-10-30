# The Frog v3.0

The Frog, a space explorer vehicle from the SAxion Space Agency (SASA), is about
to wrap up a mission and was heading back to the base on the recently discovered
asteroid S42, hoping to find precious minerals, as disaster strikes; an avalanche
caused rocks to block the entry to the base and also knocked out the navigation
software of the Frog (called the Pilot).

In order to retrieve the very expensive Frog, it needs to get back to the base
where it will be beamed up with a tractor beam. The only solution appears to be
to blow up the obstruction to clear the entry. And luckily there is still TNT and
a Detonator present on the asteroid, but because of the avalanche, we don't know
where it is. The Frog has to find and collect it and drop it near the obstruction.

Communication between the base, the Frog and Mission Control, back on earth, is
via a satellite orbiting the asteroid, that distributes all messages. Depending
on the transmission distance, it may take a longer time for the message to arrive.

Mission Control has a detailed map of the asteroids surface and can guide the Frog,
although not navigate it directly, because of the aforementioned huge lag in the
transmission.

The astronaut driver of the Frog suffered some injuries during the avalanche and
is not able to drive the Frog for extended times. Therefore it is the task of the
software team to write software, called The Pilot, for the navigation computer,
so that the Frog can drive mostly autonomous to given locations on the asteroid.
The Frog can provide information on the actual location, heading and energy
levels through messages send to the satellite (and the Pilot). This communication
interface can also be used to drive the Frog and use the Frog's radar to scan the
area for obstructions. Because this navigation computer is nearby The Frog and the
satellite, it suffers no lag in the communication.

When the Frog is near the TNT or the Detonator, these are automatically picked up,
which is reported back by the software interface.
When the Frog is near to the obstruction at the base and if it carries both the TNT
and the detonator, it will automatically drop both items and the countdown for the
explosion begins. The rocks will then be blown away, sky high.

The Frog uses batteries while driving and radar scans also use energy. The batteries
are charged by solar panels, but only when it is in direct sunlight.

The Radar provides blips; obstacle locations relative to the Frog's position and
heading. It does a -90° to +90° sweep at the height of the Frog's bottom and at the
height of it's roof.

The Communication protocol for the Frog's part is described below. It may be
extended for other communication like between Mission Control and the Pilot.

## Coordinate system

The coordinate system on the asteroid uses 3D-coordinates (in meters):

* x from left to right (on the 2D-map),
* y from down to up (i.e. height, therefore not on the 2D-map!) and
* z from bottom to top (on the 2D-map).

## Map

The satellite made an image of the asteroid's surface, covering an area of
1000 x 1000 m, of which the center location (x=0, z=0) is where the base is.

![asteroid Map](../src/main/resources/groundControl/Map.png)

## Protocol roles

There are three, must have, roles in the simulation and two optional:

1. The FROG: the vehicle on the asteroid. (a provided executable).
2. The PILOT: located at the asteroid base, navigates and drives the Frog.
3. Mission Control: on earth, guides the pilot. Messages between mission
   control and pilot take long to arrive due to the distance.
4. Terminal (optional): receives, and logs all received communication for
   analysis. It can also send messages. (lagged)
5. Guest (optional): for only viewing the Frogs adventures on earth,
   receives all messages but does not send any. (lagged)

Note that FROG and PILOT are mandatory role names in this communication system.
They have a fast, almost instant, communication speed. The rest of the role names,
which are free to choose, experience communication with a serious lag.

Due to available power and distances the PILOT is the only role that can
communicate with the FROG.
So, the FROG messages are only received by the PILOT.
On the other hand, all other roles can communicate with the PILOT, but with lag.

## Protocol architecture

```plantuml
    title The Frog System Architecture

    cloud asteroid {
        node "Frog" {
            component SaSaCommunicator as FC
            [FROG] -d- FC
        }
        node "The Pilot" {
            component SaSaCommunicator as PC
            database Database as PD
            [PILOT] -d- PC
            [PILOT] -u- PD
        }
    }
    
    cloud orbit {
        node Satellite {
            component SaSaServer as Server
        }
    }
    
    cloud Earth{
        node "Mission Control" {
            component SaSaCommunicator as MC
            component MissionControl as M
            database Database as MD
            M -u- MC
            M -d- MD           
        }
    }
    FC -d- Server : fast
    PC -d- Server : fast
    MC -u- Server : delayed
```

The databases can be used to make map data persistent and log mission data for
analysis and for replay.

## The SaSaCommunicator (v3.01)

The SaSaServer is a message broker, which is part of The Frog application.
It will relay all messages between the connected client, simulating as the SaSa
satellite.

Each client will have to connect to the SaSaServer through the
SaSaCommunicator API (a provided Java library). Every client will have to
implement its own protocol (specification of messages send and received) and
connect using a unique ID (protocol role).

SaSaServer messages are strings, consisting of two parts; \<ID\>, then a space
and then the \<PAYLOAD\>.

1. ID: a unique name for the sender. Like FROG, PILOT, CONTROL, TERMINAL,
   GUEST_1, GUEST_2 etc. Names cannot have spaces.
2. PAYLOAD: the message itself.

The next diagram shows the connection of the three clients to the SaSaServer in
the satellite. After communication is established the client makes itself known
by name by sending "\<name\> CONNECTED". The SaSaCommunicator takes care of this.

```plantuml
    title SaSa Connecting Clients to Server
    SaSaServer <- Frog : open communication
    SaSaServer <- Frog : FROG CONNECTED
    SaSaServer <- Pilot : open communication
    SaSaServer <- Pilot : PILOT CONNECTED
    SaSaServer <- Control : open communication
    SaSaServer <- Control : CONTROL CONNECTED
```

Note: there is no acknowledgement returned

When a connection fails, the SaSaCommunicator tries to reconnect every 2 seconds
as is shown in the next diagram.

```plantuml
    title Client reconnect loop 
    loop     
        GROUP IF not connected
            SaSaCommunicator -> SaSaCommunicator : wait 2s for server to init
            SaSaCommunicator -> SaSaServer : connect
            GROUP IF connected 
                SaSaCommunicator -> SaSaServer : <ID> CONNECTED
            END     
        ELSE Connected
            SaSaServer -> SaSaCommunicator : message 
            SaSaCommunicator -> SaSaServer : message 
        END
    end  
```

## The Frog protocol

The Frog has a fixed protocol, which is described here.

### Messages from The Frog

After being connected and named, communication can start. We assume communication
between FROG and PILOT here.

#### Status info

The Frog sends its status info on an interval basis (0.25s) when enabled by
receiving a "STATUS ON" message, or on request.

The Frog status has it's position (x, y, z), an angle (in degrees) and a current
energy level [0..1]  and charging level [0..1].

```plantuml
    Frog -> Pilot : FROG STATUS <x> <y> <z> <angle> <energy> <solar>
```

#### Radar info

The Frog sends radar info every 2 seconds when enabled with "RADAR ON" or
on request with "RADAR REQUEST".

```plantuml
    Frog -> Pilot : FROG RADAR START <originX> <originY> <originZ> <angle>
    loop for each angle in range 
        Frog -> Pilot : FROG RADAR BLIP <x> <y> <z>
    end
    Frog -> Pilot : FROG RADAR STOP
```

#### Other info

Receiving Frog's pickup event at the given location including the pickups name
(TNT or DETONATOR).

```plantuml
    Frog -> Pilot : FROG PICKUP <name> <x> <y> <z> 
```

Receiving Frog's dropping a pickup at a named location ( explosion area )

```plantuml
    Frog -> Pilot : FROG DROP <name> <x> <y> <z> 
```

Receiving the Frog touching it's base center to beam up, mission completed !

```plantuml
    Frog -> Pilot : FROG HOME <x> <y> <z> 
```

### Messages to The Frog

We assume the Pilot sending here, but any SaSaCommunicator can send these for the
Frog to process, with or without lag.

#### Driving the Frog

Note that every new drive command overrules a possibly running one, there is no
queue.

* power is ranged from -1.0..1.0 power factor, where 0 means braking,
* angle from -30°..30° and
* duration 0.0..5.0s, where 0 means continue until the next drive command is received.

```plantuml
    Pilot -> Frog : PILOT DRIVE <power> <angle> <duration>   
```

#### Requesting status info

Requesting the Frog's status, it's position and energy data.

```plantuml
    Pilot -> Frog : PILOT STATUS REQUEST   
    Frog -> Pilot : FROG STATUS <x> <y> <z> <energy> <charging>    
```

When the Frog's auto status updates are enabled (they are disabled by default),
the Frog send its status with an interval of 0.2s.

```plantuml
    Pilot -> Frog : PILOT STATUS ON   
    loop on interval
        Frog -> Pilot : FROG STATUS <x> <y> <z> <angle> <energy> <charging>
    end
    Pilot -> Frog : PILOT STATUS OFF   
```

After the STATUS OFF message, the automatic status updates are disabled again.

#### Requesting radar info

A single radar scan can be requested from The Frog.

```plantuml
    Pilot -> Frog : PILOT RADAR REQUEST
        Frog -> Pilot : FROG RADAR START <originX> <originY> <originZ> <angle>
        loop for each angle in range 
            Frog -> Pilot : FROG RADAR POINT <x> <y> <z> <angle>
        end
        Frog -> Pilot : FROG RADAR STOP
```

When the Frog's auto radar scans are enabled (they are disabled by default),
the Frog send its radar scans with an interval of 0.5s.

```plantuml
    Pilot -> Frog : PILOT RADAR ON   
    loop on interval
        Frog -> Pilot : FROG RADAR START <originX> <originY> <originZ> <angle>
        loop for each angle in range 
            Frog -> Pilot : FROG RADAR POINT <x> <y> <z> <angle>
        end
        Frog -> Pilot : FROG RADAR STOP
    end
    Pilot -> Frog : PILOT RADAR OFF   
```

After the RADAR OFF message, the automatic radar scans are disabled again.
