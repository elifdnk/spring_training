package com.cydeo;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Spring19RestOpenApi3Application {

    public static void main(String[] args) {
        SpringApplication.run(Spring19RestOpenApi3Application.class, args);
    }

    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI().info(new Info() //modify the info
                .title("Cydeo Application OpenAPI") //change the info title
                .version("v1") //change info version
                .description("Cydeo application API documentation"))// change info description
                .servers(List.of(new Server().url("https://dev.cydeo.com").description("Dev Environment"))); //change server server and description
        //we didnt add path.  path came from controller
    }

}
