package edu.neu.universityeventmanagementsystem.system.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Spring Application Configuration class
 *
 * @author Raghavan Renganathan <{renganathan.r@husky.neu.edu}>
 * @version 1.0
 * @since 4/6/18
 */
@Configuration
@ComponentScan(basePackages = "edu.neu.universityeventmanagementsystem.business")
@EnableJpaRepositories(basePackages = "edu.neu.universityeventmanagementsystem.business")
public class ApplicationConfiguration {
}
