package org.versa.sender;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

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

}
