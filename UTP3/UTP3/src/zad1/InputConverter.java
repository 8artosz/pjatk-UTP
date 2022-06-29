package zad1;

import java.util.function.Function;

public class InputConverter<T> {
    private T t;

    public InputConverter(T t) {
        this.t = t;
    }
    public <T> T convertBy(Function... func) {
        Object in=t;
        Object out=null;
        for (Function f : func) {
            out= f.apply(in);
            in = out;
        }

        return (T) out;
    }

}
