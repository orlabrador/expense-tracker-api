package dev.orlabrador.expense_tracker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import dev.orlabrador.expense_tracker.auth.AuthenticationService;
import dev.orlabrador.expense_tracker.auth.RegisterRequest;

import static dev.orlabrador.expense_tracker.user.Role.ADMIN;
import static dev.orlabrador.expense_tracker.user.Role.MANAGER;

@SpringBootApplication
public class ExpenseTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseTrackerApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner (
			AuthenticationService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("admin@mail.com")
					.password("password")
					.role(ADMIN)
					.build();
				System.out.println("Admin token: " + service.register(admin));

			var manager = RegisterRequest.builder()
					.firstname("Manager")
					.lastname("Manager")
					.email("manager@mail.com")
					.password("password")
					.role(MANAGER)
					.build();
				System.out.println("Manager token: " + service.register(manager));
		};
	}
}
