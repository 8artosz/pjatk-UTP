package zad1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

    public class TravelData {
        private ArrayList<ArrayList<String>> dataList = new ArrayList<ArrayList<String>>();
        private ArrayList<String> descList = null;
        private ArrayList<String> tmp = new ArrayList<String>();
        private Locale localeSaved, locale;

        public TravelData(File dataDir) {
            Arrays.stream(Objects.requireNonNull(dataDir.listFiles())).forEach(file -> {
                try {
                    Files.lines(Paths.get(file.getPath())).forEach(line -> {
                        String[] data = line.split("\\t");
                        ArrayList<String> tmpList = new ArrayList<String>();
                        for (int i = 0; i < data.length; i++)
                            tmpList.add(data[i]);

                        dataList.add(tmpList);
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
    }

    public List<String> getOffersDescriptionsList(String localeString, String dateFormat) {
        descList = new ArrayList<String>();
        String[] data = localeString.split("_");
        StringBuilder descString = new StringBuilder();
        if(data.length > 1) {
            localeSaved = new Locale(data[0], data[1]);
        }else {
            localeSaved = new Locale(data[0]);
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

        Date date = null;
        for (ArrayList<String> list : dataList) {
            String[] dataTmp = list.get(0).split("_");
            if(dataTmp.length > 1) {
                locale = new Locale(dataTmp[0], dataTmp[1]);
            }else {
                locale = new Locale(dataTmp[0]);
            }

            Locale.setDefault(locale);

            Locale[] locales = Locale.getAvailableLocales();
            Locale localeTranslate = null;

            for (int i = 0; i < locales.length; i++) {
                if(locales[i].getDisplayCountry().equals(list.get(1)))
                    localeTranslate = locales[i];
            }
            descString.append(localeTranslate.getDisplayCountry(localeSaved)).append("\t");

            try {
                date = simpleDateFormat.parse(list.get(2));
                descString.append(simpleDateFormat.format(date)).append("\t");

                date = simpleDateFormat.parse(list.get(3));
                descString.append(simpleDateFormat.format(date)).append("\t");

                ResourceBundle labels = ResourceBundle.getBundle("zad1.LabelsBundle", localeSaved);
                descString.append(labels.getString(list.get(4))).append("\t");

                NumberFormat numberFormat = NumberFormat.getInstance(locale);
                Number number = numberFormat.parse(list.get(5)).doubleValue();
                descString.append(numberFormat.format(number)).append("\t");

                Currency currency = Currency.getInstance(list.get(6));
                descString.append(currency.getCurrencyCode());
                tmp.add(descString.toString());
                descList.add(descString.toString());
                descString = new StringBuilder();
            } catch (ParseException e) {
               e.printStackTrace();
            }
        }
        return descList;
    }

    public ArrayList<String> getDescList() {
        return tmp;
    }
}