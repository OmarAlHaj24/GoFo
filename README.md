# GoFo
The purpose of this software is to organize the process of booking football playgrounds and make it easier for both the playgrounds owners and the players.

## PlayerComponents Package
* ### Player Class
    Player class includes all the players data, his team, as well as all the functions that has to do with the player such as viewing his bookings, booking a new playground, registering for a team and so on.
* ### FavoriteTeam Class
    FavoriteTeam class that represents the created team by the player named favorite team to invite those players to a specific playground.
* ### Complaint Class
    Complaint class will contain all the attributes and functions required by a player to file a complaint against any of the playgrounds he booked before.

## PlaygroundComponent
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
