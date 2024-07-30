package org.example.sudoku;

import org.example.Entity;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SudokuSolver {

    private List<SudokuEntity> population;

    public SudokuSolver(int[][] sudoku, int populationCount) {
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
