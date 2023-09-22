package robot_in_a_grid;

import java.util.*;

public class RobotInAGrid {
    public static List<Point> findPath(boolean[][] grid) {
        if(grid == null || grid.length == 0) {
            return new LinkedList<>();
        }
        int rows = grid.length - 1;
        int columns = grid[0].length - 1;
        Set<Point> visited = new HashSet<>();
        List<Point> path = findPath(grid, rows, columns, visited);
        return path;
    }
    private static List<Point> findPath(boolean[][] grid, int row, int column, Set<Point> visited) {
        Point current = new Point(row, column);
        if(isBlocked(grid, row, column) || visited.contains(current)) {
            return new LinkedList<>();
        }
        if(isStartPoint(row, column)) {
            List<Point> path = new LinkedList<>();
            path.add(current);
            return path;
        }
        if(!isBlocked(grid, row - 1, column)) {
            List<Point> path = findPath(grid, row - 1, column, visited);
            if(containsStartPoint(path)) {
                path.add(current);
                return path;
            }
        }
        if(!isBlocked(grid, row, column - 1)) {
            List<Point> path = findPath(grid, row, column - 1, visited);
            if(containsStartPoint(path)) {
                path.add(current);
                return path;
            }
        }
        visited.add(current);
        return new LinkedList<>();
    }
    private static boolean isStartPoint(int row, int column) {
        return row == 0 && column == 0;
    }
    private static boolean isBlocked(boolean[][] grid, int row, int column) {
        if(row < 0 || column < 0 || row >= grid.length || column >= grid[0].length) {
            return true;
        }
        return grid[row][column] == true;
    }
    private static boolean containsStartPoint(List<Point> path) {
        if(path.isEmpty()) {
            return false;
        }
        Point point = path.get(0);
        int row = point.getRow();
        int column = point.getColumn();
        return isStartPoint(row, column);
    }
    public static void main(String[] args) {
        boolean[][] grid = new boolean[4][5];
        grid[0][1] = true;
        grid[1][1] = true;
        grid[1][3] = true;
        grid[2][3] = true;
        grid[3][0] = true;
        grid[3][1] = true;
        List<Point> path = findPath(grid);
        for(Point point : path) {
            int row = point.getRow();
            int column = point.getColumn();
            StringBuilder stringBuilder = new StringBuilder("row: ");
            stringBuilder.append(row);
            stringBuilder.append("  column: ");
            stringBuilder.append(column);
            System.out.println(stringBuilder);
        }
    }
}
