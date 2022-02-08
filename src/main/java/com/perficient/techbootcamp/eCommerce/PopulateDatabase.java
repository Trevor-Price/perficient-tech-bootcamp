package com.perficient.techbootcamp.eCommerce;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.perficient.techbootcamp.eCommerce.entity.Brand;
import com.perficient.techbootcamp.eCommerce.entity.Product;
import com.perficient.techbootcamp.eCommerce.repository.BrandRepository;
import com.perficient.techbootcamp.eCommerce.repository.ProductRepository;

@Configuration
public class PopulateDatabase {
	
	@Bean
	@SuppressWarnings("unused")
	CommandLineRunner initDatabase(ProductRepository productRepo, BrandRepository brandRepo) {
		return args -> {
			Brand tools = brandRepo.save(new Brand("Top Tools"));
			Brand sports = brandRepo.save(new Brand("Sports and Stuff"));
			Brand micro = brandRepo.save(new Brand("Microware Emporium"));
			Brand more = brandRepo.save(new Brand("Bed Bath and More"));
			Brand lucky = brandRepo.save(new Brand("Lucky LLC."));
			Brand nano = brandRepo.save(new Brand("Nanoware Nexus"));
			
			Product toaster = productRepo.save(new Product("Toaster", 96.99, 12, more));
			Product tennis = productRepo.save(new Product("Tennis Racket", 14.95, 57, sports));
			Product pan = productRepo.save(new Product("Frying Pan", 71.99, 32, more));
			Product watch = productRepo.save(new Product("Lucky Watch", 1023.95, 3, lucky));
			Product vacuum = productRepo.save(new Product("Vacuum", 233.99, 34, more));
			Product tv = productRepo.save(new Product("Smart TV", 1171.99, 7, nano));
			Product oven = productRepo.save(new Product("Microwave Oven 2000", 101.99, 21, micro));
			Product mwrench = productRepo.save(new Product("Micro Wrench", 23.95, 59, micro));
			Product nwrench = productRepo.save(new Product("Nano Wrench", 1023.95, 6, nano));
			Product bot = productRepo.save(new Product("Nano Bot", 2.95, 602812, nano));
			Product chip = productRepo.save(new Product("Microchip", 4.99, 1052, micro));
			Product jet = productRepo.save(new Product("Jetpack", 2999.99, 43, sports));
			Product wrench = productRepo.save(new Product("Wrench", 23.95, 59, tools));
	    };
	}
}
