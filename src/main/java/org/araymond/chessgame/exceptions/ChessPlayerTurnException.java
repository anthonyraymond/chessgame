package org.araymond.chessgame.exceptions;

/**
 * Created by Anthony on 14/07/2015.
 */
public class ChessPlayerTurnException extends ChessException {


    private static final long serialVersionUID = 4345896003416219313L;

    /**
     * {@inheritDoc}
     */
    public ChessPlayerTurnException() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    public ChessPlayerTurnException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public ChessPlayerTurnException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * {@inheritDoc}
     */
    public ChessPlayerTurnException(Throwable cause) {
        super(cause);
    }
}
