package org.example.nqueen;

import org.example.Entity;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NQueenEntityTest {

    @Test
    public void entity_constructor_should_throw_error_if_col_size_is_invalid() throws Exception {
        char[][] queens = {
                new char[]{'x','x','x','x','x','x','x','x'},
                new char[]{'x','x','x','x','x','x','x','x'},
                new char[]{'x','x','x','x','x','x','x','x'},
                new char[]{'x','x','x','x','x','x','x','x'},
                new char[]{'x','x','x','x','x','x','x'},
                new char[]{'x','x','x','x','x','x','x','x'},
                new char[]{'x','x','x','x','x','x','x','x'},
                new char[]{'x','x','x','x','x','x','x','x'},
        };
        assertThatThrownBy(() -> new NQueenEntity(queens)).isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    public void entity_constructor_should_throw_error_if_row_size_is_invalid() throws Exception {
        char[][] queens = {
                new char[]{'x','x','x','x','x','x','x','x'},
                new char[]{'x','x','x','x','x','x','x','x'},
                new char[]{'x','x','x','x','x','x','x','x'},
                new char[]{'x','x','x','x','x','x','x','x'},
                new char[]{'x','x','x','x','x','x','x','x'},
                new char[]{'x','x','x','x','x','x','x','x'},
                new char[]{'x','x','x','x','x','x','x','x'},
        };
        assertThatThrownBy(() -> new NQueenEntity(queens)).isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    public void entity_constructor_should_create_entity_if_sizes_are_valid() throws Exception {
        char[][] queens = {
                new char[]{'x','x','x','x','x','x','x','x'},
                new char[]{'x','x','x','x','x','x','x','x'},
                new char[]{'x','x','x','x','x','x','x','x'},
                new char[]{'x','x','x','x','x','x','x','x'},
                new char[]{'x','x','x','x','x','x','x','x'},
                new char[]{'x','x','x','x','x','x','x','x'},
                new char[]{'x','x','x','x','x','x','x','x'},
                new char[]{'x','x','x','x','x','x','x','x'},
        };
        Entity entity = new NQueenEntity(queens);
        assertThat(entity).isNotNull();
    }
    @Test
    public void entity_should_generate_valid_perfect_fitness() throws Exception {
        char[][] queens = {
                new char[]{'o','x','x','x','x','x','x','x'},
                new char[]{'x','x','o','x','x','x','x','x'},
                new char[]{'x','o','x','x','x','x','x','x'},
                new char[]{'x','x','x','x','x','x','x','x'},
                new char[]{'x','x','x','x','x','x','x','x'},
                new char[]{'x','x','x','x','x','x','x','x'},
                new char[]{'x','x','x','x','x','x','x','x'},
                new char[]{'x','x','x','o','x','x','x','x'},
        };
        Entity entity = new NQueenEntity(queens);
        assertThat(entity).isNotNull();
        assertThat(entity.fitness()).isEqualTo(0);
    }
    @Test
    public void entity_should_generate_valid_imperfect_fitness() throws Exception {
        char[][] queens = {
                new char[]{'o','x','x','x','x','x','x','x'},
                new char[]{'x','o','x','x','x','x','x','x'},
                new char[]{'x','x','o','x','x','x','x','x'},
                new char[]{'x','x','x','o','x','x','x','x'},
                new char[]{'x','x','x','x','o','x','o','x'},
                new char[]{'x','x','x','x','o','o','x','x'},
                new char[]{'x','x','x','x','x','x','o','x'},
                new char[]{'x','x','x','x','x','x','x','o'},
        };
        Entity entity = new NQueenEntity(queens);
        assertThat(entity).isNotNull();
        assertThat(entity.fitness()).isEqualTo(12);
    }
}
