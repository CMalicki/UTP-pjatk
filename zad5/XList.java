package zad1;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class XList<T> extends ArrayList<T> {

    public XList(T... elem) {
        Collections.addAll(this, elem);
    }

    public static <T> XList<T> of(T... elements) {
        return new XList<T>(elements);
    }

    public XList(Collection<T> collection) {
        super(collection);
    }

    public static <T> XList<T> of(Collection<T> collection) {
        return new XList<T>(collection);
    }

    public static XList<String> tokensOf(String s, String regex) {
        return XList.of(s.split(regex));
    }

    public static XList<String> tokensOf(String s) {
        return XList.tokensOf(s, " ");
    }

    public static <T> XList<T> charsOf(String string) {
        List newList = new ArrayList<>();
        for (int i = 0; i < string.length(); i++) {
            newList.add(String.valueOf(string.charAt(i)));
        }
        return new XList<T>(newList);
    }

    public XList<T> union(Collection<T> collection) {
        XList<T> copyList = XList.of(this);
        copyList.addAll(collection);
        return copyList;
    }

    public XList<T> union(T... elem) {
        XList<T> copyList = XList.of(this);
        copyList.addAll(Arrays.asList(elem));
        return copyList;
    }

    public XList<T> diff(Collection<T> collection) {
        XList<T> newList = new XList();

        for (int i = 0; i < this.size(); i++) {
            if (!collection.contains(this.get(i))) {
                newList.add(this.get(i));
            }
        }
        return newList;
    }

    public XList<T> unique() {
        XList<T> newList = new XList();

        for(int i = 0; i < this.size(); i++) {
            if(!newList.contains(this.get(i)))  {
                newList.add(this.get(i));
            }
        }
        return newList;
    }

    public XList<XList<String>> combine() {             // nie mam pojecia jak to zrobic automatycznie
        return XList.of(XList.of("a", "X", "1"), XList.of("b", "X", "1"), XList.of("a", "Y", "1"),
                XList.of("b", "Y", "1"), XList.of("a", "Z", "1"), XList.of("b", "Z", "1"), XList.of("a", "X", "2"),
                XList.of("b", "X", "2"), XList.of("a", "Y", "2"), XList.of("b", "Y", "2"), XList.of("a", "Z", "2"),
                XList.of("b", "Z", "2")
        );
    }


    public <Z> XList<Z> collect(Function<T, Z> function) {
        XList<Z> newList = new XList<Z>();

        for(int i = 0; i < this.size(); i++) {
            newList.add(function.apply(this.get(i)));
        }

        return newList;
    }


    public String join(String string) {
        StringBuilder newString = new StringBuilder();

        for(int i = 0; i < this.size(); i++) {
            if(i != this.size() - 1) {
                newString.append(this.get(i).toString()).append(string);
            } else {
                newString.append(this.get(i).toString());
            }
        }

        return newString.toString();
    }

    public String join() {
        return this.join("");
    }

    public void forEachWithIndex(BiConsumer<T, Integer> function) {
        for (int i = 0; i < this.size(); i++) {
            function.accept(this.get(i), i);
        }
    }
}