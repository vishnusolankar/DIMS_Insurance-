package com.dims;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DimsApplication {

	public static void main(String[] args) {

		SpringApplication.run(DimsApplication.class, args);

        System.out.println("BAAS IT JINDABAD");
	}

	@Bean
	public ModelMapper modelMapper (){


	return  new ModelMapper();
	}

}
