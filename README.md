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
    "schema_version": "2.0.0",
    "transaction_handles": {
        "customer_email": "jshmoe@sandbox-firehose.versa.org"
    },
    "receipt_json": "{\"schema_version\":\"2.0.0\",\"header\":{\"currency\":\"usd\",\"customer\":null,\"invoice_asset_id\":null,\"invoice_number\":null,\"invoiced_at\":1734451654,\"location\":{\"address\":{\"city\":null,\"country\":null,\"lat\":null,\"lon\":null,\"postal_code\":null,\"region\":null,\"street_address\":null,\"tz\":\"America/Chicago\"},\"google_place_id\":null,\"image\":null,\"name\":null,\"phone\":null,\"url\":null},\"mcc\":null,\"paid\":52295,\"receipt_asset_id\":null,\"subtotal\":45837,\"third_party\":null,\"total\":52295},\"itemization\":{\"car_rental\":null,\"ecommerce\":null,\"flight\":{\"itinerary_locator\":null,\"tickets\":[{\"fare\":45837,\"metadata\":null,\"number\":\"0012170813359\",\"passenger\":{\"first_name\":\"John\",\"last_name\":\"Smith\"},\"record_locator\":\"FMKREO\",\"segments\":[{\"adjustments\":null,\"aircraft_type\":null,\"arrival_airport_code\":\"CLT\",\"arrival_at\":1733954760,\"arrival_tz\":null,\"class_of_service\":\"M\",\"departure_airport_code\":\"STL\",\"departure_at\":1733948100,\"departure_tz\":null,\"fare\":null,\"flight_number\":\"AA2486\",\"seat\":null},{\"aircraft_type\":null,\"arrival_airport_code\":\"PNS\",\"arrival_at\":1733966160,\"arrival_tz\":null,\"class_of_service\":\"M\",\"departure_airport_code\":\"CLT\",\"departure_at\":1733959140,\"departure_tz\":null,\"fare\":null,\"flight_number\":\"AA5429\",\"seat\":null}],\"taxes\":[{\"amount\":6458,\"name\":\"Taxes & Fees\",\"rate\":null}]}]},\"general\":null,\"lodging\":null,\"subscription\":null,\"transit_route\":null},\"payments\":[{\"ach_payment\":null,\"amount\":52295,\"card_payment\":{\"last_four\":\"1234\",\"network\":\"visa\"},\"paid_at\":1734451654,\"payment_type\":\"card\"}],\"footer\":{}}"
}
```
