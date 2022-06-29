package zad4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProgLang {

    private Map<String, List<String>> langsMap;
    private Map<String, List<String>> progsMap;
    public ProgLang(String fname) {
        langsMap = new HashMap<>();
        progsMap = new HashMap<>();
        try {
            BufferedReader TSVFile = new BufferedReader(new FileReader(fname));
            String line;
            while ((line = TSVFile.readLine()) != null){
                String[] data = line.split("\t");
                List<String> tmp = new ArrayList<>(Arrays.asList(data).subList(1, data.length));
                langsMap.put(data[0], tmp);
                tmp.forEach(name -> {
                    if (progsMap.containsKey(name)) {
                        progsMap.get(name).add(data[0]);
                    } else {
                        List<String> languages = new ArrayList<>();
                        languages.add(data[0]);
                        progsMap.put(name, languages);
                    }
                });
            };
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, List<String>> getLangsMap() {
        return langsMap;
    }

    public Map<String, List<String>> getProgsMap() {
        return progsMap;
    }

    public Map<String, List<String>> getLangsMapSortedByNumOfProgs() {
        this.langsMap.entrySet()
                .stream()
                .sorted((e1,e2) -> {
                    int compare = Integer.compare(e1.getValue().size(), e2.getValue().size()) * -1;

                    if (compare == 0) {
                        return e1.getKey().compareToIgnoreCase(e2.getKey());
                    } else return compare;
                });
        return this.langsMap;
    }

    public Map<String, List<String>> getProgsMapSortedByNumOfLangs() {
        this.progsMap.entrySet()
                .stream()
                .sorted((e1,e2) -> {
                    int compare = Integer.compare(e1.getValue().size(), e2.getValue().size()) * -1;

                    if (compare == 0) {
                        return e1.getKey().compareToIgnoreCase(e2.getKey());
                    } else return compare;
                });
        return this.progsMap;
    }
    public Map<String, List<String>> getProgsMapForNumOfLangsGreaterThan(int i) {
        return filtered(progsMap, n -> n.getValue().size() > i);
    }

    public static <T,V> Map <T,V> sorted(Map<T,V> mapArg, Comparator<Map.Entry<T,V>> comp) {
        Map<T,V> map = mapArg.entrySet().stream()
                .sorted(comp)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (n1, n2) -> n2, LinkedHashMap::new));
        return map;
    }

    public static <T,V> Map <T,V> filtered(Map<T,V> mapArg, Predicate<Map.Entry<T,V>> pred) {
        Map<T,V> map = mapArg.entrySet().stream()
                .filter(pred)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (n1, n2) -> n1, LinkedHashMap::new));
        return map;
    }
}