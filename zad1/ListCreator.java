/**
 * @author Malicki Cezary S22434
 */

package zad1;

import java.util.ArrayList;
import java.util.List;

public class ListCreator<T> { // Uwaga: klasa musi być sparametrtyzowana
    public List<T> list;

    public static <T> ListCreator<T> collectFrom(List<T> src) {
        ArrayList<T> c = new ArrayList<>(src); 
        ListCreator<T> tempList = new ListCreator<>();
        tempList.list = c;
        return tempList;
    }

    public ListCreator<T> when(Selector<T> sel) {
        List<T> swapList = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (sel.select(list.get(i))) {
                swapList.add(count, list.get(i));
                count++;
            }
        }
        list.clear();
        list.addAll(swapList);
        return this;
    }

    public List<T> mapEvery(Mapper<T> mapper) {
        List<T> returnList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            T mappedValue = mapper.map(list.get(i));
            returnList.add(mappedValue);
        }
        return returnList;
    }
}  
