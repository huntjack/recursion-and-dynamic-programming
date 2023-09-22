package eight_queens;

import java.util.*;

public class EightQueens {
    private static final int boardSize = 8;
    public static List<List<Integer>> solve() {
        List<Integer> state = new LinkedList<>();
        return search(state);
    }
    private static List<List<Integer>> search(List<Integer> state) {
        if(isValidState(state)) {
            List<Integer> result = new LinkedList<>(state);
            List<List<Integer>> resultList = new LinkedList<>();
            resultList.add(result);
            return resultList;
        }
        List<List<Integer>> resultList = new ArrayList<>();
        for(Integer candidate : getCandidates(state)) {
            state.add(candidate);
            List<List<Integer>> result = search(state);
            if(!result.isEmpty()) {
                resultList.addAll(result);
            }
            int last = state.size() - 1;
            state.remove(last);
        }
        return resultList;
    }
    private static boolean isValidState(List<Integer> state) {
        return state.size() == boardSize;
    }
    private static List<Integer> getCandidates(List<Integer> state) {
        Set<Integer> takenColumns = new HashSet<>(state);
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.modelState(state);
        List<Integer> candidates = new LinkedList<>();
        for(int i = 0; i < boardSize; i++) {
            int row = state.size();
            if(!takenColumns.contains(i) &&
                    chessBoard.isAvailable(row, i)) {
                candidates.add(i);
            }
        }
        return candidates;
    }
    public static <T> void print(List<List<T>> lists) {
        for(List<T> list : lists) {
            list.stream()
                    .forEach(
                            element -> System.out.print(element));
            System.out.println();
        }
    }
    public static void main(String[] args) {
        EightQueens eightQueens = new EightQueens();
        List<List<Integer>> result = eightQueens.solve();
        StringBuilder stringBuilder = new StringBuilder("Result List Length: ");
        stringBuilder.append(result.size());
        System.out.println(stringBuilder);
        print(result);
    }
}
