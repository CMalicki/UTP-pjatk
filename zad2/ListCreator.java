package zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListCreator<T> { // Uwaga: klasa musi być sparametrtyzowana
    public List<T> list;

    public static <T> ListCreator<T> collectFrom(List<T> src) {
        ArrayList<T> c = new ArrayList<>(src); // Do konca dalej nie wiem dlaczego tak, ale potrzebne, bo wywolanie metody clear na ArraysList wyrzuca blad. ArraysList != ArrayList
        ListCreator<T> tempList = new ListCreator<>();
        tempList.list = c;
        return tempList;
    }

    public ListCreator<T> when(List<T> dest, Predicate<T> filter) {
        List<T> swapList = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if(filter.test(dest.get(i))) {
                swapList.add(count, dest.get(i));
                count++;
            }
        }
        list.clear();
        list.addAll(swapList);
        return this;
    }

    public List<T> mapEvery(List<T> dest, Function<T, T> func) {
        List<T> returnList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            T elem = func.apply(list.get(i));
            returnList.add(elem);
        }
        return returnList;
    }
}