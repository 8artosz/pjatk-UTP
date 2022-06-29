package zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListCreator<T> {

    private List<T> list;

    public ListCreator(List<T> list) {
        this.list = list;
    }

    public static <T> ListCreator<T> collectFrom(List<T> destinations) {
        return new ListCreator<T>(destinations);
    }

    public ListCreator<T> when(Predicate<T> p) {
        List<T> tmp = new ArrayList<T>();

        for(int i = 0; i < list.size(); i++) {
            if(p.test(list.get(i))) {
                tmp.add(list.get(i));
            }
        }
        this.list = tmp;
        return this;
    }

    public List<T> mapEvery(Function<T, T> func) {

        List<T> result = new ArrayList<>();
        for (T t : list) {
            result.add(func.apply(t));
        }
        return result;
    }

}
