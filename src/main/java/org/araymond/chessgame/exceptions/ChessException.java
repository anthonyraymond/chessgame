package org.araymond.chessgame.exceptions;

/**
 * Created by Anthony on 21/06/2015.
 */
public class ChessException extends RuntimeException {

    private static final long serialVersionUID = 6256544404227699872L;

    /**
     * {@inheritDoc}
     */
    public ChessException() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    public ChessException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public ChessException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * {@inheritDoc}
     */
    public ChessException(Throwable cause) {
        super(cause);
    }
}
