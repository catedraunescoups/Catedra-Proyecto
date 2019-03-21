package ec.edu.ups.app.catedra.datos;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.Part;
import ec.edu.ups.app.catedra.modelo.Imagen;
import ec.edu.ups.app.catedra.modelo.Persona;
import ec.edu.ups.app.catedra.modelo.Publicacion;



/**
 * 
 * @author Christian Flores
 */

@Stateless
public class PublicacionDAO {
	
	
	//variables
	private Part file;
	private Part file2;
	private List<Part> files;
	
	@Inject
	private ImagenDAO daoImg;
	
	@Inject
	private EntityManager em;

	
	//getters an setters

	public void setFile2(Part file2) {
		this.file2 = file2;
	}
	
	public void insertarI(Imagen archivo){
		em.persist(archivo);
	}

	
	public List<Part> getFiles() {
		return files;
	}

	public void setFiles(List<Part> files) {
		this.files = files;
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	//metodos de crud
	public void insertar(Publicacion publicacion) {
		em.persist(publicacion);
	}

	public void actualizar(Publicacion publicacion) {
		em.merge(publicacion);
	}

	public void borrar(int codigo) {
		em.remove(leer(codigo));
	}

	public Publicacion leer(int codigo) {
		return em.find(Publicacion.class, codigo);
	}
	
	
	
	public ImagenDAO getDaoImg() {
		return daoImg;
	}


	public void setDaoImg(ImagenDAO daoImg) {
		this.daoImg = daoImg;
	}


	public Publicacion leer1(String nombre) {
		return em.find(Publicacion.class, nombre);
	}
	/**
	 * Este metodo permite generar un lista de todas las publicaciones consultadas a la base de datos
	 * @return listado
	 */
	public List<Publicacion> listadoPublicaciones() {
		String jppql = "SELECT pu FROM Publicacion pu";
		Query query = em.createQuery(jppql, Publicacion.class);
		@SuppressWarnings("unchecked")
		List<Publicacion> listado = query.getResultList();
		return listado;
	}
	/**
	 * Este metodo permite generar un lista de las publicaciones consultadas a la base de datos
	 * con el parametro de tipo 1
	 * @return listado
	 */
	public List<Publicacion> listadoPublicacionesEventos() {
		String jppql = "SELECT pu FROM Publicacion pu where pu.tipo=1";
		Query query = em.createQuery(jppql, Publicacion.class);
		@SuppressWarnings("unchecked")
		List<Publicacion> listado = query.getResultList();
		 
		return listado;
	}
	/**
	 * Este metodo permite generar un lista de las publicaciones consultadas a la base de datos
	 * con el parametro de tipo 3
	 * @return listado
	 */
	public List<Publicacion> listadoPublicacionesInformacion() {
		String jppql = "SELECT pu FROM Publicacion pu where pu.tipo=3";
		Query query = em.createQuery(jppql, Publicacion.class);
		@SuppressWarnings("unchecked")
		List<Publicacion> listado = query.getResultList();
		
		return listado;
	}
	/**
	 * Este metodo permite generar un lista de las publicaciones consultadas a la base de datos
	 * con el parametro de tipo 2
	 * @return listado
	 */
	public List<Publicacion> listadoPublicacionesNoticias() {
		String jppql = "SELECT pu FROM Publicacion pu where pu.tipo=2";
		Query query = em.createQuery(jppql, Publicacion.class);
		@SuppressWarnings("unchecked")
		List<Publicacion> listado = query.getResultList();
		
		return listado;
	}
	
	/**
	 * Este metodo permite generar un lista de las publicaciones consultadas a la base de datos
	 * con el parametro de fecha
	 * @return listado
	 */
	public List<Publicacion> listadoPubliFecha (String fecha) 
	{
		String jppql = "SELECT pu FROM Publicacion pu where pu.fecha = :fecha";
		Query query = em.createQuery(jppql, Publicacion.class);
		query.setParameter("fecha", fecha);
		@SuppressWarnings("unchecked")
		List<Publicacion> listado1 = query.getResultList();
		
		return listado1;
	}
	
	/**
	 * este metodo permite guardar una publicacion y inmediatamente despues guarda las imagenes(base64) de la publicacion
	 * con el id de la misma.
	 * @param publicacion
	 */
	public void guardar(Publicacion publicacion)
	{
		
		Publicacion pl = leer(publicacion.getId());
		
			try {
			if (pl == null) {
				insertar(publicacion);
				
				for (int i = 0; i < files.size(); i++) 
				{
					file = files.get(i);
					//System.out.println("---------------------------vales"+file.getSize());
				
					int fotoSize = (int)file.getSize();
		        	System.out.println("tamano     "+fotoSize);
						
		        	byte[] foto;
		        	
		        	if(fotoSize>0){
		        		foto = new byte [fotoSize];
		        		//System.out.println("valor     "+foto);
							file.getInputStream().read(foto);
						
						Imagen inm=new Imagen();
						int in = daoImg.CantidadI()+1;
		        		inm.setId(in);
		        		inm.setImagen(foto);
		        		inm.setPublicacion(publicacion);
		        		System.out.println(inm.getId());
		        		insertarI(inm);
		        		
		        	}
				}	
			} 
			else
				actualizar(publicacion);
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}	
		}
	
	public void guardar1(Publicacion publicacion)
	{
		Publicacion pl = leer(publicacion.getId());
		if (pl == null) {
			insertar(publicacion);
        	
		} 
		else
			actualizar(publicacion);
				
		}

	
	/**
	 * Este metodo permite transformar un arreglo de byte a string para poder mostrar la foto al cliente resiviendo como parametro el arreglo de byte.
	 * Y retorna el string con el nombre de la imagen.
	 * @param photo
	 * @return
	 */
	public String convertir (byte[] photo ) 
	{
		String bphoto = Base64.getEncoder().encodeToString(photo);
		return bphoto;
		
	}


	
}