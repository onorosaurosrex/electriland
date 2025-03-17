package tech.botworks.electriland.entities;

import java.util.concurrent.atomic.AtomicInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter // LOMBOK SHORTCUT
@Setter // LOMBOK SHORTCUT
@Entity // SPRINT COMMENTARY FOR ENTITY IMPLEMENTATION
@Table (name= "articulo") // SINGULAR AND SMALL CAPS NAMES FOR TABLE
public class Articulo {
  // atomicInteger is a STATIC inherent attribute of the class
  // IT WILL IDENTIFY IN AN INCREASING COUNT EVERY INSTANCE OF THE
  // ARTICULO CLASS.
  private static final AtomicInteger atomicInteger = new AtomicInteger(0);

  @Id
  @GeneratedValue (strategy = GenerationType.UUID)
  @Column (name="id_articulo")
  private String idArticulo;
  
  @Column(name="nro_articulo", nullable = false, unique = true)
  private Integer nroArticulo;

  @Column(name="nombre_articulo", nullable = false)
  private String nombreArticulo;

  @Column(name="descripcion_articulo",nullable = false)
  private String descripcionArticulo;
  
  @ManyToOne
  @JoinColumn (name="id_fabrica", nullable = false)
  private Fabrica fabrica;

  public Articulo() {
    this.nroArticulo = atomicInteger.incrementAndGet();
  }
}
