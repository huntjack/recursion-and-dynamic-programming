package paint_fill;

import robot_in_a_grid.Point;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PaintFill {
    public static Color[][] fill(Color[][] matrix, Point point, Color target) {
        Color original = getColor(matrix, point);
        return fill(matrix, point, target, original);
    }
    private static Color getColor(Color[][] matrix, Point point) {
        int row = point.getRow();
        int column = point.getColumn();
        return matrix[row][column];
    }
    private static Color[][] fill(Color[][] matrix, Point point, Color target, Color original) {
        List<Point> candidates = getCandidates(matrix, point, original);
        for(Point candidate : candidates) {
            matrix = paint(matrix, candidate, target);
            matrix = fill(matrix, candidate, target, original);
        }
        return matrix;
    }
    private static List<Point> getCandidates(Color[][] matrix, Point point, Color original) {
        int row = point.getRow();
        int column = point.getColumn();
        List<Point> candidates = new LinkedList<>();
        if(isCandidate(matrix, row, column - 1, original)) {
            Point left = new Point(row, column - 1);
            candidates.add(left);
        }
        if(isCandidate(matrix, row, column + 1, original)) {
            Point right = new Point(row, column + 1);
            candidates.add(right);
        }
        if(isCandidate(matrix, row - 1, column, original)) {
            Point up = new Point(row - 1, column);
            candidates.add(up);
        }
        if(isCandidate(matrix, row + 1, column, original)) {
            Point down = new Point(row + 1, column);
            candidates.add(down);
        }
        return candidates;
    }
    private static boolean isCandidate(Color[][] matrix, int row, int column, Color original) {
        if(isOutOfBounds(matrix, row, column)) {
            return false;
        }
        return matrix[row][column] == original;
    }
    private static boolean isOutOfBounds(Color[][] matrix, int row, int column) {
        return row < 0 || row >= matrix.length || column >= matrix[0].length || column < 0;
    }
    private static Color[][] paint(Color[][] matrix, Point point, Color target) {
        int row = point.getRow();
        int column = point.getColumn();
        matrix[row][column] = target;
        return matrix;
    }
    private static void printMatrix(Color[][] matrix) {
        for(int i = 0; i < matrix.length; i++) {
            Arrays.stream(matrix[i])
                    .forEach(pixel ->
                            System.out.print(pixel + " "));
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Color[][] matrix = {
                {Color.GREEN, Color.GREEN, Color.GREEN, Color.YELLOW},
                {Color.GREEN, Color.RED, Color.GREEN, Color.YELLOW},
                {Color.GREEN, Color.GREEN, Color.RED, Color.RED}
            };
        Point target = new Point(1, 2);
        matrix = fill(matrix, target, Color.BLUE);
        printMatrix(matrix);
    }
}
