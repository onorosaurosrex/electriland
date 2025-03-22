package tech.botworks.electriland.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tech.botworks.electriland.entities.Fabrica;

@Repository
public interface FabricaRepository extends JpaRepository<Fabrica,String> {
  
  Optional<Fabrica> findByNombreFabrica(String nombreFabrica);

  @Query ("SELECT f FROM Fabrica f WHERE f.nombreFabrica = :nombre")
  public Fabrica buscarPorNombre(@Param("nombre") String nombre);


}
