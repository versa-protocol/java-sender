package org.versa.sender;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;


@RestController
public class ServiceInfoController {
	@Value("${spring.application.name}")
	private String applicationName;
	@Value("${versa.sender.version}")
	private String versaSenderVersion;
	@Value("${versa.registry.url}")
	private String versaRegistryUrl;

	@GetMapping("/")
	public String index() {
		return "<div>Hello from the " + applicationName + "@" + versaSenderVersion + " service.</div><div>The registry url is " + versaRegistryUrl +"</div>";
	}

	@GetMapping("/registry")
	public String registry() {
		return checkRegistry();
	}

	@PostMapping("/send") 
	public String send(@RequestBody Receipt newReceipt) { 
		// TODO: accept transaction handles as well and regist the receipt
		return newReceipt.getInvoiceNumber();
	}
	@PostMapping("/encrypt") 
	public String encrypt(@RequestBody EncryptionTestPayload payload) { 
		// TODO: accept transaction handles as well and regist the receipt
		return Base64.getEncoder().encodeToString(Protocol.encrypt(payload.get_key(), payload.get_nonce(), payload.get_receipt()));
	}

	private String checkRegistry()
	{
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(versaRegistryUrl, String.class);

		return result;
	}
}
