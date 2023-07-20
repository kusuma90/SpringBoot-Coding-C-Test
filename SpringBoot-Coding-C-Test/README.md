# SpringBoot-Coding-C-Test

Build: ./gradlew build
Run: 

Testing in Postman:

Request URL: http://localhost:8080/rewards/calculatePoints
Methos: Post
Body: Sample json data:
[
{
"customerId": 1,
"transactionAmount": 400,
"transactionDate": "2023-07-01"
},
{
"customerId": 1,
"transactionAmount": 50,
"transactionDate": "2023-07-15"
},
{
"customerId": 2,
"transactionAmount": 80,
"transactionDate": "2023-07-05"
},
{
"customerId": 3,
"transactionAmount": 250,
"transactionDate": "2023-07-10"
}
]

Sample OutPut:
{
"1": {
"2023-07": 650
},
"2": {
"2023-07": 30
}
}


