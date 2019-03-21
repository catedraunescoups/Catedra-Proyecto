package ec.edu.ups.app.catedra.controlador;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import ec.edu.ups.app.catedra.datos.ImagenDAO;
import ec.edu.ups.app.catedra.datos.PublicacionDAO;
import ec.edu.ups.app.catedra.modelo.Imagen;
import ec.edu.ups.app.catedra.modelo.Publicacion;

@ManagedBean
@RequestScoped
public class PublicacionControler 
{
	//variables
	private Publicacion publicacion;
	private List<Publicacion> publicacionesE;
	private List<Publicacion> publicacionesNo;
	private List<Publicacion> publicacionesI;
	private List<Imagen> publicaciones1;
	private List<Imagen> publicaciones2;
	private List<Imagen> publicaciones3;
	private List<Imagen> images;
	int id;
	
	
	@Inject
	private PublicacionDAO pdao;
	
	@Inject
	private ImagenDAO idao;
	
	/**
	 * metodo para inicializar las variables
	 */
	@PostConstruct
	public void init() {
		publicacion = new Publicacion();
		loadPublicaciones();
	}
	
	/**
	 * Este metodo permite guardar una publicacion al momento de llamar al objeto
	 * pdao que tiene el metodo guardar y recarga el metodo loadPublicaciones, retornando un
	 * String (Listado_Publiciones), siendo este un nombre de un archivo html.
	 * 
	 * @return Listado_Publiciones
	 */
	public String guardarE() 
	{

		try {	
		pdao.guardar(publicacion);
		loadPublicaciones();
		} catch (Exception e1) {
			e1.getMessage();
			
		}
		return "Listado_Publiciones.xhtml";
	}
	/**
	 * Este metodo permite cargar las publicacion al momento de llamar al objeto
	 * pdao que tiene el metodo load y recarga el metodo loadPublicaciones como 
	 * tambien las imagenes que se guardarion anteriormente las mismas que se guanda
	 * en arreglos de objetos que son extraidas de la base de datos y separadas segun su tipo.
	 * 
	 */
	
	public void loadPublicaciones() {
		//System.out.println("estoy aqui");
		Publicacion p1 = new Publicacion();
		Imagen im =new Imagen();
		publicacionesE = pdao.listadoPublicacionesEventos();
		publicacionesNo = pdao.listadoPublicacionesNoticias();
		publicacionesI = pdao.listadoPublicacionesInformacion();
		images = idao.ListaI();
		publicaciones1 = new ArrayList<Imagen>();
		publicaciones2 = new ArrayList<Imagen>();
		publicaciones3 = new ArrayList<Imagen>();
		for (int i = 0; i < images.size(); i++) 
		{
			//System.out.println("--------->"+images.size());
			//System.out.println("---------aqui>"+i);
			im=images.get(i);
			if(im.getPublicacion().getTipo()==1) {
				publicaciones1.add(im);
				//System.out.println("--------->"+i);
			}
			else
				if(im.getPublicacion().getTipo()==2) {
					publicaciones2.add(im);
				}
				else
					if(im.getPublicacion().getTipo()==3) {
						publicaciones3.add(im);
					}
			
		}
	}
	/**
	 * este metodo permite borrar las publicaciones medienate el codigo de cada una 
	 * de las publicaciones y recarga el metodo load.
	 * 
	 * @param codigo
	 */
	public void Borrar1(int codigo) {
		pdao.borrar(codigo);
		loadPublicaciones();
	}

	@PreDestroy
	public void close(){
		System.out.println("Cerrando");
	}

	/**
	 * este metodo se genera cuando al invocar el metodo guardar no se puede
	 * guardar; este metodo nos mostrara la causa por que no se guardo reciviendo un
	 * parametro de excepcion (e) y retornara un String con la informacion del error
	 * 
	 * @param e
	 * @return
	 */
	private String getRootErrorMessage(Exception e) {
		// Default to general error message that registration failed.
		String errorMessage = "Registration failed. See server log for more information";
		if (e == null) {
			// This shouldn't happen, but return the default messages
			return errorMessage;
		}
		// Start with the exception and recurse to find the root cause
		Throwable t = e;
		while (t != null) {
			// Get the message from the Throwable class instance
			errorMessage = t.getLocalizedMessage();
			t = t.getCause();
		}
		// This is the root cause message
		return errorMessage;
	}

	/**
	 * este metodo permite encontrar un objeto a partir de un parametro de busqueda
	 * (codigo) y nos retornara un String (Editar_Publicacion) que es un nombre de una
	 * pagina Xhtml, con todos los datos de la publicacion
	 * 
	 * @param codigo
	 * @return Editar_Publicacion
	 */
	public String listaPublicacionEditar(int codigo) {
		publicacion = pdao.leer(codigo);
		return "Editar_Publicacion.xhtml";
	}
	
	//getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Publicacion getPublicacion() {
		return publicacion;
	}
	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}
	public List<Publicacion> getPublicacionesE() {
		return publicacionesE;
	}
	public void setPublicacionesE(List<Publicacion> publicacionesE) {
		this.publicacionesE = publicacionesE;
	}
	public List<Publicacion> getPublicacionesNo() {
		return publicacionesNo;
	}
	public void setPublicacionesNo(List<Publicacion> publicacionesNo) {
		this.publicacionesNo = publicacionesNo;
	}
	public List<Publicacion> getPublicacionesI() {
		return publicacionesI;
	}
	public void setPublicacionesI(List<Publicacion> publicacionesI) {
		this.publicacionesI = publicacionesI;
	}
	public PublicacionDAO getPdao() {
		return pdao;
	}
	public void setPdao(PublicacionDAO pdao) {
		this.pdao = pdao;
	}
	public List<Imagen> getPublicaciones1() {
		return publicaciones1;
	}
	public void setPublicaciones1(List<Imagen> publicaciones1) {
		this.publicaciones1 = publicaciones1;
	}
	public List<Imagen> getPublicaciones2() {
		return publicaciones2;
	}
	public void setPublicaciones2(List<Imagen> publicaciones2) {
		this.publicaciones2 = publicaciones2;
	}
	public List<Imagen> getPublicaciones3() {
		return publicaciones3;
	}
	public void setPublicaciones3(List<Imagen> publicaciones3) {
		this.publicaciones3 = publicaciones3;
	}
	public ImagenDAO getIdao() {
		return idao;
	}
	public void setIdao(ImagenDAO idao) {
		this.idao = idao;
	}
	public void setImages(List<Imagen> images) {
		this.images = images;
	}
	public List<Imagen> getImages() {
        return images;
    }
	
	
}