package com.dna.rna.config;

import com.dna.rna.domain.SchoolUser.SchoolUserRepositoryImpl;
import com.dna.rna.domain.User.User;
import com.dna.rna.domain.User.UserRepository;
import com.dna.rna.domain.User.UserRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration("AuthGateway")
@AllArgsConstructor
public class AuthGateway {

    private final UserRepository userRepository;
    private final SchoolUserRepositoryImpl schoolUserRepository;

    private boolean schoolUserImmigration(String URI) {
        User user = userRepository.findUserByLoginId("4whomtbts");
        System.out.println(user.getId());
        if (user != null) {
            return true;
        }
        return false;
    }

    public boolean immigration (String URI) {
        if (URI.startsWith("/schools")) {
            return schoolUserImmigration(URI);
        }
        return true;
    }
}
