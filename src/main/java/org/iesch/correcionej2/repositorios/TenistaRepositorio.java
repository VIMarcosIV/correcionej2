package org.iesch.correcionej2.repositorios;

import org.iesch.correcionej2.modelos.Tenista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TenistaRepositorio extends JpaRepository<Tenista,Long> {
    List<Tenista> findByNombre(String nombre);

    Optional<Tenista> findByRanking(int ranking);
}
