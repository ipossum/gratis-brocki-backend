# GratisBrocki

A platform that can be freely used by everyone to share or give away all kinds of things for free. Some of the main goals are
* to make sustainable resource use easier
* to help transition to a minimalist lifestyle
* to facilitate giving and sharing without financial motives
* to create a free and simple, but effective platform

GratisBrocki was developed as part of a student project on object-oriented programming.
We hope you enjoy it. We did :-)

Go [here](https://github.com/ipossum/gratis-brocki-final-frontend) to find our frontend repository.

## Installation

Use Project from Version Control in your IntelliJ IDEA. 
Just copy the link from git and paste it into IDEA.

## Usage

There are different ways to use this application. When just using the 
backend, the only way to interact with the backend via its API (e.g. using Postman). If you 
want to enjoy the full package, you have to use the frontend additionally. 
You can find it [here](https://github.com/ipossum/gratis-brocki-final-frontend).

## Contributing

* Fork it!
* Create your feature branch: `git checkout -b fb-description`
* Commit your changes: `git commit -am 'Add some feature'`
* Push to the branch: `git push origin fb-description`
* Submit a pull request!

## Description/Documentation

The backend currently uses an H2 database. The database holds data about users,
items, pictures and messages. So far User, Item and Picture are implemented in 
the code and can be manipulated via the API.

## APIs

The following APIs are available for users:
* POST api/v1/users --> register a new user
* GET api/v1/users/{id} --> load a specific user from the database
* PUT api/v1/users/{id} --> adjust data of a specific user
* DELETE api/v1/users/{id} --> delete specific user

The following APIs are available for items:
* POST api/v1/items --> load a new item into database
* GET api/v1/items/{id} --> load specific item from database
* GET api/v1/items/all --> load all items from database
* GET api/v1/items/ --> load items using filter
* PUT api/v1/items/{id} --> adjust data of a specific item
* DELETE api/v1/items/{id} --> delete specific item

The following APIs are available for pictures:
* POST api/v1/pictures --> load a new picture into the database
* GET api/v1/pictures --> load pictures using filters
* PUT api/v1/pictures/{id} --> adjust data of a specific picture
* DELETE api/v1/pictures/{id} --> delete a specific picture

## Request-Body

For the POST and PUT methods a request-body is sent with the request. These are composed as follows.

* User:
```
{
    "username": "TestUser",
    "email": "test@test.ch",
    "phoneNumber": "0793940283",
    "password": "qwertz@1234"
}
```
* Item:
```
{
    "title": "title of the item",
    "description": "description of the item you want to give away",
    "zipCode": "9014",
    "category": "Category.SPORT",
    "condition": "Condition.USED",
    "owner": 1
}
```

* Pictures:
```
{
    "name": "name-of-picture", 
    "url": "https://www.meinBild.ch/img/fahrrad-rot.jpg", 
    "item": 1 
}
```

## Caution!

This version is for testing purposes only. Some components are
still missing. Among other things, a working login and authentication is 
not yet implemented. This should be extended in the near future, so that a 
secure login and authentication are possible.
