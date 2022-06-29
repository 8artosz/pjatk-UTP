/**
 *
 *  @author Wasilewski Bartosz S20296
 *
 */

package zad2;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CustomersPurchaseSortFind {

    private List<Purchase> purchaseList = new ArrayList<>();
    private Purchase purchase = null;

    public void readFile(String fname) {
        purchaseList = new ArrayList<Purchase>();
        try {
            Files.readAllLines(Paths.get(fname)).forEach(line -> {
                String[] splited = line.split(";| ");
                purchase = new Purchase(
                        splited[0],
                        splited[1],
                        splited[2],
                        splited[3],
                        Double.parseDouble(splited[4]),
                        Double.parseDouble(splited[5]),
                        Double.parseDouble(splited[4]) * Double.parseDouble(splited[5])
                );

                purchaseList.add(purchase);
            });
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSortedBy(String string) {
        Comparator<Purchase> byNazwisko = Comparator.comparing(Purchase::getNazwisko).thenComparing(Purchase::getId);
        switch (string) {
            case "Nazwiska":
                System.out.println(string);
                purchaseList.stream().sorted(byNazwisko)
                        .forEach(System.out::println);
                break;

            case "Koszty":
                System.out.println(string);
                purchaseList.stream()
                        .sorted((o1, o2) -> {
                            int priceCompare = Double.compare(
                                    (o1.getIlosc() * o1.getCena() * -1)
                                    , (o2.getCena() * o2.getIlosc() * -1)
                            );

                            if (priceCompare == 0) {
                                return o1.getId().compareToIgnoreCase(o2.getId());
                            } else return priceCompare;
                        })
                        .forEach(System.out::println);
                break;
        }

        System.out.println();
    }

    public void showPurchaseFor(String clientId) {
        System.out.println("Klient " + clientId);
        purchaseList.stream()
                .filter(purchase -> purchase.getId().equalsIgnoreCase(clientId))
                .forEach(purchase -> System.out.println(purchase.toString()));
        System.out.println();
    }
}
