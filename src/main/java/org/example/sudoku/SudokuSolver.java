package org.example.sudoku;

import org.example.Entity;

import java.util.*;

public class SudokuSolver {

    private List<SudokuEntity> population;
    private static Set<String> baseCoordinates;

    public SudokuSolver(int[][] sudoku, int populationCount) {
        fillBaseCoordinates(sudoku);
        population = SudokuEntity.population(sudoku, populationCount);
    }

    public Entity run(int generation) {
        for (int i = 0; i < generation; i++) {
            Collections.sort(population);
            
        }
        return population.get(0);
    }

    public void printPopulation() {
        for (SudokuEntity entity : population) {
            System.out.println(entity);
        }
    }

    public static Set<String> getBaseCoordinates() {
        return baseCoordinates;
    }

    private void fillBaseCoordinates(int[][] sudoku) {
        baseCoordinates = new HashSet<>();
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku[i].length; j++) {
                if (sudoku[i][j] != 0) {
                    baseCoordinates.add(SudokuEntity.coordinate(i, j));
                }
            }
        }
    }

    private void validate(SudokuEntity entity) {
        int[][] data = entity.getData();
        if (data.length != SudokuEntity.SIZE || !Arrays.stream(data).allMatch(l -> {
            return l.length == SudokuEntity.SIZE;
        })) {
            throw new IllegalArgumentException("Size of sudoku is not valid!");
        }
        validateCells(data);
        if (entity.fitness() > 0) {
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
