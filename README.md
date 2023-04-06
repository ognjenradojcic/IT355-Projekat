# IT355 - Project assignment 

## Topic: **Web Shop**

Project is created as RESTfull web service. API documentation can be read through Swagger-ui on
<a href="http://localhost:8080/api/swagger-ui/index.html">http://localhost:8080/api/swagger-ui/index.html</a>. 
Service is secured using JWT authentication.

## Technologies used

* Java 17
* Spring Boot 3.0.5
* Docker 4.18.0
* MySQL 8.0.32
* Hibernate

## API ENDPOINTS

### Auth

| Method | URL | Description | Request Example |
| -------|-----|------------ | ----------------|
| POST | /api/v1/auth/login | Login | [JSON](#login) |
| POST | /api/v1/auth/register | Register | [JSON](#register) |
| POST | /api/v1/auth/refresh | Refresh | [JSON](#refresh) |

### Users

| Method | URL | Description | Request Example |
| -------|-----|------------ |-----------------|
| GET | /api/v1/users | Get all users | / |
| GET | /api/v1/users/{id} | Get user by ID| / |
| PUT | /api/v1/users/{id} | Update user | [JSON](#user_request) |
| DELETE | /api/v1/users/{id} | Delete user by ID | / |
| GET | /api/v1/users/profile | Get user data | / |

### Products

| Method | URL | Description | Request Example |
| -------|-----|------------ |-----------------|
| GET | /api/v1/products  | Get all products| / |
| GET | /api/v1/products/{id} | Get product by ID| / |
| POST | /api/v1/products  | Create a new product | [JSON](#product_request) |
| PUT | /api/v1/products/{id} | Update product | [JSON](#product_request)|
| DELETE | /api/v1/products/{id} | Delete product by id | / |

### Categories

| Method | URL | Description | Request Example |
| -------|-----|------------ |-----------------|
| GET | /api/v1/categories  | Get all categories | / |
| GET | /api/v1/categories/{id} | Get category by ID| / |
| POST | /api/v1/categories | Create a new category | [JSON](#category_request) |
| PUT | /api/v1/categories/{id} | Update category | [JSON](#category_request)|
| DELETE | /api/v1/categories/{id} | Delete category by id | / |


### Invoices

| Method | URL | Description | Request Example |
| -------|-----|------------ |-----------------|
| GET | /api/v1/invoices  | Get all invoices | / |
| GET | /api/v1/invoices/{id} | Get invoice by ID| / |
| POST | /api/v1/invoices  | Create a new invoice| [JSON](#invoice_request) |
| DELETE | /api/v1/invoices/{id} | Delete invoice by id| / |
| GET | /api/v1/invoices/users | Get user's invoices | / |

## JSON request examples

### Auth

##### <a id="register">Register -> /api/v1/auth/register</a>

```json
{
    "username": "exampleUsername",
    "password": "examplePassword",
    "firstName": "exampleFirstName",
    "lastName": "exampleLastName",
    "phone": "examplePhone"
}
```

##### <a id="login">Login -> /api/v1/auth/login</a>

```json
{
  "username": "example@gmail.com",
  "password": "examplePassword"
}
```

##### <a id="refresh">Refresh -> /api/v1/auth/refresh</a>

```json
{
  "refreshToken": "exampleToken",
}
```

### <div id="user_request">Users</div>

Update -> /api/v1/users/{id}

```json
{
    "firstName": "exampleFirstName",
    "lastName": "exampleLastName",
    "phone": "examplePhone"
}
```

### <div id="product_request">Products</div>

Create -> /api/v1/products <br/>Update -> /api/v1/products/{id}

```json
{
    "name": "exampleName",
    "manufacturer": "exampleManufacturer",
    "price": 0,
    "categoryId": 0
}
```

### <div id="category_request">Categories</div>

Create -> /api/v1/categories <br/>Update -> /api/v1/categories/{id}

```json
{
    "name": "exampleName"
}
```

### <div id="invoice_request">Invoices</div>

Create -> /api/v1/invoices

```json
{
    "address": "exampleAddress",
    "userId": 0,
    "items": [
        {
            "productId": 0,
            "quantity": 0
        }
    ]
}
```

