/**
 *
 *  @author Wasilewski Bartosz S20296
 *
 */

package zad3;

import java.io.*;
import java.util.*;

public class Anagrams {
    private List<String> list1;
    private List<List> list2;

    public Anagrams(String allWords) throws FileNotFoundException {
        list1 = new ArrayList<String>();
        Scanner scan = new Scanner (new File(allWords));
        while (scan.hasNext()) {
            list1.add(scan.next());
        }

        scan.close();
    }
    public boolean isAnagram(String s1, String s2) {
        boolean isAnagram = false;
        if (s1.length() == s2.length()) {
            char[] s1AsChar = s1.toCharArray();
            char[] s2AsChar = s2.toCharArray();
            Arrays.sort(s1AsChar);
            Arrays.sort(s2AsChar);
            isAnagram = Arrays.equals(s1AsChar, s2AsChar);
        }
        return isAnagram;
    }

    public List<List> getSortedByAnQty() {
        list2 = new ArrayList<List>();
        List<String> t = new ArrayList<String>();
        for (int i = 0; i < list1.size(); i++) {
            if (!t.contains(list1.get(i))) {
                List<String> tmp = new ArrayList<String>();
                for (int j = 0; j < list1.size(); j++) {
                    if (isAnagram(list1.get(i), list1.get(j))) {
                        t.add(list1.get(j));
                        tmp.add(list1.get(j));
                    }
                }
                list2.add(tmp);
            }
        }
        list2.sort((o1, o2) -> o2.size() - o1.size());
        return list2;
    }

    public String getAnagramsFor(String next) {
        for (int i = 0; i < list2.size(); i++) {
            List<String> tmp = new ArrayList<String>(list2.get(i));
            for (int j = 0; j < tmp.size(); j++) {
                if(tmp.get(j).equals(next)) {
                    tmp.remove(j);
                    return next + ": " + tmp;
                }
            }
        }
        return "";
    }
}
