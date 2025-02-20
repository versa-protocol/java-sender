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
    "schema_version": "1.9.0",
    "transaction_handles": {
        "customer_email": "jshmoe@sandbox-firehose.versa.org"
    },
    "receipt_json": "{  \"schema_version\": \"1.9.0\",  \"header\": {    \"invoice_number\": \"auth_1MzFN1K8F4fqH0lBmFq8CjbU\",    \"currency\": \"usd\",    \"total\": 8069,    \"subtotal\": 7420,    \"paid\": 8069,    \"invoiced_at\": 1713295619,    \"mcc\": null,    \"third_party\": null,    \"customer\": null,    \"location\": null,    \"invoice_asset_id\": null,    \"receipt_asset_id\": null  },  \"itemization\": {    \"general\": null,    \"lodging\": null,    \"ecommerce\": null,    \"car_rental\": null,    \"transit_route\": null,    \"subscription\": {      \"subscription_items\": [        {          \"subscription_type\": \"recurring\",          \"description\": \"Starter monthly plan\",          \"amount\": 7420,          \"interval\": \"month\",          \"interval_count\": 1,          \"current_period_start\": 1679609767,          \"current_period_end\": 1682288167,          \"quantity\": 10,          \"unit_cost\": 742,          \"taxes\": [            {              \"amount\": 649,              \"rate\": 0.0875,              \"name\": \"Sales Tax\"            }          ],          \"metadata\": [],          \"adjustments\": []        }      ],      \"invoice_level_adjustments\": []    },    \"flight\": null  },  \"footer\": {    \"actions\": [      {        \"name\": \"Manage Subscription\",        \"url\": \"https:\/\/versa.org\/\"      },      {        \"name\": \"Contact Support\",        \"url\": \"https:\/\/versa.org\/\"      }    ]  },  \"payments\": []}"
}
```
