# Sii - Recruitment task

##  How to build and run the application
1. Clone the repository
2. Run:
``` shell
docker build -t sii-recruitment-task .
```
3. Run:
``` shell
docker run -p 8080:8080 sii-recruitment-task
```
3. The application will be available at `http://localhost:8080`
4. Use the following URL's to access the REST services:


## REST API endpoints
1. Create a new product
```http request
POST http://localhost:8080/add-product
{
  "currency": "USD",
  "name": "name",
  "description": "description",
  "price": 2.0
}
```
2. Get all products
```http request
GET http://localhost:8080/products
```
``` shell
[
    {
        "currency": "USD",
        "name": "name",
        "description": "description",
        "price": 2.0
    }
]
```


3. Update product data

```http request
PATCH localhost:8080/update-product?name=name
{
  "price": 20.0
}
```
4. Create a new promo code.
```shell
POST http://localhost:8080/add-promo-code
{
  "code": "promocode",
  "currency": "USD",
  "maxUses": 5,
  "discount": 3
}
```
5. Get all promo codes.
```http request
GET http://localhost:8080/promo-codes
```
Repsonse:
```shell
[
    {
        "code": "promocode",
        "currency": "USD",
        "maxUses": 5,
        "discount": 3,
        "uses:": 0
    }
]
```

6. Get one promo code's details by providing the promo code.
```http request
GET http://localhost:8080/promo-code?code=promocode
```
Response:
```shell
{
    "code": "promocode",
    "currency": "USD",
    "maxUses": 5,
    "discount": 3,
    "uses:": 0
}
```

7. Get the discount price by providing a product and a promo code.
```http request
GET http://localhost:8080/discount-price?product=name&promo=promocode
```
Response:
```shell
{
    "discountPrice": 17.0
}
```

8. Simulate purchase
```http request
POST http://localhost:8080/purchase
{
  "productName": "name",
  "promoCode": "promocode"
}
```

Response:
```shell
{
    "creationDate": "2024-06-13T21:12:16.505581",
    "regularPrice": 20.0,
    "discountPrice": 17.0,
    "currency": "USD",
    "productDto": {
        "currency": "USD",
        "name": "name_r27mk",
        "description": "description_dqloe",
        "price": 200.0
    },
    "warning": null
}
```

9. A sales report: number of purchases and total value by currencyEnum
```http request
GET http://localhost:8080/sales-report
```

Response:
```shell
[
    {
        "currency": "USD",
        "totalAmount": 20.0,
        "totalDiscount": 3.0,
        "numberOfPurchases": 1
    }
]
```

You can check and try out all endpoints using the Swagger UI at `http://localhost:8080/swagger-ui.html`