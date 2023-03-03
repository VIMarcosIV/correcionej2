package org.iesch.correcionej2.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TenistaNotFoundException extends RuntimeException {
    public TenistaNotFoundException(Long id) {
        super("No se encontro al tenista con ID: " + id);
    }

    public TenistaNotFoundException(int ranking) {
        super("No se encontro el tenista con ranking: " + ranking);
    }
}
