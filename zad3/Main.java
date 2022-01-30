/**
 *
 *  @author Malicki Cezary S22434
 *
 */

package zad1;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;

/*<--
 *  niezbędne importy
 */
public class Main {
  public static void main(String[] args) {
    /*<--
     *  definicja operacji w postaci lambda-wyrażeń:
     *  - flines - zwraca listę wierszy z pliku tekstowego
     *  - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)
     *  - collectInts - zwraca listę liczb całkowitych zawartych w napisie
     *  - sum - zwraca sumę elmentów listy liczb całkowitych
     */
    Function<String, List<String>> flines = s -> {
      try {
        return Files.readAllLines(Paths.get(s));
      } catch (IOException e) {
        e.printStackTrace();
        return null;
      }
    };

    Function<List<String>, String> join = s -> String.join("", s);

    Function<String, List<Integer>> collectInts = s -> {
      List<Integer> intList = new ArrayList<>();
      StringBuilder cso = new StringBuilder();
      for(int i = 0; i < s.length(); i++) {
        if(Character.isDigit(s.charAt(i))) {
          cso.append(s.charAt(i));
        }
        else {
          cso.append(",");
        }
      }
      String xx = cso.toString();
      List<String> numbers = new ArrayList<String>(Arrays.asList(xx.split(",")));
      numbers.removeAll(Collections.singleton(""));
      for(int i = 0; i < numbers.size(); i++) {
        intList.add(Integer.parseInt(numbers.get(i)));
      }
      return intList;
    };

    Function<List<Integer>, Integer> sum = s -> {
      int intSum = 0;
      for(int i = 0; i < s.size(); i++) {
        intSum = intSum + s.get(i);
      }
      return intSum;
    };

    String fname = System.getProperty("user.home") + "/LamComFile.txt"; 
    InputConverter<String> fileConv = new InputConverter<>(fname);
    List<String> lines = fileConv.convertBy(flines);
    String text = fileConv.convertBy(flines, join);
    List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
    Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

    System.out.println(lines);
    System.out.println(text);
    System.out.println(ints);
    System.out.println(sumints);

    List<String> arglist = Arrays.asList(args);
    InputConverter<List<String>> slistConv = new InputConverter<>(arglist);  
    sumints = slistConv.convertBy(join, collectInts, sum);
    System.out.println(sumints);

  }
}
