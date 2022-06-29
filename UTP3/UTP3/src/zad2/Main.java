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
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Main {
  public static void main(String[] args) throws IOException {
    Function<String,List<String>> flines = (CEF<String,List<String>>)filename ->{
        return Files.readAllLines(Paths.get(filename));
    };

    Function<List<String>, String> join = (l) -> (String.join("", l));

    Function<String,List<Integer>> collectInts = e ->{
      List<Integer> integers = new ArrayList();
      Pattern p = Pattern.compile("\\d+");
      Matcher m = p.matcher(e);
      while(m.find()) {
        integers.add(Integer.valueOf(m.group()));
      }
      return integers;
    };

    Function<List<Integer>, Integer> sum = (ints) -> (ints.stream().mapToInt(i -> i).sum());

    String fname = System.getProperty("user.home") + "/LamComFile.txt";
    InputConverter<String> fileConv = new InputConverter<>(fname);
    List<String> lines = fileConv.convertBy(flines);
    String text = fileConv.convertBy(flines, join);
    List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
    Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

    System.out.println(lines);
    System.out.println(text);
    System.out.println(ints);
    System.out.println(sumints);

    List<String> arglist = Arrays.asList(args);
    InputConverter<List<String>> slistConv = new InputConverter<>(arglist);
    sumints = slistConv.convertBy(join, collectInts, sum);
    System.out.println(sumints);

    // Zadania badawcze:
    // Operacja flines zawiera odczyt pliku, zatem może powstac wyjątek IOException
    // Wymagane jest, aby tę operację zdefiniowac jako lambda-wyrażenie
    // Ale z lambda wyrażeń nie możemy przekazywac obsługi wyjatków do otaczającego bloku
    // I wobec tego musimy pisać w definicji flines try { } catch { }
    // Jak spowodować, aby nie było to konieczne i w przypadku powstania wyjątku IOException
    // zadziałała klauzula throws metody main
  }
}
