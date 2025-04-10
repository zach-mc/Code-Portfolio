package com.example.javagui.datamodel;

public class Customer {

    private String name;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String type;
    private String subtype;
    private String title;
    private String date;
    private String accNumber;

    public Customer(String name, String street, String city, String state, String zip,
                    String type, String subtype, String title, String date, String accNumber) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.type = type;
        this.subtype = subtype;
        this.title = title;
        this.date = date;
        this.accNumber = accNumber;
    }

    public Customer(){

    }



    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getType() {
        return type;
    }

    public String getSubtype() {
        return subtype;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getAccNumber() {
        return accNumber;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

}
