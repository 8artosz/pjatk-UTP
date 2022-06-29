package zad1;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CountryList {
    private List<Country> countrylist;
    public CountryList(){
        countrylist= new ArrayList<Country>();
    }
    public String [] columnNames ={"","","",""};
    public void add(Country co){
        countrylist.add(co);
    }
    public List<Country> getCountries(){
        return countrylist;
    }
    public void readFromtxt(String filename){
        File file = new File(filename);
        FileReader reader = null;
        try{
            reader = new FileReader(file);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        BufferedReader infile = new BufferedReader(reader);
        String line= "";
        try{
            boolean done = false;
            while(!done) {
                line = infile.readLine();
                if (line == null) {
                    done = true;
                }
                else {
                    String tokens[] = line.split("\t");
                    Boolean flag = Character.isDigit(tokens[2].charAt(0));
                    if(!flag && columnNames[0].equals("")){
                           columnNames[0]=tokens[0];
                           columnNames[1]=tokens[1];
                           columnNames[2]=tokens[2];
                           columnNames[3]=tokens[4];
                    }
                    else{
                        String name = tokens[0];
                        String capital = tokens[1];
                        double population = Double.parseDouble(tokens[2]);
                        String obraz = tokens[4];
                        ImageIcon icon = new ImageIcon(obraz);

                        Country sb = new Country(name, capital, population, icon);
                        countrylist.add(sb);
                    }
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
            System.exit(1);
        }
    }
}
