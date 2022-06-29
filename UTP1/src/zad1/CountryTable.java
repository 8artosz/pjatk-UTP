package zad1;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.text.NumberFormat;

public class CountryTable {
    private CountryTableModel tableModel;
    private JTable table;
    private CountryList myList;
    String countriesFilename;
    public CountryTable (String countriesFilename) {
        this.countriesFilename=countriesFilename;
    }
    public JTable create(){
        myList = new CountryList();
        myList.readFromtxt(countriesFilename);
       tableModel = new CountryTableModel(myList);
        table = new JTable(tableModel);
        table.setRowHeight(120);
        table.setDefaultRenderer(Double.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                setForeground((Double) value < 20000000 ? Color.BLACK : Color.RED);
                    value= NumberFormat.getNumberInstance().format(value);
                return super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
            }
        });

        table.setAutoCreateRowSorter(true);
        return table;
    }


}
