
# Open API for Banking to manage account services

This project design in OpenAPI specification in (OAS 3.0.x)  to build assignment aka BookMyShow digital API platform for online ticket booking.
Enable or transform as digital customer journey as  self service based, captured the imagination of digital leaders and is empowering customers to use new digital services. Harnessing the power of platform that is open source, is helping and control of their own destiny and accelerating innovation. Create an ecosystem, connect with your customers or simply make it easier to partner with you.


## customer API

####  /user-svcc/v1/contacts/{Id}:
* `GET` : Get specific user details
####  /user-svc/v1/contacts/:
* `POST` : Add new user 

####   /catalog-svc/v1/carts/:
* `POST` :Add new theatre
####  /catalog-svc/v1/carts/{Id}:
* `PUT` : Modify existing theater details
* ####  /catalog-svc/v1/carts/{Id}:
* `DELETE` : Modify existing theater details
* * ####  /catalog-svc/v1/carts/{Id}:
* `GET` : Retrieve existing theater details

####   /catalog-svc/v1/audies/:
* `POST` :Add new theatre
####  /catalog-svc/v1/audies/{Id}:
* `PUT` : Modify existing theater details
* ####  /catalog-svc/v1/audies/{Id}:
* `DELETE` : Modify existing theater details
* * ####  /catalog-svc/v1/audies/{Id}:
* `GET` : Retrieve existing theater details


####   /catalog-svc/v1/shows/:
* `POST` :Add new theatre
####  /catalog-svc/v1/shows/{Id}:
* `PUT` : Modify existing theater details
* ####  /catalog-svc/v1/shows/{Id}:
* `DELETE` : Modify existing theater details
* * ####  /catalog-svc/v1/shows/{Id}:
* `GET` : Retrieve existing theater details
* 
####   /catalog-svc/v1/cities/:
* `POST` :Add new theatre
####  /catalog-svc/v1/cities/{Id}:
* `PUT` : Modify existing theater details
* ####  /catalog-svc/v1/cities/{Id}:
* `DELETE` : Modify existing theater details
* * ####  /catalog-svc/v1/cities/{Id}:
* `GET` : Retrieve existing theater details

####   /catalog-svc/v1/movies/:
* `POST` :Add new theatre
####  /catalog-svc/v1/movies/{Id}:
* `PUT` : Modify existing theater details
* ####  /catalog-svc/v1/movies/{Id}:
* `DELETE` : Modify existing theater details
* * ####  /catalog-svc/v1/movies/{Id}:
* `GET` : Retrieve existing theater details

####   /catalog-svc/v1/theatres/:
* `POST` :Add new theatre
####  /catalog-svc/v1/theatres/{Id}:
* `PUT` : Modify existing theater details 
* ####  /catalog-svc/v1/theatres/{Id}:
* `DELETE` : Modify existing theater details
* * ####  /catalog-svc/v1/theatres/{Id}:
* `GET` : Retrieve existing theater details


####   /catalog-svc/v1/orders/initiate:
* `POST` :Add new theatre
####  /catalog-svc/v1/orders/process:
* `POST` : Modify existing theater details

## Structure
```
├── openapi.yaml
│   ├── schemas
│   │   └── request
│  │   │   └── audiUpsertRequest.yaml     // APIs for Task model
│  │   │   └── cartsUpsertRequest.yaml     // APIs for Task model
│  │   │   └── cityUpsertRequest.yaml
│  │   │   └── movieUpsertRequest.yaml
│  │   │   └── ordernItiateUpsertRequest.yaml
│  │   │   └── showUpsertRequest.yaml 
│  │   │   └── theatreUpsertRequest.yaml 
│   │   └── respons
│  │   │   └── AudiResponse.yaml     // APIs for Task model
│  │   │   └── cartsUpsertResponse.yaml     // APIs for Task model
│  │   │   └── city.yaml
│  │   │   └── movie.yaml
│  │   │   └── orderInitiateUpsertResponse.yaml
│  │   │   └── orderProcessUpsertResponse.yaml
│  │   │   └── showUpsertResponse.yaml
│  │   │   └── theatreUpsertResponse.yaml
│  │   │   └── userContactDetails.yaml
│  │   └── error
│  │   │   └── ProblemDetails.yaml
│  │   │   └── ValidationProblemDetails.yaml
│  ├── httpresponse
│   │   └── 200.yaml
│   │   └── 201.yaml
│   │   └── 204.yaml
│   │   └── 400.yaml
│   │   └── 401.yaml
│   │   └── 402.yaml
│   │   └── 404.yaml
│   │   └── 404.yaml
│   │   └── 500.yaml

│  ├── SecuritySchemas

## Todo

- [x] Support  REST APIs all standars and patterns.
- [x] Add on all dummy sample responses.
- [x] Support OpenAPI(OAS 3.0.x) all standards.
- [x] Support platform 360 degree view for all business LOBs.
- [x] Support authentication and autherization.
- [x] Add all mock examples  to support all test coverage for successful response, request and error scenarios
- [x] adopt modularziation 
- [x] Cover all business scenarios   
- [x] Modularity in designing as top to botton approach   
- [x] Considering Resuability and global declaration   


## Install
  

## Run the app
convert particular platfomr by leavearing code-gen

## Run the tests
using any api based platform which support OpenAPI 3.0.X (OAS)
in this project used mulesoft anypoint platform to generate Swagger API specifications


## Contributing
Github URL:

## License

Copyright 2018-2023 XXX, Inc.
