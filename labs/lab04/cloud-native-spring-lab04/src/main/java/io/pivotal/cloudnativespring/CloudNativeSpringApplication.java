package io.pivotal.cloudnativespring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
// Add these imports:
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@SpringBootApplication
@RestController
@EnableJpaRepositories // <---- And this
@Import(RepositoryRestMvcConfiguration.class) // <---- And this
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class }) // To Bypass Security in the Demo Application

public class CloudNativeSpringApplication {

	@Value("${greeting:Hola}")
	private String greeting;

	public static void main(String[] args) {
		SpringApplication.run(CloudNativeSpringApplication.class, args);
	}

	@RequestMapping("/")
	public String hello() {
		return this.greeting + " World!";
	}

	@RequestMapping("/home")
	public String hellohome() {
		return "Hello World - Home!";
	}
}
