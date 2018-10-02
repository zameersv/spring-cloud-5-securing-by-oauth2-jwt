# spring-cloud-5-securing-by-oauth2-jwt
securing micro services using OAuth2 &amp; JWT

## Project Build
### Pre-requisites
1.  Java8
### Build Steps:
1. clone the project from the gitHub: 
2. From Project directory, run - `./gradlew clean build`
3. Start the spring boot app, run - `./gradlew :<moduleName>:bootRun`. For example, `./gradlew :registry:bootRun`

## Start all the servers.
~~~
./gradlew :registry:bootRun
./gradlew :api-gateway:bootRun
./gradlew :auth-server:bootRun
./gradlew :customer:bootRun
~~~  
## Overview.

In this project, OAuth2 & JWT is used for securing the micro-services. JWT will sign the tokens using the private key generated using the Keytool. 

1. Auth-server is using OAuth2 with signed tokens using JWT.
2. API gateway application to route all our requests into our microservices.
3. Registry - Eureka server for registration of all our services.
4. Customer Service - This is our simple Resource Server, exposing some basic REST APIs. Customer service will validate the JWT token with the public key.

### Import the postman collection file - spring-cloud-5-securing-by-oauth2-jwt.postman_collection.json

After  importing the collection into Postman, perform the below steps.

1. Trigger the get_admin_WRITE_READ_access_token request - It will generate access token for Admin
2. update the Bearer token in get_customer_details_with_api_gateway request and trigger the request - you should see the 200 OK Response.
3. update the Bearer token in create_customer_details request and trigger the request - You should see 201 Created response as the admin user has 
4. Now, trigger get_apiuser_READ_access_token request and copy the access token from the request body.
5. update the Bearer token in get_customer_details_with_api_gateway request and trigger the request - you should see the 200 OK Response.
6. update the Bearer token in create_customer_details request and trigger the request - You should see 403 permission denied response code as the apiuser only has only READ access. Our post method in customer API is allowed only for users with WRITE permisssions.
7. Trigger any request without bearer token or some invalid token - You will see 400 unauthorized response code.

