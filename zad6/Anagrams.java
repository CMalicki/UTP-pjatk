/**
 * @author Malicki Cezary S22434
 */

package zad1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

public class Anagrams{

    private List<String> listOfWords;
    private List<List> listOfLists;

    public Anagrams(String words) throws FileNotFoundException {
        File file = new File(words);
        listOfWords = new ArrayList<>();
        Scanner scanner = new Scanner(new File(words));
        while (scanner.hasNext()) {
            String word = scanner.next();
            listOfWords.add(word);
        }
    }

    public List<List> getSortedByAnQty() {
        listOfLists = new ArrayList<>();
        List<String> words = new ArrayList<>();
        for (int i = 0; i < listOfWords.size(); i++) {
            if (!words.contains(listOfWords.get(i))) {
                List<String> tempList = new ArrayList<>();
                for (int j = 0; j < listOfWords.size(); j++) {
                    if (isAnagram(listOfWords.get(i), listOfWords.get(j))) {
                        words.add(listOfWords.get(j));
                        tempList.add(listOfWords.get(j));
                    }
                }
                listOfLists.add(tempList);
            }
        }

        Comparator<List> listComparator = new Comparator<List>()
        {
            public int compare(List o1, List o2) {
                return Integer.compare(o2.size(), o1.size());
            }
        };
        Collections.sort(listOfLists, listComparator);
        return listOfLists;
    }

    public boolean isAnagram(String w1, String w2) {
        char[] word1 = w1.toCharArray();
        char[] word2 = w2.toCharArray();
        Arrays.sort(word1);
        Arrays.sort(word2);
        return Arrays.equals(word1, word2);
    }


    public String getAnagramsFor(String word) {
        for(int i = 0; i < listOfLists.size(); i++) {
            List<String> tempList = new ArrayList<>();
            tempList.addAll(listOfLists.get(i));
            for(int j = 0; j < tempList.size(); j++) {
                if(tempList.get(j).equals(word)) {
                    tempList.remove(j);
                    return word + ": " + tempList;
                }
            }
        }
        return null;
    }

}  
