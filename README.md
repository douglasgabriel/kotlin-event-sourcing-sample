Event Sourcing with pure Kotlin example
===
The goal of this project is to show a sample of event sourcing implementation with pure Kotlin, with the minimum of 
frameworks.

### Tech stack
- [Gradle](https://gradle.org/)
- [Kotlin](https://kotlinlang.org/)
- [Javalin](https://javalin.io/)
- [Koin](https://insert-koin.io/)
- [MongoDB](https://www.mongodb.com/)
- [Spek Framework](https://spekframework.org/)

### Running
In order to get the project running, you need to:

* Provide the following environment vars:

```
SERVER_PORT     7000
MONGO_HOST      localhost 
MONGO_PORT      27017
MONGO_USER      mongo
MONGO_PASS      password
MONGO_DATABASE  admin
```

* Provide a running MongoDB instance that correspond to the configured vars. You can use Docker, for example:

```
$ docker container run 
                        -e MONGO_INITDB_ROOT_USERNAME=mongo \
                        -e MONGO_INITDB_ROOT_PASSWORD=mongo \
                        -e MONGO_INITDB_DATABASE=credit_limit \ 
                        -p 127.0.0.1:27017:27017 \
                        --name credit_limit \
                        -d mongo
```

* Execute the application

```
$ gradle run
```

### About the project

#### Features

In order to test some flows of event sourcing implementation, some features were implemented:

* **Create a user:** You can create a new user by giving its username:

`[POST] /users`
```json
{
  "username": "userNameTest"
}
``` 

* **Block a user:** You can block and unblock a user by giving its block state:

`[PATCH] /users`
```json
{
  "username": "userNameTest",
  "block": true
}
```
