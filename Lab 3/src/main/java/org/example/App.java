package org.example;

import dao.ManufactureDAO;
import dao.PhoneDAO;
import entity.Manufacture;
import entity.Phone;


public class App {
    public static void main(String[] args) throws Exception {
        PhoneDAO phoneDAO = new PhoneDAO();
        ManufactureDAO manufactureDAO = new ManufactureDAO();


        Manufacture apple = new Manufacture("Apple", "US", 200);
        Manufacture samsung = new Manufacture("Samsung", "Korea", 200);
        Manufacture google = new Manufacture("Google", "California", 300);
        Manufacture onepluss = new Manufacture("OnePlus", "China", 20);
        Manufacture xiaomii = new Manufacture("Xiaomi", "China", 40);

        manufactureDAO.add(apple);
        manufactureDAO.add(samsung);
        manufactureDAO.add(google);
        manufactureDAO.add(onepluss);
        manufactureDAO.add(xiaomii);
        //manufactureDAO.remove(xiaomii);
//        manufactureDAO.removeById(1);
//        manufactureDAO.getById(2);
//        onepluss.setName("OnePlus 7Pro");
//        onepluss.setLocation("America");
//        manufactureDAO.update(onepluss);


        Phone iphone = new Phone("iPhone", "Red", 20000000, "US", 20, apple);
        Phone galaxy = new Phone("Samsung", "Pink", 18000000, "Korea", 18, samsung);
        Phone pixel = new Phone("Google Pixel", "Black", 15000000, "Korea", 15, google);
        Phone oneplus = new Phone("OnePlus", "Pink", 12000000, "China", 17, onepluss);
        Phone xiaomi = new Phone("Xiaomi", "White", 14000000, "China", 14, xiaomii);
        Phone xiaomi15 = new Phone("Xiaomi15", "White", 14000000, "China", 14, xiaomii);

        phoneDAO.add(iphone);
        phoneDAO.add(galaxy);
        phoneDAO.add(pixel);
        phoneDAO.add(oneplus);
        phoneDAO.add(xiaomi);
        phoneDAO.add(xiaomi15);
//        phoneDAO.getAll();
//        phoneDAO.remove(iphone);
//        phoneDAO.remove(2);
//        pixel.setColor("Aqua");
//        pixel.setQuantity(50);
//        phoneDAO.update(pixel);


        System.out.println("Highest price phone: " + phoneDAO.getPhoneWithHighestPrice().toString());
        System.out.println("Sorted phones: " + phoneDAO.getPhonesSortedByCountry().getClass().getName());
        System.out.println("Is there a phone above 50 million VND? " + phoneDAO.isPhoneAbove50M());
        System.out.println("Pink phone above 15 million: " + phoneDAO.getFirstPinkPhoneAbove15M());


        System.out.println("All manufactures more than 100 employees " + manufactureDAO.moreThan100Employees());
        System.out.println("Sum of all employees: " + manufactureDAO.getAllEmployees());
        // System.out.println("Last US manufacture: " + manufactureDAO.getLastUSManufacture().getName());

    }
}

