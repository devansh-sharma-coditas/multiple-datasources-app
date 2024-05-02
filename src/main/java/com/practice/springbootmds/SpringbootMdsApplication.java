package com.practice.springbootmds;

import com.practice.springbootmds.entities.postgreSQL.Order;
import com.practice.springbootmds.entities.mySQL.Product;
import com.practice.springbootmds.repository.postgreSQL.OrderRepository;
import com.practice.springbootmds.repository.mySQL.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootMdsApplication implements CommandLineRunner {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private OrderRepository orderRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMdsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Product product = new Product(1105 , "PRODUCT-5" , 1000);
		productRepository.save(product); // mySQL
		System.out.println("*************** saving product in mySQL *******************");

		Order order = new Order();
		order.setId(11111);
		order.setName("Order-AA");
		orderRepository.save(order); // postgresQL
		System.out.println("*************** saving order in postgresSQL *******************");

	}
}
