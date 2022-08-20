package cz.ankach.cms.configuration;

import cz.ankach.cms.entity.*;
import cz.ankach.cms.repository.ArticleRepository;
import cz.ankach.cms.repository.CommentRepository;
import cz.ankach.cms.repository.RoleRepository;
import cz.ankach.cms.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(
            UserRepository userRepository,
            RoleRepository roleRepository,
            ArticleRepository articleRepository,
            CommentRepository commentRepository
    ) {
        return args -> {
//            Role admin = new Role("admin");
//            Role moderator = new Role("moderator");
//            Role author = new Role("author");
//            Role reader = new Role("reader");
//            roleRepository.save(admin);
//            roleRepository.save(moderator);
//            roleRepository.save(author);
//            roleRepository.save(reader);
//
//
//            User adminUser  = new User("admin", "admin", "admin");
//            User moderatorUser  = new User("Andrii", "Plyskach", "ankach");
//
//            adminUser.addRole(new UserRole(adminUser, admin));
//            adminUser.addRole(new UserRole(adminUser, author));
//            adminUser.addRole(new UserRole(adminUser, reader));
//            moderatorUser.addRole(new UserRole(moderatorUser, moderator));
//
//
//            userRepository.saveAll(List.of(adminUser, moderatorUser));
//
//            Article article = new Article("Test article", "Lorem ipsum te st test test t t t dfdf tpaksdf test", moderatorUser);
//            articleRepository.save(article);
//
//            var comment1 = new Comment(article, "test", moderatorUser, null);
//            var comment2 = new Comment(article, "test2", moderatorUser, null);
//            commentRepository.saveAll(List.of(comment1, comment2));
//            var comment3 = new Comment(article, "test2", moderatorUser, comment2);
//            commentRepository.save(comment3);
        };
    }
}
