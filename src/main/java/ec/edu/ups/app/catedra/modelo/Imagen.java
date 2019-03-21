package ec.edu.ups.app.catedra.modelo;

import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Imagen {
	@Id
	@Column(name="img_id")
	private int id;
	
	@Lob
	@Column(name="img_archivo")
	private byte[] imagen;
	
	//bi-directional many-to-one association to Publicacion
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="img_pub_id" ,nullable=false)
	@JsonIgnore
	private Publicacion publicacion; 
	
	//Getters and Setters

	public int getId() {
		return id;
	}

	public Publicacion getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	@Override
	public String toString() {
		return "Imagen [id=" + id + ", imagen=" + Arrays.toString(imagen) + "]";
	}
	
}
