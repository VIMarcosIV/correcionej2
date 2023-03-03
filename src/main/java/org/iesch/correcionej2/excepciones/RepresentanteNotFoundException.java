package org.iesch.correcionej2.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RepresentanteNotFoundException extends RuntimeException {
    public RepresentanteNotFoundException(Long id) {
        super("No se encontro la representante con ID: " + id);
    }
}
