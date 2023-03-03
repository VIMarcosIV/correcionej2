package org.iesch.correcionej2.repositorios;

import org.iesch.correcionej2.modelos.Raqueta;
import org.iesch.correcionej2.modelos.Tenista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RaquetaRepositorio extends JpaRepository<Raqueta, Long> {
    List<Raqueta> findByMarca(String marca);

    Raqueta findByTenistas(Tenista tenista);
}
