package stack_of_boxes;

import java.util.LinkedList;
import java.util.List;

public class PartialResult {
    private List<List<Box>> results;
    private LinkedList<Box> stack;
    public PartialResult(List<List<Box>> results, LinkedList<Box> stack) {
        this.results = results;
        this.stack = stack;
    }
    public List<List<Box>> getResults() {
        return results;
    }
    public LinkedList<Box> getStack() {
        return stack;
    }
}
