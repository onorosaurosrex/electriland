package tech.botworks.electriland.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.botworks.electriland.enumerators.Rol;


@Getter // LOMBOK - SHORTCUTS
@Setter // LOMBOK - SHORTCUTS
@NoArgsConstructor // LOMBOK - SHORTCUTS
@Entity // SPRING ANNOTATION
@Table (name="usuario") // S.A. SMALLCAP DATABASE NAMES
public class Usuario {
  @Id // THIS IS THE PRIMARYKEY
  @GeneratedValue (strategy = GenerationType.UUID) // WE WILL GENERATE UUID VALUES
  private String idUsuario;

  @Column (nullable=false, unique=true) // MANDATORY INPUT ALSO UNIQUE
  private String email;

  @Column (nullable=false) // MANDATORY INPUT
  private String nombre;
  
  @Column (nullable=false) // MANDATORY INPUT
  private String apellido;
  
  @Column (nullable=false) // MANDATORY INPUT
  private String password;

  @Enumerated(EnumType.STRING) // MANDATORY INPUT DEFAULT SET AS "USER" 
  private Rol rol = Rol.USER;

}
