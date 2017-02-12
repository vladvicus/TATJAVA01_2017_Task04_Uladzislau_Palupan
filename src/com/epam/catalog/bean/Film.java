package com.epam.catalog.bean;

import java.util.Scanner;

public class Film {
    static Scanner in = new Scanner(System.in);
    private int id;
    private String name;
    private String country;
    private Integer year;
    private Integer rating;


    public Film() {
        super();
    }

    public Film(String name, String country, Integer year, Integer rating) {
        super();
        this.name = name;
        this.country = country;
        this.year = year;
        this.rating = rating;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public Integer getYear() {

        return year;
    }

    public void setYear(Integer year)
    {
        this.year = year;
    }

    public Integer getRating()
    {
        return rating;
    }

    public void setRating(Integer rating)    {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Film makeFilm() {

        System.out.println("Input Film's name");
        if (in.hasNextLine()) {
            this.name = in.nextLine();
        }
        System.out.println("Input Film's country");
        if (in.hasNextLine()) {
            this.country = in.nextLine();
        }

        System.out.println("Input Film's year (Integer)");
        this.year = inputInt();

        System.out.println("Input Films' rating(Integer)");

        this.rating = inputInt();

        return new Film(name, country, year, rating);

    }

    int inputInt() {
        int x = 0;

        while (!in.hasNextInt()) {
            System.out.println("You input not an integer number");
            System.out.println("Input an integer number");

            in = new Scanner(System.in);
        }
        x = in.nextInt();
        return x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Film film = (Film) o;

        if (getId() != film.getId()) return false;
        if (!getName().equals(film.getName())) return false;
        if (!getCountry().equals(film.getCountry())) return false;
        if (!getYear().equals(film.getYear())) return false;
        return getRating().equals(film.getRating());

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getCountry().hashCode();
        result = 31 * result + getYear().hashCode();
        result = 31 * result + getRating().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                '}';
    }
}
