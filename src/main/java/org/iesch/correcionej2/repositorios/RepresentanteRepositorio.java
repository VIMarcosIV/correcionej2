package org.iesch.correcionej2.repositorios;

import org.iesch.correcionej2.modelos.Representante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepresentanteRepositorio extends JpaRepository<Representante, Long> {
    List<Representante> findByNombre(String nombre);
}
