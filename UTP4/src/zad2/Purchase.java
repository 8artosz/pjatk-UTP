/**
 *
 *  @author Wasilewski Bartosz S20296
 *
 */

package zad2;


public class Purchase {
    private String ID;
    private String imie;
    private String nazwisko;
    private String produkt;
    private double cena;
    private double ilosc;
    private double koszt;

    public Purchase(String id,String nazwisko, String imie, String produkt, double cena, double ilosc, double koszt) {
        this.ID = id;
        this.nazwisko = nazwisko;
        this.imie = imie;
        this.produkt = produkt;
        this.cena = cena;
        this.ilosc = ilosc;
        this.koszt = koszt;
    }

    public String getId() {
        return ID;
    }

    public void setId(String Id) {
        this.ID = Id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getProdukt() {
        return produkt;
    }

    public void setProdukt(String produkt) {
        this.produkt = produkt;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public double getIlosc() {
        return ilosc;
    }

    public void setIlosc(double ilosc) {
        this.ilosc = ilosc;
    }

    public double getKoszt() {
        return koszt;
    }
    @Override
    public String toString() {
        return ID + ";" + nazwisko + " " + imie + ";" + produkt + ";" + cena + ";" + ilosc;
    }
}
