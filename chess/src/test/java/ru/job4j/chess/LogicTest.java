package ru.job4j.chess;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.BishopBlack;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogicTest {

    @Test
    public void whenMoveThenFigureNotFoundException()
            throws FigureNotFoundException {
        Logic logic = new Logic();
        FigureNotFoundException exception = assertThrows(FigureNotFoundException.class, () -> {
            logic.move(Cell.C1, Cell.H6);
        });
        assertThat(exception.getMessage()).isEqualTo("Figure not found on the board.");
    }

    @Test
    public void whenMoveThenFigureIsImpossible()
            throws ImpossibleMoveException {
        Logic logic = new Logic();
        BishopBlack bp = new BishopBlack(Cell.C8);
        BishopBlack bishopBlack = new BishopBlack(Cell.C7);
        logic.add(bp);
        logic.add(bishopBlack);
        ImpossibleMoveException exception = assertThrows(ImpossibleMoveException.class, () -> {
            logic.move(Cell.C7, Cell.C8);
        });
        assertThat(exception.getMessage()).isEqualTo("Could not move by diagonal from %s to %s",
                bishopBlack.position(), bp.position());
    }

    @Test
    public void whenMoveThenFigureAtOccupiedCell()
            throws OccupiedCellException {
        Logic logic = new Logic();
        BishopBlack bp = new BishopBlack(Cell.C1);
        BishopBlack bishopBlack = new BishopBlack(Cell.D2);
        logic.add(bp);
        logic.add(bishopBlack);
        OccupiedCellException exception = assertThrows(OccupiedCellException.class, () -> {
            logic.move(Cell.C1, Cell.D2);
        });
        assertThat(exception.getMessage()).isEqualTo("Could not move! Cell is occupied.");
    }
}