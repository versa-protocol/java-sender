package org.versa.sender;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class SendReceiptController {
	@Value("${spring.application.name}")
	private String applicationName;
	@Value("${versa.sender.version}")
	private String versaSenderVersion;
	@Value("${versa.registry.url}")
	private String versaRegistryUrl;
	@Value("${versa.client.client_id}")
	private String versaClientId;
	@Value("${versa.client.client_secret}")
	private String versaClientSecret;

    private final ObjectMapper objectMapper;

    public SendReceiptController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

	@PostMapping("/send") 
    public ResponseEntity<String> send(@RequestBody SendReceiptPayload payload) { 

		if (payload.schema_version == null) {
			return new ResponseEntity<>("Missing schema_version", HttpStatus.BAD_REQUEST);
		}
		if (payload.receipt_json == null) {            
			return new ResponseEntity<>("Missing receipt_json", HttpStatus.BAD_REQUEST);
		}

		ReceiptRegistrationRequest registrationPayload = new ReceiptRegistrationRequest();
		registrationPayload.schema_version = payload.schema_version;
		registrationPayload.handles = payload.transaction_handles;
		registrationPayload.transaction_id = payload.transaction_id;

		// make post request to registry
		RestTemplate restTemplate = new RestTemplate();

                // Make the HTTP POST request
                String url = versaRegistryUrl + "/register";

				String credential = "Basic " + versaClientId + ":" + versaClientSecret;

                // Set headers
                HttpHeaders headers = new HttpHeaders();
                headers.set("Accept", "application/json");
                headers.set("Authorization", credential);
                headers.set("Content-Type", "application/json");

                // Create the HTTP entity
                HttpEntity<ReceiptRegistrationRequest> entity = new HttpEntity<>(registrationPayload, headers);

			try {
                // Make the HTTP POST request
                ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    entity,
                    String.class
                );
                // Check for successful response status
                if (response.getStatusCode() == HttpStatus.OK) {
                    // Deserialize the response body into ReceiptRegistrationResponse
                    ReceiptRegistrationResponse data = objectMapper.readValue(
                        response.getBody(),
                        ReceiptRegistrationResponse.class
                    );
					// Log the success status
					System.out.println("Successfully registered receipt with registry: " + data.getReceiptId());
                } else {
                    // Log the failure status
                    System.out.println("Received error status from registry: " + response.getStatusCode());
					return new ResponseEntity<String>("Error during registration", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } catch (Exception e) {
                // Log the error (replace kirk! with a logger)
                System.out.println("Error during registration: " + e.getMessage());
				return new ResponseEntity<String>("Error during registration", HttpStatus.INTERNAL_SERVER_ERROR);
            }
			return new ResponseEntity<String>("Accepted", HttpStatus.ACCEPTED);
	}
}