package zad3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Predicate;

public class ProgLang {
    Map<String, ArrayList<String>> langMap;
    Map<String, ArrayList<String>> progMap;
    ArrayList<String> fileLines = new ArrayList<>();
    String[] wordsTab;
    ArrayList<String> tempList; // do unikniecia duplikatow
    ArrayList<String> tempProgList = new ArrayList<>();

    public ProgLang(String file) throws FileNotFoundException {
        langMap = new LinkedHashMap<>();
        progMap = new LinkedHashMap<>();

        Scanner scan = new Scanner(new File(file));
        while (scan.hasNext()) {
            fileLines.add(scan.nextLine());
        }
    }

    public Map<String, ArrayList<String>> getLangsMap() {
        for (String fileLine : fileLines) { // uzupelnienie mapy jezykow bez duplikatow
            wordsTab = fileLine.split("\t");
            tempList = new ArrayList<>();
            for (int j = 1; j < wordsTab.length; j++) {
                if (!tempList.contains(wordsTab[j])) {
                    tempList.add(wordsTab[j]);
                }
                if (!tempProgList.contains(wordsTab[j])) { // dodanie programistow bez duplikatow
                    tempProgList.add(wordsTab[j]);
                }
                langMap.put(wordsTab[0], tempList);
            }
        }
        return langMap;
    }

    public Map<String, ArrayList<String>> getProgsMap() {
        for (String s : tempProgList) { // uzupelnienie mapy programistow bez duplikatow
            tempList = new ArrayList<>();
            for (String fileLine : fileLines) {
                wordsTab = fileLine.split("\t");
                for (int n = 1; n < wordsTab.length; n++) {
                    if (wordsTab[n].equals(s)) {
                        if (!tempList.contains(wordsTab[0])) {
                            tempList.add(wordsTab[0]);
                        }
                    }
                }
            }
            progMap.put(s, tempList);
        }
        return progMap;
    }

    public <T, U> Map<T, U> sorted(Map<T, U> map, Comparator<Map.Entry<T, U>> comparator) {
        List<Map.Entry<T, U>> entries = new LinkedList<>(map.entrySet());
        entries.sort(comparator);
        LinkedHashMap<T, U> sortedMap = new LinkedHashMap<>();
        entries.forEach(e -> sortedMap.put(e.getKey(), e.getValue()));
        return sortedMap;
    }

    public <T, U> Map<T, U> filtered(Map<T, U> map, Predicate<Map.Entry<T, U>> predicate) {
        LinkedHashMap<T, U> filteredMap = new LinkedHashMap<>();
        Set<Map.Entry<T, U>> set = map.entrySet();
        set.forEach(
                e -> {
                    if (predicate.test(e)) {
                        filteredMap.put(e.getKey(), e.getValue());
                    }
                });
        return filteredMap;
    }

    public Map<String, ArrayList<String>> getLangsMapSortedByNumOfProgs() {
        return getSortedMap(langMap);
    }

    public Map<String, ArrayList<String>> getProgsMapSortedByNumOfLangs() {
        return getSortedMap(progMap);
    }

    private Map<String, ArrayList<String>> getSortedMap(Map<String, ArrayList<String>> map) {
        return sorted(map, (o1, o2) -> {
            if ((o2.getValue()).size() > (o1.getValue()).size())
                return 1;
            if ((o2.getValue()).size() < (o1.getValue()).size())
                return -1;
            return Integer.compare(0, (o2.getKey()).compareTo(o1.getKey()));
        });
    }

    public Map<String, ArrayList<String>> getProgsMapForNumOfLangsGreaterThan(int number) {
        return filtered(progMap, p -> (((p.getValue()).size())) > number);
    }
}
