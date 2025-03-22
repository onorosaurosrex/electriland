package tech.botworks.electriland.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.botworks.electriland.entities.Usuario;
import tech.botworks.electriland.enumerators.Rol;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,String> {

  Usuario findByNombre(String nombre);
  Usuario findByApellido(String apellido);
  Usuario findByEmail(String email);
  Usuario findByRol(Rol rol);

}