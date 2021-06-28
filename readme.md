# Shopping Cart

Shopping Cart Expereince

## Assumptions

- User login/register using JWT/OpenID OAuth implemented.
- Product Category and Product are present in the catalog.
- Shipping Address/Billing Address not considered in Order.
- Cart is designed per user (UserId is the key to cart and not the cart id).
- Payment gateway integration to be in place before placing order.


## Features

- Add batch catalog products.
- Fetch All products.
- Delete a product.
- Add to new product to cart from product page.
- Add existing product to cart from product page.
- Minimize/Maximize product in cart from Cart page.
- Out of stock/product removed from catalog handled in cart.
- Remove a product from cart.
- Clear or empty the entire cart.
- Checkout the cart and place order.
- Get all orders in the system.
- Get orders per user in the system.


## Tech Stack
- Java
- Spring Boot
- Redis
- Mysql
- Docker
- Swagger


## Installation/Execution
Download, Install and Run Docker desktop.

Run Redis.

```sh
docker run --name redis-container -d redis

docker run -it --name my-redis-cli --link my-redis-container:redis --rm redis redis-cli -h redis -p 6379
```

Build and execute application

```sh
./mvnw clean package   

docker-compose up --build
```


## Swagger Link
http://<host_name>:<port>/swagger-ui/
