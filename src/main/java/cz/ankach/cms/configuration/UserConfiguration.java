package cz.ankach.cms.configuration;

import cz.ankach.cms.entity.User;
import cz.ankach.cms.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository) {
        return args -> {
            User user1  = new User("user1", "last name", "user1");
            User user2  = new User("user2", "last name", "user2");

            repository.saveAll(List.of(user1, user2));
        };
    }
}
