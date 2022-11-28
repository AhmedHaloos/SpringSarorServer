package com.saror.sarorserver;


import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
		public List<User> getUsers(){
			Query query = new Query();
			List<User> users = SpringServerApplication.mongoTemplateServer.find(query, User.class);
			System.out.println("**************************************************************");
			System.out.println(users.size());
			return users;
		}
		@PostMapping
		public User addUser( @RequestBody User user){
//			User user = new User(phone, email, password, name, location, nationalId, country, state);
			User res = SpringServerApplication.mongoTemplateServer.insert(user);
			return res;
		}
		@PutMapping("/{id}")
	public User updateUser( @PathVariable long id, @RequestBody User  user){

		Query query = new Query(Criteria.where("_id").is(Long.valueOf(id)));
			Update update  = new Update();
			  update.set("name",user.getName() )
				    .set("phone", user.getPhone() )
					.set("email", user.getEmail())
					.set("password", user.getPassword())
					.set("country", user.getCountry())
					.set("state", user.getState())
					.set("location" , user.getLocation())
					.set("nationalId", user.getNationalId());

		   return  SpringServerApplication.mongoTemplateServer.findAndModify(query,update , User.class);
		}
		@DeleteMapping("{id}")
	public User deleteUser(@PathVariable long id) {
			Query query = new Query(Criteria.where("_id").is(Long.valueOf(id)));
		return SpringServerApplication.mongoTemplateServer.findAndRemove(query, User.class);
		}
		@GetMapping("/login")
	public User login(@RequestParam String phone, @RequestParam String password){

		Criteria criteria = Criteria.where("phone").is(phone).and("password").is(password);
		Query query = new Query(criteria);
		List<User> users =  SpringServerApplication.mongoTemplateServer.find( query, User.class);
		return users.size() > 0 ? users.get(0): null;
		}

		@RequestMapping("/test")
	public String testMsg(){return "test";}
	@RequestMapping("/tst")
	public String tstMsg(){return "tst";}
}
