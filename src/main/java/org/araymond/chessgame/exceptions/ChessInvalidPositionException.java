package org.araymond.chessgame.exceptions;

/**
 * Created by Anthony on 21/06/2015.
 */
public final class ChessInvalidPositionException extends ChessException {


    private static final long serialVersionUID = -7498295937812106982L;

    /**
     * {@inheritDoc}
     */
    public ChessInvalidPositionException() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    public ChessInvalidPositionException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public ChessInvalidPositionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * {@inheritDoc}
     */
    public ChessInvalidPositionException(Throwable cause) {
        super(cause);
    }
}
