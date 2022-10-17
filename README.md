# IronHackFinalProject

This guide show to interact with the REST api using tools like Postman. You will find the steps below to setup test data using REST API request and the corresponding response.

## Objective

To write a banking applications with 3 types of users:
* **Admins Users** should be able to access the balance for any account and to modify it, create new third parties to add in the database.
* **AccountHolders Users** should be able to access their own account balance, transfer money from any of their accounts to any other account (regardless of owner). The transfer should only be processed if the account has sufficient funds.
* **Third-Party Users** should be able to receive and send money to other accounts. In order to receive and send money, Third-Party Users must provide their hashed key in the header of the HTTP request and provide the amount, the Account id and the account secret key of the receiver account.

## Technology
Technology / Library / Framework used:

  * Java
  * Spring Boot
  * REST API
  * Maven
  * Mock mvc
  * Spring Boot JPA
  * Spring Security
  * Postman

### Run the application

Open the application in Intellij, and right click on the project and Run As -> Spring Boot application
And then follow the step by step guide (below) to interact with the system.

### To run tests

Open the application in Intellij, and right click on src/test/java directory in project explorer, and right click -> Run As -> JUnit test

## Use Cases

* [Get admin account](#get-admin)
* [Get account-holder account](#get-account-holder)
* [Get third-party](#get-third-party)
* [Create new Checking Account for the account holder younger than 24](#create-checking-account)
* [Increase Checking Account by 100](#increase-checking)
* [Withdrawal from a third-party acocunt](#withdrawal)
* [Get information abount the account holder accounts](#balance)
* [Do a transaction between two accounts](#transaction)

## Steps

#### <a name="#get-admin">Get admin account</a>

		USERNAME: admin1
		PASSWORD: admin123

*Request*

GET 	/admin/4

*Response*

        {
            "userId": 4,
            "username": "admin",
            "name": "Josh"
        }

#### <a name="#get-account-holder">Get account-holder account</a>
Account1 has a Checking Account and a Credit Card
		USERNAME: user1
		PASSWORD: user123

GET	  http:/localhost:8080/account/1

*Response*

          [
              {
                  "accountId": 1,
                  "balance": {
                      "currency": "EUR",
                      "amount": 5460
                  },
                  "penaltyFee": 40,
                  "secondaryAccountHolder": null,
                  "creationDate": "2019-01-05",
                  "lastModifiedDate": "2022-10-17",
                  "minimumBalance": 250,
                  "monthlyMaintenanceFee": 12,
                  "status": "ACTIVE"
              },
              {
                  "accountId": 4,
                  "balance": {
                      "currency": "EUR",
                      "amount": 5050
                  },
                  "penaltyFee": 40,
                  "secondaryAccountHolder": null,
                  "creationDate": "2022-09-02",
                  "lastModifiedDate": "2022-10-17",
                  "creditLimit": 100000,
                  "interestRate": 0.1
              }
          ]

Account2 has a Savings Account

        USERNAME: user2
        PASSWORD: user456

*Response*

        [
            {
                "accountId": 8,
                "balance": {
                    "currency": "EUR",
                    "amount": 8000
                },
                "penaltyFee": 40,
                "secondaryAccountHolder": null,
                "creationDate": "2019-01-05",
                "lastModifiedDate": "2022-01-05",
                "minimumBalance": 100,
                "monthlyMaintenanceFee": null,
                "status": null,
                "interestRate": null
            }
        ]



