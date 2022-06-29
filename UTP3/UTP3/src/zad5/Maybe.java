package zad5;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe <T> {

    private T t;

    private Maybe (T t) {
        this.t = t;
    }

    public static <T> Maybe<T> of(T t) {
        return new Maybe<>(t);
    }

    public boolean isPresent() {
        if(t != null){
            return true;
        }
        return false;
    }
    public void ifPresent (Consumer cons) {
        if (this.t != null)
            cons.accept(t);
    }

    public <F> Maybe<F> map (Function<T, F> function) {
        if(this.t != null){
            return new Maybe<>(function.apply(this.t));
        }
        else return new Maybe<>(null);
    }


    public Maybe<T> filter (Predicate<T> predicate) {
        if (this.t != null) {
        }  if (predicate.test(this.t)){
            return this;
        }
        return this;
    }

    public T orElse (T t) {
        if(this.t != null) {
            return this.t;
        }
        else return t;
    }

    public T get () {
        if (!isPresent())
            throw new NoSuchElementException("maybe is empty");
        return this.t;
    }


    @Override
    public String toString() {
        if(this.t == null)
            return "Maybe is empty";
        else
            return "Maybe has value " + this.t;
    }
}