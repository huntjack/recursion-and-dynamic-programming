package recursive_multiply;

public class RecursiveMultiply {
    public static int multiply(int a, int b) {
        if(a < 0 || b < 0) {
            throw new IllegalArgumentException();
        }
        int low = a < b ? a : b;
        int high = a >= b ? a : b;
        int[] memo = new int[low + 1];
        return multiply(low, high, memo);
    }
    private static int multiply(int low, int high, int[] memo) {
        if(low == 0 || high == 0) {
            return 0;
        } else if(low == 1) {
            return high;
        } else if (low == 2) {
            return high << 1;
        } else if(memo[low] != 0) {
            return memo[low];
        } else {
            int lowerHalf = low >> 1;
            int higherHalf = low - lowerHalf;
            int result = multiply(lowerHalf, high, memo) +
                    multiply(higherHalf, high, memo);
            memo[low] = result;
            return result;
        }
    }
    public static void main(String[] args) {
        int a = 27;
        int b = 11;
        int result = multiply(a, b);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(a);
        stringBuilder.append(" x ");
        stringBuilder.append(b);
        stringBuilder.append(" = ");
        stringBuilder.append(result);
        System.out.println(stringBuilder);
    }
}
