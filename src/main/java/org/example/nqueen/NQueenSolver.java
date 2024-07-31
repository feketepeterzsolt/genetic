package org.example.nqueen;

import org.example.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class NQueenSolver {
    private final Random random;
    private final int populationSize;
    private List<NQueenEntity> population;

    public NQueenSolver(int populationSize) {
        random = new Random();
        this.populationSize = populationSize;
        population = new ArrayList<>();
    }

    public Entity run(int generationNum) {
        for (int i = 0; i < generationNum; i++) {
            population = getPopulation();
            Collections.sort(population);
            printPopulation();
        }
        return population.get(0);
    }

    public List<NQueenEntity> getPopulation() {
        if (population.isEmpty()) {
            for (int i = 0; i < populationSize; i++) {
                population.add(new NQueenEntity(randomSolution(NQueenEntity.SIZE)));
            }
        }
        return population;
    }

    private void printPopulation() {
        for (NQueenEntity entity : population) {
            System.out.println(entity + "\n\n");
        }
    }

    private char[][] randomSolution(int size) {
        char[][] solution = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (random.nextBoolean()) {
                    solution[i][j] = NQueenEntity.QUEEN_CHAR;
                } else {
                    solution[i][j] = 'x'; //TODO mivan ha a queenchar pont 'x'?
                }
            }
        }
        return solution;
    }
}
