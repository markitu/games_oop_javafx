package ru.job4j.chess.firuges.black;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BishopBlackTest {

    @Test
    void position() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C8);
        assertThat(bishopBlack.position()).isEqualTo(Cell.C8);
    }

    @Test
    void copy() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C8);
        Figure bp = bishopBlack.copy(Cell.D7);
        assertThat(bp.position()).isEqualTo(Cell.D7);
    }

    @Test
    void way() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        Cell[] result = new Cell[] {Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        assertThat(bishopBlack.way(Cell.G5)).isEqualTo(result);
    }

    @Test
    void incorrectWay() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        ImpossibleMoveException exception = assertThrows(
                ImpossibleMoveException.class,
                () -> {
                    bishopBlack.way(Cell.C2);
                });
        assertThat(exception.getMessage()).isEqualTo("Could not move by diagonal from %s to %s",
                bishopBlack.position(), Cell.C2);
    }

    @Test
    void wayToSameCell() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        ImpossibleMoveException exception = assertThrows(
                ImpossibleMoveException.class,
                () -> {
                    bishopBlack.way(Cell.C1);
                });
        assertThat(exception.getMessage()).isEqualTo("Could not move by diagonal from %s to %s",
                bishopBlack.position(), Cell.C1);
    }

    @Test
    void isDiagonalC2A4True() {
        Assertions.assertTrue(BishopBlack.isDiagonal(Cell.C2, Cell.A4));
    }

    @Test
    void isDiagonalC2B1True() {
        Assertions.assertTrue(BishopBlack.isDiagonal(Cell.C2, Cell.B1));
    }

    @Test
    void isDiagonalC2D1True() {
        Assertions.assertTrue(BishopBlack.isDiagonal(Cell.C2, Cell.D1));
    }

    @Test
    void isDiagonalC2E4True() {
        Assertions.assertTrue(BishopBlack.isDiagonal(Cell.C2, Cell.E4));
    }

    @Test
    void isDiagonalC2E2False() {
        Assertions.assertFalse(BishopBlack.isDiagonal(Cell.C2, Cell.E2));
    }

    @Test
    void isDiagonalC2C6False() {
        Assertions.assertFalse(BishopBlack.isDiagonal(Cell.C2, Cell.C6));
    }

    @Test
    void isDiagonalC2F4False() {
        Assertions.assertFalse(BishopBlack.isDiagonal(Cell.C2, Cell.F4));
    }

    @Test
    void isDiagonalC2A5False() {
        Assertions.assertFalse(BishopBlack.isDiagonal(Cell.C2, Cell.A5));
    }
}