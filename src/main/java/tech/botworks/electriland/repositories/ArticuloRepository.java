package tech.botworks.electriland.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.botworks.electriland.entities.Articulo;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo,String> {
  Articulo findByNombreArticulo(String nombreArticulo);

  @Query ("SELECT COALESCE(MAX(a.nroArticulo), 0) FROM Articulo a")
  public Integer encontrarNumeroProducto();
}
