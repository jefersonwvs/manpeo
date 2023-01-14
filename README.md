# `manpeo`

This is a REST API for people registration manager.


### Available endpoints:

`POST` `/api/v1/people`: inserts a new person;

`GET` `/api/v1/people/:personId`: retrieves a person by its ID;

`GET` `/api/v1/people`: retrieves all registered people;

`PUT` `/api/v1/people/:personId`: updates a person by its ID;

`DELETE` `/api/v1/people/:personId`: deletes a person by its ID;

`POST` `/api/v1/people/:personId/addresses`: inserts a new address;

`GET` `/api/v1/people/:personId/addresses`: retrieves all person's addresses;

`PUT` `/api/v1/people/:personId/addresses/:addressId`: sets an address as the main person's address.

### How to use this API:
1. Clone this repository;
2. `cd` into the repository;
3. Execute `mvn package` command;
4. Execute `java -jar target/manpeo-0.0.1-SNAPSHOT.jar`.