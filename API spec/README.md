
# Open API for Banking to manage account services

This project desing OpenAPI sepcification in (OAS 3.0.x) platform. To transformatin customer journey to digitalize into self service for Open Banking has captured the imagination of digital leaders and is emopowering customers to use new digital services with their banking information. Harnessing the power of platform that is open source, is helping banks to be in control of their own destintity and accelerating innovation. Create an ecosystem, connect with your customers or simply make it easier to partner with you. The objective of this project is to provide a an accelerator for open banking


## APIs

#### /api/v1/customer/{custID}/accounts
* `GET` : Get all accounts details

####  /api/v1/accounts/{accountNumber}/current:
* `GET` : Get current accounts details
####  /api/v1/accounts/{accountNumber}/saving:
* `GET` : Get saving accounts details
####  /api/v1/accounts/{accountNumber}:
* `POST`: Create new account number
* `PUT` : Update account details
* `DELETE` : Delete account number

#### /api/v1/accounts/{accountNumber}/current/transactions
* `GET` : reterive transaction history for specific account
#### /api/v1/accounts/{accountNumber}/saving/transactions
* `GET` : reterive transaction history for specific account

####  /api/v1/accounts/{accountNumber}/saving/transfers
* `POST` : Fund transfer to beneficiary account 
####  /api/v1/accounts/{accountNumber}/saving/transfers
* `POST` : Fund transfer to beneficiary account
  
## Structure
```
├── openapi.yaml
│  ├── parameters
│   │   └── accountNumber.yaml
│   │   └── customerID.yaml
│   ├── schemas
│   │   └── request
│  │   │   └── AccountTransferRequest.yaml     // APIs for Task model
│  │   │   └── AccountTransferAccountRequest.yaml     // APIs for Task model
│  │   │   └── CreateNewAccountRequest.yaml
│  │   │   └── UpdateAccountRequest.yaml
│  │   │   └── AccountTransferToAccountRequest.yaml
│  │   │   └── AccountTransferFromAccountRequest.yaml 
│   │   └── respons
│  │   │   └── AccountTypeResponse.yaml     // APIs for Task model
│  │   │   └── AccountCardResponse.yaml     // APIs for Task model
│  │   │   └── CreateNewAccountRequest.yaml
│  │   │   └── UpdateAccountRequest.yaml
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
│   │   └── currentAccountResponse_200.yaml
│   │   └── currentTransactionHistorySuccessfull_200.yaml
│   │   └── savingAccountTypeResponse_200.yaml
│   │   └── savingTransactionHistorySuccessfull_200.yaml
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
