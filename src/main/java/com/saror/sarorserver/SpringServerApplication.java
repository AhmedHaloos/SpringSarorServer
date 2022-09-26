package com.saror.sarorserver;

import com.mongodb.client.MongoClients;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringServerApplication {
	static final String  uri = "mongodb+srv://ahmed_db:20101993mongodb@e-commetc-cluster.7gfpk46.mongodb.net/?retryWrites=true&w=majority";
	public static MongoTemplate mongoTemplateServer = null;
	public static void main(String[] args) {
		SpringApplication.run(SpringServerApplication.class, args);
		mongoTemplateServer  = new  MongoTemplate(MongoClients.create(uri), "sarordb");
		String state = mongoTemplateServer != null ? "connected" : "not connected";
		System.out.println("*********************************************");
		System.out.println(state);
}

@Controller
@RequestMapping("/")
	class  DefaultRoutes{

		@GetMapping("/")
	public String getMainPage(){

			return "mainPage";
		}


}


}
