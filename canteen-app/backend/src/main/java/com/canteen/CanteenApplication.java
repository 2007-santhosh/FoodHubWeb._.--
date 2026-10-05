package com.canteen;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.canteen.model.Item;
import com.canteen.repository.ItemRepository;

@SpringBootApplication
public class CanteenApplication {
    public static void main(String[] args) {
        SpringApplication.run(CanteenApplication.class, args);
    }

    @Bean
    CommandLineRunner seed(ItemRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                repo.save(new Item(null, "Veg Sandwich",   "Grilled with cheese & veggies", 60.0, "🥪"));
                repo.save(new Item(null, "Masala Dosa",    "Crispy with potato filling",    80.0, "🥞"));
                repo.save(new Item(null, "Paneer Roll",    "Spicy paneer wrap",            100.0, "🌯"));
                repo.save(new Item(null, "Veg Burger",     "Loaded with fresh veggies",     90.0, "🍔"));
                repo.save(new Item(null, "Cold Coffee",    "Chilled & frothy",              50.0, "🥤"));
                repo.save(new Item(null, "French Fries",   "Crispy salted",                 70.0, "🍟"));
            }
        };
    }
}
