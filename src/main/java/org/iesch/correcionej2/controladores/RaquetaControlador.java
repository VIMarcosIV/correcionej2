package org.iesch.correcionej2.controladores;

import org.iesch.correcionej2.excepciones.RaquetaNotFoundException;
import org.iesch.correcionej2.modelos.Raqueta;
import org.iesch.correcionej2.repositorios.RaquetaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RaquetaControlador {
    @Autowired
    RaquetaRepositorio raquetaRepositorio;

    @CrossOrigin(origins = {"**"})
    @GetMapping("/raquetas")
    public ResponseEntity<?> obtenerTodos() {
        List<Raqueta> result = raquetaRepositorio.findAll();
        if (result.isEmpty()) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @CrossOrigin(origins = {"**"})
    @GetMapping("/raquetas/{id}")
    public Raqueta obtenerUno(@PathVariable Long id) {
        return raquetaRepositorio.findById(id).orElseThrow(() -> new RaquetaNotFoundException(id));
    }

    @CrossOrigin(origins = {"http://localhost:8888"})
    @PostMapping("/raquetas")
    public ResponseEntity<?> nuevaRaqueta(@RequestBody Raqueta raqueta) {
        if (raqueta.getRepresentante() != null) {
            Raqueta salvada = raquetaRepositorio.save(raqueta);
            return ResponseEntity.status(HttpStatus.CREATED).body(salvada);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @CrossOrigin(origins = {"http://localhost:8888"})
    @PutMapping("/raquetas/{id}")
    public Raqueta editarRaqueta(@RequestBody Raqueta raqueta, @PathVariable Long id) {
        if (raquetaRepositorio.existsById(id)) {
            raqueta.setId(id);
            Raqueta actualizada = raquetaRepositorio.save(raqueta);
            return actualizada;
        }
        throw new RaquetaNotFoundException(id);
    }

    @CrossOrigin(origins = {"http://localhost:8888"})
    @DeleteMapping("/raquetas/{id}")
    public ResponseEntity<?> borrarRaqueta(@PathVariable Long id) {
        if (raquetaRepositorio.existsById(id)) {
            raquetaRepositorio.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        throw new RaquetaNotFoundException(id);
    }

    @CrossOrigin(origins = {"**"})
    @GetMapping("/raquetas/find{marca}")
    public ResponseEntity<?> buscaMarca(@RequestParam String marca) {
        List<Raqueta> list = raquetaRepositorio.findByMarca(marca);
        return ResponseEntity.ok(list);
    }

    @CrossOrigin(origins = {"**"})
    @GetMapping("/raquetas/{id}/representante")
    public ResponseEntity<?> buscaRepresentanteDadoIdRaqueta(@PathVariable Long id) {
        if (raquetaRepositorio.existsById(id)) {
            Raqueta raqueta = raquetaRepositorio.findById(id).get();
            return ResponseEntity.ok(raqueta.getRepresentante());
        }
        throw new RaquetaNotFoundException(id);
    }
}
