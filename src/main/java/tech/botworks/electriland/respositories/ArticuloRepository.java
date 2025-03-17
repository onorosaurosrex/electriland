package tech.botworks.electriland.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.botworks.electriland.entities.Articulo;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo,String> {
  
}
