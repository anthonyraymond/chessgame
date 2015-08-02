package org.araymond.chessgame.exceptions;

/**
 * Created by Anthony on 14/07/2015.
 */
public class ChessGameIllegalStateException extends ChessException {

    private static final long serialVersionUID = 5026226000369197286L;

    /**
     * {@inheritDoc}
     */
    public ChessGameIllegalStateException() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    public ChessGameIllegalStateException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public ChessGameIllegalStateException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * {@inheritDoc}
     */
    public ChessGameIllegalStateException(Throwable cause) {
        super(cause);
    }
}
