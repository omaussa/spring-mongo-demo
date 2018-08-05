package com.omaussa.mongodemo.config;


import com.omaussa.mongodemo.document.User;
import com.omaussa.mongodemo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = UserRepository.class)
@Configuration
public class MongoDBConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        if (userRepository.count() == 0) {
            return args -> {
                userRepository.save(new User("1", "Peter", "Development", 2000L));
                userRepository.save(new User("2", "Sarah", "Sales", 3000L));
            };
        } else {
            return args -> {};
        }
    }

}
