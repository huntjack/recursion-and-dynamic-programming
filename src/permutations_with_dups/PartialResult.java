package permutations_with_dups;

import java.util.List;
import java.util.Set;

public class PartialResult {
        private Set<List<Character>> permutations;
        private List<Character> string;
        public PartialResult(Set<List<Character>> permutations, List<Character> string) {
            this.permutations = permutations;
            this.string = string;
        }
        public Set<List<Character>> getPermutations() {
            return permutations;
        }
        public List<Character> getString() {
            return string;
        }
}
