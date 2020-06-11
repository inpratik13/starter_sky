# SKY - a project template

## This is a Gradle project is ready to use with following setup:
- Embedded Jetty server
- Spring DI, ORM, Transaction management
- Hibernate
- Jersey api
- Authentication and authorisation
- Some sample apis

### 2 sub-projects:
- core: is for business and DB layer
- api: is api layer

### Database:
A sub-directory `db` contains `docker-compose.yaml` with mysql db configuration.
To start the db, open terminal in `db` directory and run command `docker-compose up`.
(You should have docker installed).

### To get started with project:
- Checkout the project
- Rename project names in all build.gradle &  settings.gralde to your desired project names
- Rename all Java packages to your desired names
- Implement your authentication logic inside `com.vyantech.sky.api.service.auth.UserAuthServiceImpl`
- Update DB credentials in `api/src/main/resources/persistence.properties` file

### How to Run:
To run the api, just run `com.vyantech.sky.api.App`

### Sample endpoints:
#### `com.vyantech.sky.api.rest.test.HelloEndpoint.helloAnonymous(String)`
You can trigger the endpoint with following command
`curl localhost:8080/rest/test/helloAnonymous?name=Pratik`
Should give you result `{"message":"Hello Anonymous Pratik!"}`.
This endpoint ndoesn't have any security.

#### `com.vyantech.sky.api.rest.token.TokenEndpoint.getToken(String)`
You can call this endpoint to retrieve a token that can be used on secured endpoints.
You can trigger the endpoint you can use following command
`curl localhost:8080/rest/token --user "abc:xyz"`
You will received a response similar to `{"token":"....token..here..."}`, with an actual token.

#### `com.vyantech.sky.api.rest.test.HelloEndpoint.helloUser(String)`
Now you can call another secured endpoint using above token with below command.
`curl localhost:8080/rest/test/helloUser -H "Authorization: Bearer ....token..here..."`
You will see the response something similar to `{"message":"Hello User World!!"}`.
If you call this endpoint without a header it will result in `403 Forbidden` HTTP error.
If you call this endpoint with wrong/invalid token it will result in `401 Unauthorized` HTTP error.

