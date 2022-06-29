package zad1;

import javax.swing.*;

public class Country {
    private String name;
    private String capital;
    private double population;
    private ImageIcon icon;

    public Country(String name, String capital, double population, ImageIcon icon){
       this.name=name;
       this.capital=capital;
       this.population=population;
       this.icon=icon;
    }

    public String getName(){
        return this.name;
    }
    public String getCapital(){
        return this.capital;
    }
    public double getPopulation(){
        return this.population;
    }
    public ImageIcon getIcon(){ return this.icon;}
}