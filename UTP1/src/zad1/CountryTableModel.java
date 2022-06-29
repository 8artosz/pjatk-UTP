package zad1;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CountryTableModel extends AbstractTableModel{
    private String[] columnNames = {" ", " ", " "," "};
    private List<Country> myList;
    String Filepath;
    private CountryList mylist;
    private CountryList names;
    private int rowIndex;
    private int columnIndex;
    String countriesFilename;
    public CountryTableModel (CountryList list){
        myList=list.getCountries();
        this.columnNames[0]=list.columnNames[0];
        this.columnNames[1]=list.columnNames[1];
        this.columnNames[2]=list.columnNames[2];
        this.columnNames[3]=list.columnNames[3];
    }
    @Override
    public int getRowCount() {
        int size;
        if (myList==null){
            size=0;
        }
        else size=myList.size();
        return size;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }
    @Override
    public Object getValueAt(int row, int col) {
        Object temp = null;
        if (col == 0) {
            temp = myList.get(row).getName();
        }
        else if (col == 1) {
            temp = myList.get(row).getCapital();
        }
        else if (col == 2) {
            temp = new Double(myList.get(row).getPopulation());
        }
        else if (col == 3){
            temp=myList.get(row).getIcon();
        }
        return temp;
    }
    @Override
    public Class <?> getColumnClass(int col) {
        if (col == 2) {
            return Double.class;
        }
        else if (col == 3){
            return ImageIcon.class;
        }
        else {
            return String.class;
        }
}}
