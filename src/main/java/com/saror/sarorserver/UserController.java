package com.saror.sarorserver;

import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
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
    public List<User> getUsers() {
        Query query = new Query();
        List<User> users = SpringServerApplication.mongoTemplateServer.find(query, User.class);
        System.out.println("**************************************************************");
        System.out.println(users.size());
        return users;
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        User res = SpringServerApplication.mongoTemplateServer.insert(user);
        return res;
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable long id, @RequestBody User user) {

        Query query = new Query(Criteria.where("_id").is(Long.valueOf(id)));
        Update update = new Update();
        update.set("name", user.getName())
                .set("phone", user.getPhone())
                .set("email", user.getEmail())
                .set("password", user.getPassword())
                .set("country", user.getCountry())
                .set("state", user.getState())
                .set("location", user.getLocation())
                .set("nationalId", user.getNationalId());

        return SpringServerApplication.mongoTemplateServer.findAndModify(query, update, User.class);
    }

    @DeleteMapping("{id}")
    public User deleteUser(@PathVariable long id) {
        Query query = new Query(Criteria.where("_id").is(Long.valueOf(id)));
        return SpringServerApplication.mongoTemplateServer.findAndRemove(query, User.class);
    }

    @GetMapping("/contact")
    public User getContactData(@RequestParam String phone){

        String customQuery = "{'phone' : {$regex : /"+phone+"/}}";
        Criteria criteria = Criteria.where("phone").is(phone);
        BasicQuery basicQuery = new BasicQuery(customQuery);
		Query query = new Query(criteria);
        System.out.println("first character = "+( phone.charAt(0) == '+'));
		List<User> users = SpringServerApplication.mongoTemplateServer.
                find(phone.charAt(0) == '+'?query :  basicQuery, User.class);
        System.out.println("result users : "+users.size());
		return users.size() > 0 ? users.get(0) : null;
	}
    @GetMapping("/login")
    public User login(@RequestParam String phone, @RequestParam String password) {

        System.out.print("phone : "+ phone +" password : " +password );
        Criteria criteria = Criteria.where("phone").is(phone).and("password").is(password);
        Query query = new Query(criteria);
        List<User> users = SpringServerApplication.mongoTemplateServer.find(query, User.class);
        return users.size() > 0 ? users.get(0) : null;
    }

}
