package org.example.nqueen;

import org.example.Entity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class NQueenEntity implements Entity {
    public final static int SIZE = 8;
    public final static char QUEEN_CHAR = 'o';
    private char[][] queens;

    public NQueenEntity(char[][] queens) {
        validate(queens);
        this.queens = queens;
    }

    @Override
    public int fitness() {
        Set<Integer> rows = new HashSet<>();
        Set<Integer> cols = new HashSet<>();
        Set<Integer> lDiag = new HashSet<>();
        Set<Integer> rDiag = new HashSet<>();
        int fitness = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (queens[i][j] == QUEEN_CHAR) {
                    int lDiagVal = i-j;
                    int rDiagVal = i+j -(SIZE-1);
                    if (rows.contains(i)) {
                        fitness++;
                    }
                    if (cols.contains(j)) {
                        fitness++;
                    }
                    if (lDiag.contains(lDiagVal)) {
                        fitness++;
                    }
                    if (rDiag.contains(rDiagVal)) {
                        fitness++;
                    }
                    rows.add(i);
                    cols.add(j);;
                    lDiag.add(lDiagVal);
                    rDiag.add(rDiagVal);
                }
            }
        }
        return fitness;
    }

    @Override
    public int compareTo(Entity o) {
        return this.fitness() - o.fitness();
    }

    private void validate(char[][] queens) {
        if (queens.length != SIZE || Arrays.stream(queens).anyMatch(q -> q.length != SIZE)) {
            throw new IllegalArgumentException("Invalid map size!");
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("f=" + fitness() + "\n");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                builder.append(queens[i][j]).append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
