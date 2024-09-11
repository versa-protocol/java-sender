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
    "schema_version": "1.2.0",
    "transaction_handles": {
        "customer_email": "jshmoe@sandbox-firehose.versa.org"
    },
    "receipt_json": "{\n  \"schema_version\": \"1.2.0\",\n  \"header\": {\n    \"invoice_number\": \"auth_1MzFN1K8F4fqH0lBmFq8CjbU\",\n    \"currency\": \"usd\",\n    \"total\": 8069,\n    \"subtotal\": 7420,\n    \"paid\": 8069,\n    \"invoiced_at\": 1713295619,\n    \"mcc\": null,\n    \"third_party\": null,\n    \"customer\": null,\n    \"location\": null\n  },\n  \"itemization\": {\n    \"general\": null,\n    \"lodging\": null,\n    \"ecommerce\": null,\n    \"car_rental\": null,\n    \"transit_route\": null,\n    \"subscription\": {\n      \"subscription_items\": [\n        {\n          \"subscription_type\": \"recurring\",\n          \"description\": \"Starter monthly plan\",\n          \"subtotal\": 7420,\n          \"interval\": \"month\",\n          \"interval_count\": 1,\n          \"current_period_start\": 1679609767,\n          \"current_period_end\": 1682288167,\n          \"quantity\": 10,\n          \"unit_cost\": 742,\n          \"taxes\": [\n            {\n              \"amount\": 649,\n              \"rate\": 0.0875,\n              \"name\": \"Sales Tax\"\n            }\n          ],\n          \"metadata\": [],\n          \"adjustments\": []\n        }\n      ],\n      \"invoice_level_adjustments\": []\n    },\n    \"flight\": null\n  },\n  \"actions\": [\n    { \"name\": \"Manage Subscription\", \"url\": \"https://versa.org/\" },\n    { \"name\": \"Contact Support\", \"url\": \"https://versa.org/\" }\n  ],\n  \"payments\": null\n}"
}
```
