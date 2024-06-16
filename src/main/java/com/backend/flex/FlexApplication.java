package com.backend.flex;

import com.backend.flex.security.startup.AdminLoad;
import com.backend.flex.security.startup.RoleLoad;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class FlexApplication {

    public static void main(String[] args) {
		SpringApplication.run(FlexApplication.class, args);
	}

	@Bean
	public RoleLoad roleLoad(){
		return new RoleLoad();
	}

	@Bean
	public AdminLoad adminLoad(){
		return new AdminLoad();
	}
}
