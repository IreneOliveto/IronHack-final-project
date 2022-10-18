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
  * MySql

### Run the application

Open the application in Intellij, and right click on the project and Run As -> Spring Boot application
And then follow the step by step guide (below) to interact with the system.

### To run tests

Open the application in Intellij, and right click on src/test/java directory in project explorer, and right click -> Run As -> JUnit test

## Use Cases

* [Get an admin account](#get-admin)
* [Get an account-holder account](#get-account-holder)
* [Get a third-party user](#get-third-party)
* [Create new Checking Account for the account holder younger than 24](#create-checking)
* [Increase Checking Account by 100](#increase-checking)
* [Withdrawal from a third-party account](#withdrawal)
* [Get information abount the account holder accounts](#balance)
* [Do a transaction between two accounts](#transaction)

## Steps

#### <a name="#get-admin">Get an admin account</a>

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

#### <a name="#get-account-holder">Get an account-holder account</a>


		USERNAME: admin1
		PASSWORD: admin123

*Request*

GET 	http:/localhost:8080/account-holder/1

*Response*

	{
	    "userId": 1,
	    "username": "user1",
	    "name": "John",
	    "dateOfBirth": "1989-03-14",
	    "mailingAddress": "prueba1@hola.com",
	    "primaryAddress": {
		"street": "Str",
		"city": "St Q",
		"postalCode": 8193,
		"country": "ESP"
	    }
	}

*Request*

GET 	http:/localhost:8080/account-holder/2

	{
	    "userId": 2,
	    "username": "user2",
	    "name": "Fran",
	    "dateOfBirth": "1999-08-03",
	    "mailingAddress": "prueba2@hola.com",
	    "primaryAddress": {
		"street": "Str",
		"city": "FGS",
		"postalCode": 9765,
		"country": "ESP"
	    }
	}

#### <a name="#get-third-party">Get a Third-Party user</a>

		USERNAME: admin1
		PASSWORD: admin123
		
*Request*

GET 	http:/localhost:8080/admin/third-party/3

*Response*

	{
	    "userId": 3,
	    "username": "third_party",
	    "name": "third_party",
	    "hashedKey": 111
	}

#### <a name="#create-checking">Create new Checking Account for the account holder younger than 24</a>

		USERNAME: admin1
		PASSWORD: admin123

*Request*

POST http:/localhost:8080/checking

*Request Body*
	2

*Response*

	{
	    "accountId": /* new accountId*/,
	    "balance": {
		"currency": "EUR",
		"amount": 0
	    },
	    "penaltyFee": 40,
	    "secondaryAccountHolder": null,
	    "creationDate": "2022-10-17",
	    "lastModifiedDate": null,
	    "minimumBalance": 0, /* Student account doesn't have Minimum Balnace*/
	    "monthlyMaintenanceFee": 0, /* Student account doesn't have Maintenance Fee*/
	    "status": "ACTIVE"
	}

#### <a name="#increase-checking">Increase Checking Account by 100</a>

		USERNAME: admin1
		PASSWORD: admin123

*Request*

PATCH http:/localhost:8080/checking/3/deposit

*Request Body*

		{
		"balance": {
			"currency": "EUR",
			"amount": 100
		    }
		}

*Response*

	{
	    "accountId": 3,
	    "balance": {
		"currency": "EUR",
		"amount": 1840
	    },
	    "penaltyFee": 40,
	    "secondaryAccountHolder": null,
	    "creationDate": "2021-08-10",
	    "lastModifiedDate": "2022-10-17",
	    "minimumBalance": 250,
	    "monthlyMaintenanceFee": 12,
	    "status": "ACTIVE"
	}


#### <a name="#withdrawal">Withdrawal from a third-party account</a>

		USERNAME: third_party
		PASSWORD: 123
		
*Request*

PATCH http:/localhost:8080/checking/3/withdrawal

*Header*

HEADER hashedKey 111

*Request Body*

	{

	  "receiverAccountId" : 3,
	  "amount": {
		"currency": "EUR",
		"amount": 100
	    },
	  "secretKey": 1234
	}

*Response*

	true
	
#### <a name="#balance">Get information abount the account holder accounts</a>

Account1 has a Checking Account and a Credit Card

		USERNAME: user1
		PASSWORD: user123
		
*Request*

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

*Request*
	
GET	  http:/localhost:8080/account/2

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
	
	
#### <a name="#transaction">Do a transaction between two accounts</a>

		USERNAME: user1
		PASSWORD: user123


*Request*

PATCH	http:/localhost:8080/account/transfer/2

*Request Body*

	{
	  "receiverName": "Jen",
	  "senderAccountId": 2,
	  "receiverAccountId" : 7,
	  "amount": {
		"currency": "EUR",
		"amount": 300
	    }
	}


*Response*

true








		
		




	

	




