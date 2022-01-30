package zad2;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe<T> {
    T value;

    public Maybe(T value) {
        this.value = value;
    }

    public Maybe() {
        super();
    }

    @Override
    public String toString() {
        if(isPresent()) {
            return "Maybe has value " + value;
        } else {
            return "Maybe is empty";
        }
    }

    public static <T> Maybe<T> of(T x) {
        return new Maybe<T>(x);
    }

    public void ifPresent(Consumer<T> cons) {
        if(isPresent()) {
            cons.accept(value);
        }
    }

    public <S> Maybe<S> map(Function<T,S> func) {
        if(isPresent()) {
            S newVal = func.apply(value);
            return new Maybe<S>(newVal);
        } else {
            return new Maybe<S>();
        }
    }

    public T get() {
        if(!isPresent()) {
            throw new NoSuchElementException("maybe is empty");
        } else {
            return value;
        }
    }

    boolean isPresent() {
        if(value != null) {
            return true;
        } else {
            return false;
        }
    }

    public T orElse(T defVal) {
        if(isPresent()) {
            return value;
        } else {
            return defVal;
        }
    }

    public Maybe<T> filter(Predicate<T> pred) {
      if(isPresent()) {
          if(pred.test(value)) {
              return this;
          }
      }

      return this;
    }

}
