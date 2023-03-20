package com.example.todolistmvc.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan("com.example.todolistmvc.core.entity")
@EnableJpaRepositories("com.example.todolistmvc.core.repository")
@EnableTransactionManagement
public class JpaConfig {
}
