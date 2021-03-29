## Launch the application
```java -jar build/libs/aws1-0.0.1-SNAPSHOT.jar```

## APIs for Postgresql usage
1. Bulk upload of the customers using below api. No need to pass any data.

http://localhost:9090/bulkcreate

2. POST call to insert one customer based on the JSON data passed.

http://localhost:9090/create

### JSON data
```
{
	"firstName": "Dmytro",
	"lastName": "Kasiianenko"
}
```

2. GET call to retrive all customers.

http://localhost:9090/findall

3. GET call to search customer by id.
http://localhost:9090/search/{id}

4. GET call to search customer by first name.
http://localhost:9090/searchbyfirstname/{firstname}
