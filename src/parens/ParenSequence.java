package parens;

import java.util.LinkedList;
import java.util.List;

public class ParenSequence {
    private List<Boolean> parentheses;
    public ParenSequence copyPlusOuter() {
        List<Boolean> copy = new LinkedList<>(parentheses);
        copy.add(0, false);
        copy.add(true);
        return new ParenSequence(copy);
    }
    public ParenSequence copyPlusLeft() {
        List<Boolean> copy = new LinkedList<>(parentheses);
        copy.add(0, false);
        copy.add(1, true);
        return new ParenSequence(copy);
    }
    public ParenSequence copyPlusRight() {
        List<Boolean> copy = new LinkedList<>(parentheses);
        copy.add(false);
        copy.add(true);
        return new ParenSequence(copy);
    }
    public void print() {
        for(Boolean element : parentheses) {
            if(!element) {
                System.out.print("(");
            } else {
                System.out.print(")");
            }
        }
        System.out.println();
    }
    public ParenSequence() {
        this.parentheses = new LinkedList<>();
        parentheses.add(false);
        parentheses.add(true);
    }
    public ParenSequence(List<Boolean> parentheses) {
        this.parentheses = parentheses;
    }
    @Override
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }
        if(!(o instanceof ParenSequence)) {
            return false;
        }
        ParenSequence other = (ParenSequence) o;
        return parentheses != null &&
                other.parentheses != null &&
                parentheses.equals(
                        other.parentheses);
    }
    @Override
    public int hashCode() {
        return parentheses.hashCode();
    }
}
