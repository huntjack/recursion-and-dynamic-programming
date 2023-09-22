package magic_index;

import java.util.Optional;

public class MagicIndex {
    public static Optional<Integer> getMagicIndexDistinct(int[] array) {
        if(array == null) {
            throw new IllegalArgumentException();
        }
        return getMagicIndexDistinct(array, 0, array.length - 1);
    }
    private static Optional<Integer> getMagicIndexDistinct(int[] array, int low, int high) {
        if(low > high) {
            return Optional.empty();
        }
        int mid = (low + high) / 2;
        if(isMagicIndex(array, mid)) {
            return Optional.of(mid);
        } else if(array[mid] < mid) {
            return getMagicIndexDistinct(array, mid + 1, high);
        } else {
            return getMagicIndexDistinct(array, low, mid - 1);
        }
    }
    private static boolean isMagicIndex(int[] array, int index) {
        return array[index] == index;
    }
    public static Optional<Integer> getMagicIndex(int[] array) {
        if(array == null) {
            throw new IllegalArgumentException();
        }
        for(int i = 0; i < array.length; i++) {
            if(isMagicIndex(array, i)) {
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }
    public static void main(String[] args) {
        int[] distinctArray = new int[] {0, 2, 3, 4, 5, 8, 16, 32, 64, 128, 256};
        Optional<Integer> optionalDistinctResult = getMagicIndexDistinct(distinctArray);
        int distinctResult = optionalDistinctResult.orElseThrow();
        System.out.println("Distinct Array Result: " + distinctResult);
        int[] array = new int[] {1, 1, 6, 6, 6, 32, 32, 64, 64, 64, 128};
        Optional<Integer> optionalResult = getMagicIndex(array);
        int result = optionalResult.orElseThrow();
        System.out.println("Non-unique Result: " + result);
    }
}
