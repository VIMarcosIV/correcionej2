package org.iesch.correcionej2.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RaquetaNotFoundException extends RuntimeException {
    public RaquetaNotFoundException(Long id) {
        super("No se encontro la raqueta con ID: " + id);
    }
}
