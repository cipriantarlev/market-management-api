package ii.cipriantarlev.marketmanagementapi.exceptions;

import java.io.Serial;

public class PriceLabelGenerationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -5344786827355065523L;

    public PriceLabelGenerationException(String message) {
        super(message);
    }
}
