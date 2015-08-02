package org.araymond.chessgame.exceptions;

/**
 * Created by Anthony on 21/06/2015.
 */
public final class ChessMoveException extends ChessException {


    private static final long serialVersionUID = -5532735496529953625L;

    /**
     * {@inheritDoc}
     */
    public ChessMoveException() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    public ChessMoveException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public ChessMoveException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * {@inheritDoc}
     */
    public ChessMoveException(Throwable cause) {
        super(cause);
    }
}
