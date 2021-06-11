# GoFo
**The purpose of this software is to organize the process of booking football playgrounds and make it easier for both the playgrounds owners and the players.**

## PlayerComponents Package
* ### Player Class
    Player class includes all the players data, his team, as well as all the functions that has to do with the player such as viewing his bookings, booking a new playground, registering for a team and so on.
* ### FavoriteTeam Class
    FavoriteTeam class that represents the created team by the player named favorite team to invite those players to a specific playground.
* ### Complaint Class
    Complaint class will contain all the attributes and functions required by a player to file a complaint against any of the playgrounds he booked before.

## PlaygroundComponent Package
* ### PlaygroundOwner Class
    PlaygroundOwner class will contain all the functions that are exclusive to the playground owner such as registering a new playground as well as all of his account’s info.
* ### Playground Class
    Playground class will contain all the details of a playground such as the name and the ID.
* ### Booking Class
    Booking class contains all the details of a booking including the player ID, the playground owner ID and so on.
* ### TimeDate Class
    TimeDate class will contain all the details of the exact time and date, it will be used in specifying the time and date of all the slots.
* ### Slot Class
    Slot class will contain all the details of a slot such as the time and date of it.
* ### Status Enum
    Enumeration that represents different playground and booking status.

## System Package
* ### SystemGoFo Class
     System class is the main class of this system, it should prompt the user to choose either to register or to login using their already existing accounts.
* ### Database Class
    Database class acts as the database for our system, it stores static arrays which will be holding all the data that will be used by any of the users, it will also allow us to manipulate any of these arrays.
* ### AccountInfo Class
    AccountInfo class will contain all the data of all the users that are registered to the system whether it be a player or a playground owner.
* ### User Interface
    User interface includes all the basic functions of both players and playground owners such as the menus, the bookings, the requests, etc.
* ### Administrator Class
    Administrator class will hold the functions that are only exclusive for the administrator such as approving of newly added playgrounds.
    
    
## How to book a playground
* ### Register as administrator. 
* ### Register as playground owner.
* ### Register a new playground then its status will be pending until the acceptance of the administrator.
* ### Edit playground --> add slot.
* ### Login as administrator --> View playgrounds --> Edit Playgroud --> Update Playgrounds state to Accepted.
* ### Register as player.
* ### Book a playground --> choose filter --> input chosen playground's id and slot's id.
* ### Login as playground owner --> View my requests --> Accept booking.
