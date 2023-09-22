package stack_of_boxes;

import java.util.*;

public class StackOfBoxes {
    public static int getHeightOfTallestPossibleStack(List<Box> boxes) {
        if(boxes == null) {
            throw new IllegalArgumentException();
        }
        List<List<Box>> results = createAllPossibleStacks(boxes);
        int max = 0;
        for(List<Box> stack : results) {
            int stackMax = findMaxHeight(stack);
            max = Math.max(max, stackMax);
        }
        return max;
    }
    private static List<List<Box>> createAllPossibleStacks(List<Box> boxes) {
        Collections.sort(boxes,
                Comparator.comparing(
                        Box::getHeight)
                        .reversed());
        List<List<Box>> result = new LinkedList<>();
        for(int i = 0; i < boxes.size(); i++) {
            LinkedList<Box> stack = new LinkedList<>();
            PartialResult partialResult = createAllPossibleStacksHelper(boxes, i, stack);
            List<List<Box>> listOfStacks = partialResult.getResults();
            result.addAll(listOfStacks);
        }
        return result;
    }
    private static PartialResult createAllPossibleStacksHelper(List<Box> sortedBoxes, int index, LinkedList<Box> stackOfBoxes ) {
        Box current = sortedBoxes.get(index);
        List<List<Box>> result = new LinkedList<>();
        if(canAddBoxToStack(stackOfBoxes, current)) {
            stackOfBoxes.add(current);
            List<Box> stackCopy = new LinkedList<>(stackOfBoxes);
            result.add(stackCopy);
        } else {
            return new PartialResult(result, stackOfBoxes);
        }
        for(int i = ++index; i < sortedBoxes.size(); i++) {
            PartialResult partialResult =
                    createAllPossibleStacksHelper(sortedBoxes, i, stackOfBoxes);
            result.addAll(
                    partialResult.getResults());
            stackOfBoxes = partialResult.getStack();
        }
        stackOfBoxes.removeLast();
        return new PartialResult(result, stackOfBoxes);
    }
    private static boolean canAddBoxToStack(LinkedList<Box> stack, Box current) {
        if(!stack.isEmpty()) {
            Box topOfStack = stack.peekLast();
            return current.isSmallerThan(topOfStack);
        } else {
            return true;
        }
    }
    private static int findMaxHeight(List<Box> stackOfBoxes) {
        int max = 0;
        for(Box box : stackOfBoxes) {
           max += box.getHeight();
        }
        return max;
    }
    public static void main(String[] args) {
        Box a = new Box(6, 8, 4);
        Box b = new Box(5, 6, 7);
        Box c = new Box(4, 5, 2);
        Box d = new Box(3, 4, 3);
        List<Box> boxes = Arrays.asList(a, b, c, d);
        System.out.println("Max Height: " + getHeightOfTallestPossibleStack(boxes));
    }
}
