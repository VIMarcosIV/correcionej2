package org.iesch.correcionej2.controladores;

import org.iesch.correcionej2.excepciones.RepresentanteNotFoundException;
import org.iesch.correcionej2.modelos.Representante;
import org.iesch.correcionej2.repositorios.RepresentanteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RepresentanteControlador {
    @Autowired
    RepresentanteRepositorio representanteRepositorio;

    @CrossOrigin(origins = {"**"})
    @GetMapping("/representantes")
    public ResponseEntity<?> obtenerTodos() {
        List<Representante> result = representanteRepositorio.findAll();
        if (result.isEmpty()) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @CrossOrigin(origins = {"**"})
    @GetMapping("/representantes/{id}")
    public Representante obtenerUno(@PathVariable Long id) {
        return representanteRepositorio.findById(id).orElseThrow(() -> new RepresentanteNotFoundException(id));
    }

    @CrossOrigin(origins = {"http://localhost:8888"})
    @PostMapping("/representantes")
    public ResponseEntity<?> nuevaRepresentante(@RequestBody Representante Representante) {
        Representante salvada = representanteRepositorio.save(Representante);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvada);
    }

    @CrossOrigin(origins = {"http://localhost:8888"})
    @PutMapping("/representantes/{id}")
    public Representante editarRepresentante(@RequestBody Representante Representante, @PathVariable Long id) {
        if (representanteRepositorio.existsById(id)) {
            Representante.setId(id);
            Representante actualizada = representanteRepositorio.save(Representante);
            return actualizada;
        }
        throw new RepresentanteNotFoundException(id);
    }

    @CrossOrigin(origins = {"http://localhost:8888"})
    @DeleteMapping("/representantes/{id}")
    public ResponseEntity<?> borrarRepresentante(@PathVariable Long id) {
        if (representanteRepositorio.existsById(id)) {
            representanteRepositorio.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        throw new RepresentanteNotFoundException(id);
    }

    @CrossOrigin(origins = {"**"})
    @GetMapping("/representante/find{nombre}")
    public ResponseEntity<?> buscaNombre(@RequestParam String nombre) {
        List<Representante> lista = representanteRepositorio.findByNombre(nombre);
        return ResponseEntity.ok(lista);
    }
}
