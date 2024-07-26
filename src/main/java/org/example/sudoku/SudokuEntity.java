package org.example.sudoku;

import org.example.Entity;

import java.util.*;
import java.util.function.Consumer;

public class SudokuEntity {
    private static final int SIZE = 8;
    private static final String DELIMETER = "-";

    private int[][] data;
    private Set<String> baseCoordinates;
    private Integer fitness;

    public static List<SudokuEntity> population(int[][] data, int populationCount) {
        if (populationCount <= 0) {
            throw new IllegalArgumentException("Population size cannot be less than 1!");
        }
        Random random = new Random();
        List<SudokuEntity> population = new ArrayList<>();
        for (int i = 0; i < populationCount; i++) {
            population.add(new SudokuEntity(randomSolution(random, data)));
        }
        return population;
    }

    private static int[][] randomSolution(Random random, int[][] data) {
        int[][] solution = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (data[i][j] == 0) {
                    solution[i][j] = random.nextInt(9) + 1;
                } else {
                    solution[i][j] = data[i][j];
                }
            }
        }
        return solution;
    }

    public SudokuEntity(int[][] data) {
        this.data = data;
        validate();
        fillBaseCoordinates();
    }

    private void fillBaseCoordinates() {
        baseCoordinates = new HashSet<>();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != 0) {
                    baseCoordinates.add(coordinate(i, j));
                }
            }
        }
    }

    public int fitness() {
        if (fitness == null) {
            fitness = 0;
            Map<Integer, List<String>> valuePlacements = new HashMap<>();
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    addToMap(valuePlacements, data[i][j], coordinate(i, j));
                }
            }
            for (Map.Entry<Integer, List<String>> entry : valuePlacements.entrySet()) {
                Set<String> rows = new HashSet<>();
                Set<String> cols = new HashSet<>();
                if (entry.getKey() != 0) {
                    for (String coordinate : entry.getValue()) {
                        String[] xy = coordinate.split(DELIMETER, 2);
                        if (rows.contains(xy[0])) {
                            fitness++;
                        }
                        if (cols.contains(xy[1])) {
                            fitness++;
                        }
                        rows.add(xy[0]);
                        cols.add(xy[1]);
                    }
                }
            }
        }
        return fitness;
    }

    @Override
    public String toString() {
        return "SudokuEntity{}";
    }

    private void walk(Consumer<Integer> consumer) {

    }

    private String coordinate(int line, int col) {
        return line + DELIMETER + col;
    }

    private void addToMap(Map<Integer, List<String>> valuePlacements, int value, String s) {
        if (!valuePlacements.containsKey(value)) {
            valuePlacements.put(value, new ArrayList<>());
        }
        valuePlacements.get(value).add(s);
    }

    private void validate() {
        if (data.length != SIZE || !Arrays.stream(data).allMatch(l -> {
            return l.length == SIZE;
        })) {
            throw new IllegalArgumentException("Size of sudoku is not valid!");
        }
        validateCells(data);
        if (fitness() > 0) {
            throw new IllegalArgumentException("Sudoku is unsolvable!");
        }
    }

    private void validateCells(int[][] data) {
        for (int[] line : data) {
            for (int cell : line) {
                if (cell > 9 || cell < 0) {
                    throw new IllegalArgumentException("Size of sudoku is not valid!");
                }
            }
        }
    }
}
