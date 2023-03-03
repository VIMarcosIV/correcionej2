package org.iesch.correcionej2.controladores;

import org.iesch.correcionej2.excepciones.TenistaNotFoundException;
import org.iesch.correcionej2.modelos.Raqueta;
import org.iesch.correcionej2.modelos.Tenista;
import org.iesch.correcionej2.repositorios.RaquetaRepositorio;
import org.iesch.correcionej2.repositorios.TenistaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TenistaControlador {
    @Autowired
    TenistaRepositorio tenistaRepositorio;
    @Autowired
    RaquetaRepositorio raquetaRepositorio;

    @CrossOrigin(origins = {"**"})
    @GetMapping("/tenistas")
    public ResponseEntity<?> obtenerTodos() {
        List<Tenista> result = tenistaRepositorio.findAll();
        if (result.isEmpty()) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @CrossOrigin(origins = {"**"})
    @GetMapping("/tenistas/{id}")
    public Tenista obtenerUno(@PathVariable Long id) {
        return tenistaRepositorio.findById(id).orElseThrow(() -> new TenistaNotFoundException(id));
    }

    @CrossOrigin(origins = {"http://localhost:8888"})
    @PostMapping("/tenistas")
    public ResponseEntity<?> nuevaTenista(@RequestBody Tenista Tenista) {
        Tenista salvada = tenistaRepositorio.save(Tenista);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvada);
    }

    @CrossOrigin(origins = {"http://localhost:8888"})
    @PutMapping("/tenistas/{id}")
    public Tenista editarTenista(@RequestBody Tenista Tenista, @PathVariable Long id) {
        if (tenistaRepositorio.existsById(id)) {
            Tenista.setId(id);
            Tenista actualizada = tenistaRepositorio.save(Tenista);
            return actualizada;
        }
        throw new TenistaNotFoundException(id);
    }

    @CrossOrigin(origins = {"http://localhost:8888"})
    @DeleteMapping("/tenistas/{id}")
    public ResponseEntity<?> borrarTenista(@PathVariable Long id) {
        if (tenistaRepositorio.existsById(id)) {
            tenistaRepositorio.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        throw new TenistaNotFoundException(id);
    }

    @CrossOrigin(origins = {"**"})
    @GetMapping("/tenistas/find{nombre}")
    public ResponseEntity<?> buscaNombre(@RequestParam String nombre) {
        List<Tenista> list = tenistaRepositorio.findByNombre(nombre);
        return ResponseEntity.ok(list);
    }

    @CrossOrigin(origins = {"**"})
    @GetMapping("/tenistas/{id}/raqueta")
    public ResponseEntity<?> buscaRaquetaDadoIdTenista(@PathVariable Long id) {
        if (tenistaRepositorio.existsById(id)) {
            Tenista tenista = tenistaRepositorio.findById(id).get();
            Raqueta raqueta = raquetaRepositorio.findByTenistas(tenista);
            return ResponseEntity.ok(raqueta);
        }
        throw new TenistaNotFoundException(id);
    }

    @CrossOrigin(origins = {"**"})
    @GetMapping("/tenistas/ranking/{ranking}")
    public ResponseEntity<?> devuelveTenistaPorRanking(@PathVariable int ranking) {
        if (tenistaRepositorio.findByRanking(ranking).isPresent()) {
            Tenista tenista = tenistaRepositorio.findByRanking(ranking).get();
            return ResponseEntity.ok(tenista);
        }
        throw new TenistaNotFoundException(ranking);
    }
}
