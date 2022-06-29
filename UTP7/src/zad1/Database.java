package zad1;

import java.sql.*;

class Database {

    private String url;
    private TravelData travelData;
    public static String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private Connection connection;
    private int id = 1;

    Database(String url, TravelData travelData) {
        this.travelData = travelData;
        this.url = url;
    }

    void create() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(url);
            if (connection != null) {
            }
        } catch (SQLException e) {
            System.exit(2);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(2);
        }

        try {
            Statement statementP = connection.createStatement();
            Statement statement = connection.createStatement();
            statementP.execute("DROP TABLE OFERTA");
            statement.execute("CREATE TABLE OFERTA ("
                    + "id int PRIMARY KEY, "
                    + "kraj varchar(20), "
                    + "data_wyjazdu Date, "
                    + "data_powrotu Date, "
                    + "miejsce varchar(20), "
                    + "cena varchar(20), "
                    + "symbol_waluty varchar(10))"
            );


            PreparedStatement ps = connection.prepareStatement("INSERT INTO Oferta VALUES(?,?,?,?,?,?,?)");
            for (String line : travelData.getDescList()) {
                String[] tokens = line.split("\t");

                ps.setInt(1, id);
                id++;
                ps.setString(2, tokens[0]);
                ps.setString(3, tokens[1]);
                ps.setString(4, tokens[2]);
                ps.setString(5, tokens[3]);
                ps.setString(6, tokens[4]);
                ps.setString(7, tokens[5]);
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    void showGui() {

    }
}