package tech.botworks.electriland.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.botworks.electriland.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,String> {

  
}