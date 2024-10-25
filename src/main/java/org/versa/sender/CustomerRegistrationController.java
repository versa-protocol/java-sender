package org.versa.sender;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class CustomerRegistrationController {
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

  public CustomerRegistrationController(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @PostMapping("/handle")
  public ResponseEntity<String> register_customer_reference(@RequestBody CustomerRegistrationRequest payload) {

    if (payload.handle == null) {
      return new ResponseEntity<>("Missing handle", HttpStatus.BAD_REQUEST);
    }
    if (payload.handle_type == null) {
      return new ResponseEntity<>("Missing handle_type", HttpStatus.BAD_REQUEST);
    }
    if (payload.receiver_client_id == null) {
      return new ResponseEntity<>("Missing receiver_client_id", HttpStatus.BAD_REQUEST);
    }
    if (payload.handle_type != "customer_email" && payload.handle_type != "customer_email_domain") {
      return new ResponseEntity<>("Invalid handle_type", HttpStatus.BAD_REQUEST);
    }

    // make post request to registry
    RestTemplate restTemplate = new RestTemplate();

    // Make the HTTP POST request
    String url = versaRegistryUrl + "/handle";

    String credential = "Basic " + versaClientId + ":" + versaClientSecret;

    // Set headers
    HttpHeaders headers = new HttpHeaders();
    headers.set("Accept", "application/json");
    headers.set("Authorization", credential);
    headers.set("Content-Type", "application/json");

    // Create the HTTP entity
    HttpEntity<CustomerRegistrationRequest> entity = new HttpEntity<>(payload, headers);

    try {
      // Make the HTTP POST request
      ResponseEntity<String> response = restTemplate.exchange(
          url,
          HttpMethod.POST,
          entity,
          String.class);
    } catch (Exception e) {
      // Log the error (replace kirk! with a logger)
      System.out.println("Error during customer registration: " + e.getMessage());
      return new ResponseEntity<String>("Error during customer registration", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<String>("Accepted", HttpStatus.ACCEPTED);
  }

  @DeleteMapping("/handle")
  public ResponseEntity<String> deregister_customer_reference(@RequestBody CustomerRegistrationRequest payload) {

    if (payload.handle == null) {
      return new ResponseEntity<>("Missing handle", HttpStatus.BAD_REQUEST);
    }
    if (payload.handle_type == null) {
      return new ResponseEntity<>("Missing handle_type", HttpStatus.BAD_REQUEST);
    }
    if (payload.receiver_client_id == null) {
      return new ResponseEntity<>("Missing receiver_client_id", HttpStatus.BAD_REQUEST);
    }
    if (payload.handle_type != "customer_email" && payload.handle_type != "customer_email_domain") {
      return new ResponseEntity<>("Invalid handle_type", HttpStatus.BAD_REQUEST);
    }

    // make post request to registry
    RestTemplate restTemplate = new RestTemplate();

    // Make the HTTP POST request
    String url = versaRegistryUrl + "/handle";

    String credential = "Basic " + versaClientId + ":" + versaClientSecret;

    // Set headers
    HttpHeaders headers = new HttpHeaders();
    headers.set("Accept", "application/json");
    headers.set("Authorization", credential);
    headers.set("Content-Type", "application/json");

    // Create the HTTP entity
    HttpEntity<CustomerRegistrationRequest> entity = new HttpEntity<>(payload, headers);

    try {
      // Make the HTTP DELETE request
      ResponseEntity<String> response = restTemplate.exchange(
          url,
          HttpMethod.DELETE,
          entity,
          String.class);
    } catch (Exception e) {
      // Log the error (replace kirk! with a logger)
      System.out.println("Error during customer registration: " + e.getMessage());
      return new ResponseEntity<String>("Error during customer registration", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<String>("Accepted", HttpStatus.ACCEPTED);
  }
}
