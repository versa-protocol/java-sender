# Versa Sender (Java implementation)

This is a Java implementation of the Versa Sender. It is a simple program that demonstrates sending a receipt over the Versa network.

## Setup

1. Install Java, and the JDK for Java 21
2. Run `./gradlew tasks`
3. Run `./gradlew bootRun`

## Usage
Below you'll find instructions for testing this sending client against the Versa sandbox. With production credentials, you could deploy this service on your own stack to forward receipts through the Versa network.

### Configure the application by setting the following environment variables:
```sh
# application.properties
versa.registry.url=https://registry.versa.org
versa.client.client_id=YOUR_VERSA_CLIENT_ID
versa.client.client_secret=YOUR_VERSA_CLIENT_SECRET
```

### Sending a receipt to the firehose

You can send a receipt to the sandbox firehose by using the `sandbox-firehose.versa.org` domain with valid test credentials. Post a request to the `/send` endpoint with a JSON request body. Note that this service currently expects the `receipt_json` to be a pre-stringified JSON object. For more, see the [sending docs](https://docs.versa.org/sending). Example below:

```json
{
    "schema_version": "1.11.0",
    "transaction_handles": {
        "customer_email": "jshmoe@sandbox-firehose.versa.org"
    },
    "receipt_json": "{\"footer\":{\"actions\":[]},\"header\":{\"currency\":\"usd\",\"customer\":null,\"invoice_asset_id\":null,\"invoice_number\":\"auth_1MzFN1K8F4fqH0lBmFq8CjbU\",\"invoiced_at\":1713295619,\"location\":{\"address\":{\"tz\":\"America/New_York\"},\"google_place_id\":null,\"image\":null,\"name\":null,\"phone\":null,\"url\":null},\"mcc\":null,\"paid\":8069,\"receipt_asset_id\":null,\"subtotal\":7420,\"third_party\":null,\"total\":8069},\"itemization\":{\"car_rental\":null,\"ecommerce\":null,\"flight\":null,\"general\":null,\"lodging\":null,\"subscription\":{\"invoice_level_adjustments\":[],\"subscription_items\":[{\"adjustments\":[],\"amount\":7420,\"current_period_end\":1682288167,\"current_period_start\":1679609767,\"description\":\"Starter monthly plan\",\"interval\":\"month\",\"interval_count\":1,\"metadata\":[],\"quantity\":10,\"subscription_type\":\"recurring\",\"taxes\":[{\"amount\":649,\"name\":\"Sales Tax\",\"rate\":0.0875}],\"unit_cost\":742}]}},\"transit_route\":null},\"payments\":[{\"ach_payment\":null,\"amount\":8069,\"card_payment\":{\"last_four\":\"7806\",\"network\":\"mastercard\"},\"paid_at\":1713295619,\"payment_type\":\"card\"}],\"schema_version\":\"1.11.0\"}"
}
```
