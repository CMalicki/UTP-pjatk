/**
 * @author Malicki Cezary S22434
 */

package zad1;

import java.util.*;

public class Main {
    public Main() {
        List<Integer> src1 = Arrays.asList(1, 7, 9, 11, 12);
        System.out.println(test1(src1));

        List<String> src2 = Arrays.asList("a", "zzzz", "vvvvvvv");
        System.out.println(test2(src2));
    }

    public List<Integer> test1(List<Integer> src) {
        Selector sel = new Selector() {
            @Override
            public boolean select(Object o) {
                if ((int) o < 10) {
                    return true;
                }
                return false;
            }
        };
        Mapper map = new Mapper() {
            @Override
            public Object map(Object o) {
                return ((int) o + 10);
            }
        };
        return   /*<-- zwrot wyniku
      uzyskanego przez wywołanie statycznej metody klasy ListCreator:
     */  ListCreator.collectFrom(src).when(sel).mapEvery(map);
    }

    public List<Integer> test2(List<String> src) {
        Selector sel = new Selector() {
            @Override
            public boolean select(Object o) {
                if (o.toString().length() > 3) {
                    return true;
                }
                return false;
            }
        };
        Mapper map = new Mapper() {
            @Override
            public Object map(Object o) {
                return (o.toString().length() + 10);
            }
        };
        return   /*<-- zwrot wyniku
      uzyskanego przez wywołanie statycznej metody klasy ListCreator:
     */  ListCreator.collectFrom(src).when(sel).mapEvery(map);
    }

    public static void main(String[] args) {
        new Main();
    }
}
