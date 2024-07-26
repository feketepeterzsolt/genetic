package org.example.sudoku;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SudokuEntityTest {

    @Test
    public void constructor_should_throw_exception_if_invalid_line_count() {
        int[][] data = {
                new int[8],
                new int[8]
        };
        assertThatThrownBy(() -> new SudokuEntity(data)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void constructor_should_throw_exception_if_invalid_column_count() {
        int[][] data = {
                new int[7],
                new int[7],
                new int[7],
                new int[7],
                new int[7],
                new int[7],
                new int[7],
                new int[7]
        };
        assertThatThrownBy(() -> new SudokuEntity(data)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void constructor_should_throw_exception_if_high_numbers_are_given() {
        int[][] data = {
                {1,0,2,0,5,0,7,0},
                {0,0,0,0,0,0,0,0},
                {10,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0}
        };
        assertThatThrownBy(() -> new SudokuEntity(data)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void constructor_should_throw_exception_if_negative_numbers_are_given() {
        int[][] data = {
                {1,0,2,0,5,0,7,0},
                {0,0,0,0,0,0,0,0},
                {-1,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0}
        };
        assertThatThrownBy(() -> new SudokuEntity(data)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void constructor_should_throw_exception_if_sudoku_is_unsolvable() {
        int[][] data = {
                {1,0,2,0,1,0,7,0},
                {0,0,0,0,0,0,0,0},
                {3,0,1,0,4,0,5,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0}
        };
        assertThatThrownBy(() -> new SudokuEntity(data)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void constructor_should_create_entity_if_size_is_correct() {
        int[][] data = {
                new int[8],
                new int[8],
                new int[8],
                new int[8],
                new int[8],
                new int[8],
                new int[8],
                new int[8]
        };
        SudokuEntity entity = new SudokuEntity(data);
        assertThat(entity).isNotNull();
    }

    @Test
    public void fitness_should_calculate_correct_fitness_if_sudoku_is_solvable() {
        int[][] data = {
                {1,0,2,0,5,0,7,0},
                {0,0,0,0,0,0,0,0},
                {3,0,1,0,4,0,5,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0}
        };
        SudokuEntity entity = new SudokuEntity(data);
        assertThat(entity.fitness()).isEqualTo(0);
    }
}