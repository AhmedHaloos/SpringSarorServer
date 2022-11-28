package com.saror.sarorserver.security;

public class Country {

    private String countryName ;
    private String countryCode ;
    private String countrySuffix;

//    public  Country(){}
    public Country(String countryName, String countryCode, String countrySuffix) {
        this.countryName = countryName.toLowerCase();
        this.countryCode = countryCode.toLowerCase();
        this.countrySuffix = countrySuffix;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName.toLowerCase();
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode.toLowerCase();
    }

    public String getCountrySuffix() {
        return countrySuffix;
    }

    public void setCountrySuffix(String countrySuffix) {
        this.countrySuffix = countrySuffix;
    }
}
