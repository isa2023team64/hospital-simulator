package isa2023team64.hospitalsimulator.hospitalsimulator;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import org.apache.tomcat.util.descriptor.web.InjectionTarget;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import isa2023team64.hospitalsimulator.hospitalsimulator.model.Equipment;
import isa2023team64.hospitalsimulator.hospitalsimulator.model.Order;

@SpringBootApplication
public class HospitalSimulatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalSimulatorApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(RestTemplate restTemplate) {
		return args -> {
			Scanner scanner = new Scanner(System.in);

			int input = -1;
			
			while (input != 0) {
				System.out.println("Choose option:");
				System.out.println("	0. Exit program.");
				System.out.println("	1. Get all available equipment.");
				System.out.println("	2. Sign contract.");
				System.out.println("Your option: ");
				scanner = new Scanner(System.in);

				try {
					input = Integer.parseInt(scanner.nextLine());
					switch (input) {
						case 1:
							Collection<Equipment> allEquipment = getAllEquipment(restTemplate);
							for (var equipment : allEquipment) {
								System.out.println("	" + equipment.getId() + " - " + equipment.getName());
							}
							break;
						case 2:
							createContract(restTemplate, scanner);
							break;
						case 0:
							System.out.println("izlaz");
							break;
						default:
							System.out.println("Invalid option!");
							break;
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid input!");
				} catch (ResourceAccessException ex) {
					System.out.println("Backend not working.");
				}
				
			}

			System.out.println("izaso si");

			scanner.close();
	
		};
	}


	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	private Collection<Equipment> getAllEquipment(RestTemplate restTemplate) {
		ResponseEntity<Collection<Equipment>> responseEntity = restTemplate.exchange(
                "http://localhost:8080/api/equipment",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Collection<Equipment>>() {});

        Collection<Equipment> equipments = responseEntity.getBody();
		
		return equipments;
	}

	private void createContract(RestTemplate restTemplate, Scanner scanner) {
		Collection<Equipment> allEquipment = getAllEquipment(restTemplate);

		try {
			System.out.print("Enter your hospital ID: ");
			int hospitalId = Integer.parseInt(scanner.nextLine());

			System.out.println("Enter delivery day in month: ");
			int day = Integer.parseInt(scanner.nextLine());

			if (!(1 <= day && day <= 31)) {
				throw new NumberFormatException();
			}

			int input = -1;
			List<Order> orders = new ArrayList<>();
			while (input != 0) {
				System.out.println("Choose equipment (type 0 to exit):");
				input = Integer.parseInt(scanner.nextLine());
				switch (input) {
					case 0:
						break;
				
					default:
						final int equipmentId = input;
						var eq = allEquipment.stream().filter(e -> e.getId() == equipmentId).findFirst().orElse(null);
						if (eq == null) {
							System.out.println("Equipment with ID=" + equipmentId + " doesn't exist.");
							continue;
						}
						System.out.println("Enter quantity: ");
						int quantity = Integer.parseInt(scanner.nextLine());
						if (quantity < 1) {
							System.out.println("Quantity must be greater than 0!");
							continue;
						}
						Order order = new Order(eq.getId(), quantity);
						orders = new ArrayList<>(orders.stream()
												 .filter(o -> o.getEquipmentId() != eq.getId())
												 .toList());
						orders.add(order);
						break;
				}
			}

			if (orders.size() == 0) {
				System.out.println("Order empty!");
				return;
			}

			for (var order : orders) {
				System.out.println(order.getEquipmentId() + " " + order.getQuantity());
			}

		} catch (NumberFormatException e) {
			System.out.println("Invalid input!");
			return;
		}
	}
}
