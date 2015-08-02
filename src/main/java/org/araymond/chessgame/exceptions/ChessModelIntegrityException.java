package org.araymond.chessgame.exceptions;

/**
 * Created by Anthony on 21/06/2015.
 */
public final class ChessModelIntegrityException extends ChessException {

    private static final long serialVersionUID = 619547983441658770L;

    /**
     * {@inheritDoc}
     */
    public ChessModelIntegrityException() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    public ChessModelIntegrityException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public ChessModelIntegrityException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * {@inheritDoc}
     */
    public ChessModelIntegrityException(Throwable cause) {
        super(cause);
    }
}
