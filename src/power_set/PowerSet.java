package power_set;

import java.util.*;

public class PowerSet {
    public static <T> List<NavigableSet<T>> getAllSubsets(NavigableSet<T> set) {
        if(set.isEmpty()) {
            NavigableSet<T> resultSet = new TreeSet<>();
            List<NavigableSet<T>> resultList = new LinkedList<>();
            resultList.add(resultSet);
            return resultList;
        }
        T first = set.pollFirst();
        List<NavigableSet<T>> combinationsWithoutFirst = getAllSubsets(set);
        List<NavigableSet<T>> combinationsWithFirst = new LinkedList<>();
        for(NavigableSet<T> combination : combinationsWithoutFirst) {
            NavigableSet<T> newSet = new TreeSet<>(combination);
            newSet.add(first);
            combinationsWithFirst.add(newSet);
        }
        combinationsWithFirst.addAll(combinationsWithoutFirst);
        return combinationsWithFirst;
    }
    public static <T> void printCombinations(List<NavigableSet<T>> resultList) {
        for(NavigableSet<T> set : resultList) {
            if(set.isEmpty()) {
                System.out.println("Empty Subset");
            }
            for(T t : set) {
                System.out.print(t);
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        NavigableSet<Character> characters = new TreeSet<>();
        characters.add(new Character('a'));
        characters.add(new Character('b'));
        characters.add(new Character('c'));
        List<NavigableSet<Character>> combinations = getAllSubsets(characters);
        printCombinations(combinations);
    }
}
