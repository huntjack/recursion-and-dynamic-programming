package permutations_without_dups;

import java.util.List;

public class PartialResult {
    private List<List<Character>> permutations;
    private List<Character> string;
    public PartialResult(List<List<Character>> permutations, List<Character> string) {
        this.permutations = permutations;
        this.string = string;
    }
    public List<List<Character>> getPermutations() {
        return permutations;
    }
    public List<Character> getString() {
        return string;
    }
}
