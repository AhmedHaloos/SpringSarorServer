package com.saror.sarorserver;

import com.saror.sarorserver.security.Country;

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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/country")
public class CountryController {

    @GetMapping("/{countryName}")
    public Country getCountry(@PathVariable String countryName){
        return SpringServerApplication.mongoTemplateServer.findOne(
                new Query(
                        Criteria.where("countryName").is(countryName.toLowerCase())
                ),
                Country.class
        );
    }
    @GetMapping(value = {"/all", "/"})
    public List<Country> getCountries() {

        return SpringServerApplication.mongoTemplateServer.find(new Query(), Country.class);
    }

    @PostMapping
    public Country addCountry(@RequestBody Country country) {
        return SpringServerApplication.mongoTemplateServer.insert(country);
    }

    @PostMapping("all")
    public List<Country> addCountries(@RequestBody List<Country> countries) {

        List<Country> result = (List) SpringServerApplication.mongoTemplateServer.insertAll(countries);
        return result;
    }

    @PutMapping("/{countryName}")
    Country editCountry(@PathVariable String countryName, @RequestBody Country updatedCountry) {
        Query query = new Query();
        query.addCriteria(
                Criteria.where("countryName").is(countryName.toLowerCase()));
        Update u = new Update();
        u.set("countryName", updatedCountry.getCountryName().toLowerCase());
        u.set("countryCode", updatedCountry.getCountryCode().toLowerCase());
        u.set("countrySuffix", updatedCountry.getCountrySuffix());
        return SpringServerApplication.mongoTemplateServer.findAndModify(query, u, Country.class);
    }

    @DeleteMapping("/{countryName}")
    public Country deleteCountry(@PathVariable String countryName) {
        try {
            return SpringServerApplication.mongoTemplateServer.findAndRemove(
                    new Query(
                            Criteria.where("countryName").is(countryName.toLowerCase())
                    ),
                    Country.class
            );
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }
    @DeleteMapping("/all")
    public List<Country> deleteAllCountry() {
        return SpringServerApplication.mongoTemplateServer.findAllAndRemove(
                new Query(),
                Country.class
        );
    }


}
