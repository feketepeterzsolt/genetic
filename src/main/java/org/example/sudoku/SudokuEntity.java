package org.example.sudoku;

import org.example.Entity;

import java.util.*;
import java.util.function.Consumer;

public class SudokuEntity implements Entity {

    public static final int SIZE = 9;
    private static final String DELIMETER = "-";
    // ANSI escape code for bold text
    final String BOLD = "\033[1m";
    // ANSI escape code to reset formatting
    final String RESET = "\033[0m";

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
        fillBaseCoordinates();
    }

    public int[][] getData() {
        return data;
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

    @Override
    public int compareTo(Entity entity) {
        return this.fitness() - entity.fitness();
    }

    @Override
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
        StringBuilder builder = new StringBuilder("f = ").append(fitness()).append("\n");
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (baseCoordinates.contains(i + DELIMETER + j)) {
                    builder.append(BOLD);
                }
                builder.append(data[i][j]).append("\t");
                if (baseCoordinates.contains(i + DELIMETER + j)) {
                    builder.append(RESET);
                }
            }
            builder.append("\n");
        }
        builder.append("\n\n");
        return builder.toString();
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
}
