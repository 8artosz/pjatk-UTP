package zad1;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class XList <T> extends ArrayList<T> {

    private XList () {}

    public XList (Object... obj) {
        this.addAll(XList.of(obj));
    }

    public static <T> XList<T> of(Object... obj) {
        XList xList = new XList<>();
        boolean combine = false;
        if (obj.length > 1) {
            combine = true;
            for (Object object : obj) {
                if (!(object instanceof Collection || object.getClass().isArray())) {
                    combine = false;
                    break;
                }
            }
        }
        for (Object object : obj) {
            if (object instanceof Collection && !combine) {
                ((Collection) object).forEach(o -> xList.addAll(XList.of(o)));
            } else if (object.getClass().isArray() && !combine) {
                Arrays.stream(((Object[]) object)).forEach(o -> xList.addAll(XList.of(o)));
            } else {
                if (combine) {
                    xList.add(XList.of(object));
                } else {
                    xList.add(object);
                }
            }
        }
        return xList;
    }

    public static <T> XList<T> charsOf(String string) {
        List tmp  = new ArrayList<>();
        for (int i = 0; i < string.length(); i++) {
            tmp.add(String.valueOf(string.charAt(i)));
        }
        return new XList<T>(tmp);
    }

    public static <T> XList<T> tokensOf(String... string) {
        List tmp = null;
        if(string.length == 1) {
            tmp = new ArrayList<String>(Arrays.asList(string[0].split("\\s")));
        }else {
            tmp = new ArrayList<String>(Arrays.asList(string[0].split(string[string.length - 1])));
        }
        return new XList<T>(tmp);
    }

    public XList<Integer> union(Object... objects) {
        XList xList = new XList(this);
        xList.addAll(XList.of(objects));
        return xList;
    }

    public XList diff(Object... objects) {
        XList list = new XList(this);
        list.removeAll(XList.of(objects));
        return list;
    }

    public XList<T> unique() {
        List<T> tmp = this.stream().distinct().collect(Collectors.toList());
        return new XList<T>(tmp);
    }

    public XList<XList<T>> combine() {
        XList<XList<T>> xList = combine(0, (XList<XList<T>>) this);
        xList.forEach(subList -> Collections.reverse(subList));
        return xList;
    }

    private static <T> XList<XList<T>> combine(int index, XList<XList<T>> size) {
        XList<XList<T>> t = new XList<>();

        if (index == size.size()) {
            t.add(new XList<>());
        } else {
            for (Object obj : size.get(index)) {
                for (XList<T> set : combine(index + 1, size)) {
                    set.add((T) obj);
                    t.add(set);
                }
            }
        }
        return t;
    }



    public <K> XList<String> collect (Function<XList<K>, String> function) {
        XList xList = new XList();
        for (XList<K> t : ((XList<XList<K>>) this)) {
            xList.add(function.apply(t));
        }
        return xList;
    }
    public String join(String separator) {
        StringBuilder sB = new StringBuilder();
        for (T t : this) {
            sB.append(t.toString() + separator);
        }
        return sB.toString();
    }

    public String join() {
        return join("");
    }

    public void forEachWithIndex(BiConsumer<T,Integer> consumer) {
        for (int i = 0; i < this.size(); i++) {
            consumer.accept(this.get(i), i);
        }
    }

}
