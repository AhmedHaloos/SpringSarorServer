package com.saror.sarorserver;

import com.mongodb.client.MongoClients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

@Component
class SecurityHandler implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		System.err.println(request.getHeader("access-token"));
		System.err.println(request.getReader());

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

	@Component
	class SecurityConfigurer extends WebMvcConfigurerAdapter{

		@Autowired
		SecurityHandler securityHandler;
		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(securityHandler);
		}
	}
}




