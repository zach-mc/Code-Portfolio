package com.example.javagui.datamodel;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomerData {

    private static CustomerData instance = new CustomerData();
    private static String appFile = "src/main/app.txt";
    private static String musicFile = "src/main/music.txt";
    private List<Customer> customerItems;

    public static CustomerData getInstance() {
        return instance;
    }

    public List<Customer> getCustomerItems() {
        return customerItems;
    }

    public void setCustomerItems(ArrayList<Customer> customerList) {
        this.customerItems = customerList;
    }

    public void loadCustomerItems() throws IOException {
        Path path = Paths.get(appFile);
        BufferedReader br = Files.newBufferedReader(path);

        String input;

        try{
            while((input = br.readLine()) != null){
                String[] itemPieces = input.split(",");

                String name = itemPieces[0];
                String street = itemPieces[1];
                String city = itemPieces[2];
                String state = itemPieces[3];
                String zip = itemPieces[4];
                String type = itemPieces[5];
                String subtype = itemPieces[6];
                String title = itemPieces[7];
                String date = itemPieces[8];
                String accNumber = itemPieces[9];

                Customer customer = new Customer(name, street, city, state, zip, type,
                        subtype, title, date, accNumber);
            }

        } finally {
            if(br != null) {
                br.close();
            }
        }

    }

    public void storeAppCustomerItems() throws IOException{

        FileWriter fw = new FileWriter(appFile, true);

        try{
            Iterator<Customer> iter = customerItems.iterator();
            while(iter.hasNext()){
                Customer customer = iter.next();
                fw.append(String.format("%s, %s, %s, %s, %s, %s, %s, %s, %s, %s\n",
                        customer.getName(), customer.getStreet(), customer.getCity(), customer.getState(),
                        customer.getZip(), customer.getType(), customer.getSubtype(), customer.getTitle(),
                        customer.getDate(), customer.getAccNumber()));
            }

        } finally {
            if (fw != null){
                fw.close();
            }
        }
    }

    public void storeMusicCustomerItems() throws IOException{

        FileWriter fw = new FileWriter(musicFile, true);

        try{
            Iterator<Customer> iter = customerItems.iterator();
            while(iter.hasNext()){
                Customer customer = iter.next();
                fw.append(String.format("%s, %s, %s, %s, %s, %s, %s, %s, %s, %s\n",
                        customer.getName(), customer.getStreet(), customer.getCity(), customer.getState(),
                        customer.getZip(), customer.getType(), customer.getSubtype(), customer.getTitle(),
                        customer.getDate(), customer.getAccNumber()));
            }

        } finally {
            if (fw != null){
                fw.close();
            }
        }
    }
}
