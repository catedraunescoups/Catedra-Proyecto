package ec.edu.ups.app.catedra.datos;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.app.catedra.modelo.Imagen;
import ec.edu.ups.app.catedra.modelo.Persona;
import ec.edu.ups.app.catedra.modelo.Publicacion;


@Stateless
public class ImagenDAO {

	@Inject
	private EntityManager em;
	/**
	 * Este metodo permite guardar una imagen en la base de datos.
	 * 
	 */
	
	public void save(Imagen archivo){
		if(em.find(Imagen.class,archivo.getId())==null)
			insertar(archivo);
		else
			actualizar(archivo);
	}
	
	public void insertar(Imagen archivo){
		em.persist(archivo);
	}
	
	public void actualizar(Imagen archivo){
		em.merge(archivo);
	}
	/**
	 * Este metodo permite generar un lista de todas las imagenes consultadas a la base de datos
	 * 
	 */
	public int CantidadI() {
		String jppql = "SELECT i FROM Imagen i";
		Query query = em.createQuery(jppql, Imagen.class);
		@SuppressWarnings("unchecked")
		List<Imagen> listado = query.getResultList();
		int in=listado.size();
		return in;
	}
	/**
	 * Este metodo permite generar un lista de todas las imagenes consultadas a la base de datos
	 * 
	 */
	public List<Imagen> ListaI() {
		String jppql = "SELECT i FROM Imagen i";
		Query query = em.createQuery(jppql, Imagen.class);
		@SuppressWarnings("unchecked")
		List<Imagen> listado = query.getResultList();
		for(Imagen imagenes: listado) {
		imagenes.getPublicacion().getTexto();
		}
		return listado;
	}
	
	/**
	 * Este metodo permite generar un lista de todas las imagenes consultadas a la base de datos
	 * por el id de la imagen
	 */
	public List<Imagen> ListaIP(int id) {
		
		String jppql = "SELECT p.texto FROM imagen i, publicacion p WHERE i.id= ?";
		Query query = em.createQuery(jppql, Imagen.class);
		query.setParameter(1, id);
		@SuppressWarnings("unchecked")
		List<Imagen> listado = query.getResultList();
		for(Imagen imagenes: listado) {
			//System.err.println(imagenes.getPublicacion().getTexto()+"hola");
		}
		return listado;
	}
	
	
}
