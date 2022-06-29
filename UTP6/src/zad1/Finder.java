/**
 *
 *  @author Wasilewski Bartosz S20296
 *
 */

package zad1;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Finder {

    private List<String> list = new ArrayList<>();
    public Finder(String fname) throws FileNotFoundException, IOException {
        list = Files.readAllLines(Paths.get(fname), StandardCharsets.UTF_8);
    }

    public int getIfCount() {
        int count = 0;
        int c = 0;
        int c1 = 0;
        int c2 = 0;
        int c3 = 0;
        for(int i=0; i<list.size(); i++) {
            Pattern pattern = Pattern.compile("if\\s*[(].+?[)]");
            Matcher matcher = pattern.matcher(list.get(i));

            Pattern pattern1 = Pattern.compile("(.*)//.*");
            Matcher matcher1 = pattern1.matcher(list.get(i));

            Pattern pattern2 = Pattern.compile("(.*)/[*].*");
            Matcher matcher2 = pattern2.matcher(list.get(i));

            Pattern pattern3 = Pattern.compile("(.*)\".*?if\\s*?[(].+?[)].*?\"(.*)");
            Matcher matcher3 = pattern3.matcher(list.get(i));


            while(matcher.find()){
                c++;
            }

            while(matcher1.find()){
                c1++;
            }

            while (matcher2.find()) {
                c2++;
            }
            while (matcher3.find()) {
                c3++;
            }
            if(c1 < 1 && c2 < 1 && c3<1 && c>0){
                count = count + c;
            }
            c1 = 0;
            c2 = 0;
            c3 = 0;
            c = 0;
        }
        return count;
    }

    public int getStringCount(String string) {
        int count = 0;
        for(int i=0; i<list.size(); i++) {
            Pattern pattern = Pattern.compile(string);
            Matcher matcher = pattern.matcher(list.get(i));
            while (matcher.find()) {
                count++;
            }
        }
        return count;
    }

}
