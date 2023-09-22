package towers_of_hanoi;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TowersOfHanoi {
    public static List<LinkedList<Integer>> playTowerOfHanoi(int numberOfRings, int start, int destination) {
        if(!isValidInput(start, destination) || numberOfRings < 1) {
            throw new IllegalArgumentException();
        }
        List<LinkedList<Integer>> rods = initializeRods(numberOfRings, start);
        return move(numberOfRings, start, destination, rods);
    }
    private static boolean isValidInput(int start, int destination) {
        return start >= 0 && start <= 2 && destination >= 0 && destination <= 2;
    }
    private static List<LinkedList<Integer>> initializeRods(int numberOfRings, int start) {
        List<LinkedList<Integer>> rods = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            rods.add(new LinkedList<>());
        }
        LinkedList<Integer> rod = rods.get(start);
        for(int i = numberOfRings; i > 0; i--) {
            rod.add(i);
        }
        return rods;
    }
    private static List<LinkedList<Integer>> move(int rings, int start, int destination,  List<LinkedList<Integer>> rods) {
        if(!isValidInput(start, destination) || rings < 1) {
            throw new IllegalArgumentException();
        }
        if(rings == 1) {
            return add(start, destination, rods);
        } else {
            int firstRod = findFirstRod(start, destination);
            rods = move(rings - 1, start, firstRod, rods);
            rods = add(start, destination, rods);
            return move(rings - 1, firstRod, destination, rods);
        }
    }
    private static List<LinkedList<Integer>> add(int start, int destination, List<LinkedList<Integer>> rods) {
        if(!isValidInput(start, destination)) {
            throw new IllegalArgumentException();
        }
        LinkedList<Integer> startRod = rods.get(start);
        LinkedList<Integer> destinationRod = rods.get(destination);
        if(canTransferRings(startRod, destinationRod)) {
            int startTopRing = startRod
                    .pollLast();
            destinationRod.addLast(startTopRing);
        } else {
            throw new IllegalStateException();
        }
        return rods;
    }
    private static boolean canTransferRings(LinkedList<Integer> startRod, LinkedList<Integer> destinationRod) {
        if(startRod.isEmpty()) {
            return false;
        }
        int startTopRing = startRod
                .peekLast();
        int destinationTopRing = !destinationRod.isEmpty() ? destinationRod.peekLast() : Integer.MAX_VALUE;
        return startTopRing < destinationTopRing;
    }
    private static int findFirstRod(int start, int destination) {
        for(int i = 0; i < 3; i++) {
            if(i != start && i != destination) {
                return i;
            }
        }
        throw new IllegalStateException();
    }
    public static void print(List<LinkedList<Integer>> rods) {
        String output = new String("Rod ");
        for(int i = 0; i < rods.size(); i++) {
            LinkedList<Integer> current = rods.get(i);
            StringBuilder stringBuilder = new StringBuilder(output);
            if(current.isEmpty()) {
                stringBuilder.append(i);
                stringBuilder.append(" is empty");
                System.out.println(stringBuilder);
            } else {
                stringBuilder.append(i);
                stringBuilder.append(" contains: ");
                for(Integer integer: current) {
                    stringBuilder.append(integer);
                    stringBuilder.append(" ");
                }
                System.out.println(stringBuilder);
            }
        }
    }
    public static void main(String[] args) {
        List<LinkedList<Integer>> rods = playTowerOfHanoi(24, 0, 2);
        print(rods);
    }
}
