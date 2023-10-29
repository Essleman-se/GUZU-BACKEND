package com.alltrips_transportion;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.alltrips_transportion.security.auth.AuthenticationService;
import com.alltrips_transportion.security.auth.RegisterRequest;

import static com.alltrips_transportion.security.user.Role.ADMIN;
import static com.alltrips_transportion.security.user.Role.MANAGER;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	//Test commit !!!
	
//	@Bean
//	public CommandLineRunner commandLineRunner(
//			AuthenticationService service
//	) {
//		return args -> {
//			var admin = RegisterRequest.builder()
//					.firstname("Admin")
//					.lastname("Admin")
//					.email("admin@mail.com")
//					.password("password")
//					.role(ADMIN)
//					.build();
//			System.out.println("Admin token: " + service.register(admin).getAccessToken());
//
//			var manager = RegisterRequest.builder()
//					.firstname("Admin")
//					.lastname("Admin")
//					.email("manager@mail.com")
//					.password("password")
//					.role(MANAGER)
//					.build();
//			System.out.println("Manager token: " + service.register(manager).getAccessToken());
//
//		};
//	}

}
