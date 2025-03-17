package tech.botworks.electriland.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.botworks.electriland.entities.Fabrica;

@Repository
public interface FabricaRepository extends JpaRepository<Fabrica,String> {

}
