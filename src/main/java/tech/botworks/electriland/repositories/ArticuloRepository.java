package tech.botworks.electriland.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.botworks.electriland.entities.Articulo;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo,String> {
  Articulo findByNombreArticulo(String nombreArticulo);
  
  List<Articulo> findAllByOrderByNroArticuloAsc();

  @Query ("SELECT COALESCE(MAX(a.nroArticulo), 0) FROM Articulo a")
  public Integer encontrarNumeroProducto();
}
