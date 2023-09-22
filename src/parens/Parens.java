package parens;

import java.util.*;

public class Parens {
    public static void printParens(int numberOfParens) {
        Set<ParenSequence> current = initializeCurrent();
        for(int i = 1; i < numberOfParens; i++) {
            current = createNextSetOfSequences(current);
        }
        printSet(current);
    }
    private static Set<ParenSequence> initializeCurrent() {
        Set<ParenSequence> current = new HashSet<>();
        current.add(new ParenSequence());
        return current;
    }
    private static Set<ParenSequence> createNextSetOfSequences(Set<ParenSequence> current) {
        Set<ParenSequence> next = new HashSet<>();
        for(ParenSequence parenSequence : current) {
            ParenSequence outer = parenSequence.copyPlusOuter();
            next.add(outer);
            ParenSequence left = parenSequence.copyPlusLeft();
            next.add(left);
            ParenSequence right = parenSequence.copyPlusRight();
            next.add(right);
        }
        return next;
    }
    private static void printSet(Set<ParenSequence> set) {
        set.stream()
                .forEach(sequence -> sequence.print());
    }
    public static void main(String[] args) {
        printParens(3);
    }
}
