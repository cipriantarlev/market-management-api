/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.exceptions;

import java.io.Serial;

/**
 * Exception to be thrown something went wrong during price label generation.
 *
 * @author ciprian.tarlev
 */
public class PriceLabelGenerationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -5344786827355065523L;

    /**
     * Constructor used when there is only the message.
     *
     * @param message exception's message.
     */
    public PriceLabelGenerationException(String message) {
        super(message);
    }
}
