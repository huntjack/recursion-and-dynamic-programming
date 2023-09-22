package triple_step;

public class TripleStep {
    public static int countSteps(int n) {
        return countPermutationsWithSum(n, new int[n + 1]);
    }
    private static int countPermutationsWithSum(int n, int[] memo) {
        if(n < 0) {
            return 0;
        } else if(n < 2) {
            return 1;
        } else if(memo[n] == 0) {
            memo[n] = countPermutationsWithSum(n - 1, memo) +
                    countPermutationsWithSum(n - 2, memo) +
                    countPermutationsWithSum(n - 3, memo);
        }
        return memo[n];
    }
    public static void main(String args[]) {
        int n = 5;
        int result = countSteps(n);
        StringBuilder stringBuilder = new StringBuilder("A child can hop up a staircase with ");
        stringBuilder.append(n);
        stringBuilder.append(" steps in ");
        stringBuilder.append(result);
        stringBuilder.append(" ways.");
        System.out.println(stringBuilder);
    }
}
