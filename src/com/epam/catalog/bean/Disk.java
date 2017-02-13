package com.epam.catalog.bean;

import java.util.Scanner;

public class Disk {

    static Scanner in = new Scanner(System.in);
    private int id;
    private String type;
    private String name;
    private Integer year;
    private Double price;

    public Disk(String type, String name, Integer year, Double price) {
        this.type = type;
        this.name = name;
        this.year = year;
        this.price = price;
    }

    public Disk() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Disk disk = (Disk) o;

        if (getType() != null ? !getType().equals(disk.getType()) : disk.getType() != null) return false;
        if (getName() != null ? !getName().equals(disk.getName()) : disk.getName() != null) return false;
        if (getYear() != null ? !getYear().equals(disk.getYear()) : disk.getYear() != null) return false;
        return getPrice() != null ? getPrice().equals(disk.getPrice()) : disk.getPrice() == null;

    }

    @Override
    public int hashCode() {
        int result = getType() != null ? getType().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getYear() != null ? getYear().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Disk{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", price=" + price +
                '}';
    }

    public Disk makeDisk() {

        System.out.println("Input Disk's type");
        if (in.hasNextLine()) {
            this.type = in.nextLine();
        }
        System.out.println("Input Disk's name");
        if (in.hasNextLine()) {
            this.name = in.nextLine();
        }

        System.out.println("Input Disk's year (Integer)");

        this.year = inputInt();
        System.out.println("Input Disks' price(Double)");


        this.price = inputDouble();

        return new Disk(type, name, year, price);

    }

    Double inputDouble() {
        Double x;
        while (!in.hasNextDouble()) {
            System.out.println("You input not an double number");
            System.out.println("Input an double number");

            in = new Scanner(System.in);
        }
        x = in.nextDouble();
        return x;
    }

    int inputInt() {
        int x;

        while (!in.hasNextInt()) {
            System.out.println("You input not an integer number");
            System.out.println("Input an integer number");

            in = new Scanner(System.in);
        }
        x = in.nextInt();
        return x;
    }
}