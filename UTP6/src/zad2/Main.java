/**
 *
 *  @author Wasilewski Bartosz S20296
 *
 */

package zad2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public class Main {
  public static int m = 0;
  public static void main(String[] args) throws IOException {
    InputStream inputStream = new URL("http://wiki.puzzlers.org/pub/wordlists/unixdict.txt").openStream();
    BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));
    Map<String, List<String>> anagrams = bufferReader.lines().sorted().collect(Collectors.groupingBy(n -> sorted(n)));
    anagrams.forEach((n1, n2) -> size(n2));
    Map<String, List<String>> map = anagrams.entrySet().stream().filter(n -> m == n.getValue().size()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    List<List<String>> myList = new ArrayList<List<String>>(map.values());
    myList.sort((l1, l2) -> l1.get(0).compareTo(l2.get(0)));
    myList.forEach(s -> System.out.println(s.stream().collect(Collectors.joining(" "))));
  }
  public static String sorted(String string) {
    char[] chars = string.toCharArray();
    Arrays.sort(chars);
    return new String(chars);
  }
  public static int size(List<String> list) {
    if(m < list.size()) m = list.size();
    return m;
  }}
