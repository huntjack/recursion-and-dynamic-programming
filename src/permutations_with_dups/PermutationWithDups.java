package permutations_with_dups;

import permutations_without_dups.PermutationsWithoutDups;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class PermutationWithDups {
    public static Set<List<Character>> getPermutations(String string) {
        List<Character> charInput = PermutationsWithoutDups.toCharList(string);
        PartialResult result = getPermutations(charInput, new LinkedList<>());
        return result.getPermutations();
    }
    private static PartialResult getPermutations(List<Character> string, List<Character> permutation) {
        if(string.isEmpty()) {
            Set<List<Character>> permutations = new HashSet<>();
            permutations.add(permutation);
            PartialResult partialResult = new PartialResult(permutations, string);
            return partialResult;
        }
        int lastIndex = string.size() - 1;
        char currentElement = string.remove(lastIndex);
        Set<List<Character>> allPermutations = new HashSet<>();
        for(int i = 0; i <= permutation.size(); i++) {
            List<Character> nextPermutation = new LinkedList<>(permutation);
            nextPermutation.add(i, currentElement);
            PartialResult currentResult = getPermutations(string, nextPermutation);
            Set<List<Character>> currentPermutations = currentResult.getPermutations();
            allPermutations.addAll(currentPermutations);
            string = currentResult.getString();
        }
        string.add(currentElement);
        return new PartialResult(allPermutations, string);
    }
    public static void main(String[] args) {
        String string = new String("loop");
        Set<List<Character>> result = getPermutations(string);
        PermutationsWithoutDups.print(result);
    }
}
