package es.serbatic.modelo.VO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "categoria")
public class CategoriaVO {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
}
