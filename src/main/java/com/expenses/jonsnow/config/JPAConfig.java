package com.expenses.jonsnow.config;

import com.expenses.jonsnow.model.AuditorAwareImpl;
import com.expenses.jonsnow.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
public class JPAConfig {

    @Bean
    public AuditorAware<User> auditorAware(){
        return new AuditorAwareImpl();
    }
}
