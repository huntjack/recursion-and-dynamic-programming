package permutations_without_dups;

import java.util.*;
import java.util.stream.Collectors;

public class PermutationsWithoutDups {
    public static List<List<Character>> getPermutations(String string) {
        if(!isUnique(string)) {
            throw new IllegalArgumentException();
        }
        List<Character> charInput = toCharList(string);
        PartialResult result = getPermutations(charInput, new LinkedList<>());
        return result.getPermutations();
    }
    private static boolean isUnique(String string) {
        Set<Character> set = new HashSet<>();
        for(int i = 0; i < string.length(); i++) {
            char current = string.charAt(i);
            if(!set.contains(current)) {
                set.add(current);
            } else {
                return false;
            }
        }
        return true;
    }
    public static List<Character> toCharList(String string) {
        return string
                .chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
    }
    private static PartialResult getPermutations(List<Character> string, List<Character> permutation) {
        if(string.isEmpty()) {
            List<List<Character>> permutations = new LinkedList<>();
            permutations.add(permutation);
            PartialResult partialResult = new PartialResult(permutations, string);
            return partialResult;
        }
        int lastIndex = string.size() - 1;
        char currentElement = string.remove(lastIndex);
        List<List<Character>> allPermutations = new LinkedList<>();
        for(int i = 0; i <= permutation.size(); i++) {
            List<Character> nextPermutation = new LinkedList<>(permutation);
            nextPermutation.add(i, currentElement);
            PartialResult currentResult = getPermutations(string, nextPermutation);
            List<List<Character>> currentPermutations = currentResult.getPermutations();
            allPermutations.addAll(currentPermutations);
            string = currentResult.getString();
        }
        string.add(currentElement);
        return new PartialResult(allPermutations, string);
    }
    public static void print(Collection<List<Character>> results) {
        for(List<Character> list : results) {
            for(char character : list) {
                System.out.print(character);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        String string = new String("vent");
        List<List<Character>> results = getPermutations(string);
        print(results);
    }
}
