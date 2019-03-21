package ec.edu.ups.app.catedra.controlador;

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
import javax.servlet.http.HttpSession;

import ec.edu.ups.app.catedra.datos.PersonaDAO;
import ec.edu.ups.app.catedra.modelo.Persona;
import ec.edu.ups.app.catedra.util.SessionUtils;
import ec.edu.ups.app.catedra.util.Validar;

/**
 * @author Christian Flores
 */

@ManagedBean
@RequestScoped
public class PersonaControler {
	//Declaracion de Varibles.
	private int codigo;
	private Validar v;
	private String id;
	private List<Persona> personas;
	private String correoI;
	private String claveI;
	private List<Persona> listadoLogin;
	private String contraseñaA;
	private String contraseñaN;

	@Inject
	private PersonaDAO pdao;

	@Inject
	private Sesion sesion;
	
	@Inject
	private Persona persona;

	//Constructor
	@PostConstruct
	public void init() {
		persona = new Persona();
		loadPersonas();
		v = new Validar();
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	/*
	 * metodos para crud
	 */
	/**
	 * Este metodo permite guardar un usuario al momento de llamar al objeto
	 * pdao que tiene el metodo guardar verifica la cedula y le setea un rol 
	 * el mismo que servira para el logeo y recarga el metodo loadUsuario, retornando un
	 * String (Logueo), siendo este un nombre de un archivo html.
	 * 
	 * @return ListarPersonas
	 */
	 @SuppressWarnings("static-access")
	public String Guardar() {
		System.out.println("Entra aqui");

		boolean t = v.validacionCedula(persona.getCedula());

		if (t == false) {
			return "ListarPersonas.xhtml";
		} else {
			persona.setRol(3);
			pdao.guardar(persona);
			sesion.setUsuario(persona);
			loadPersonas();
			return "ListarPersonas.xhtml";
		}
	}
	 /**
		 * Este metodo permite guardar un usuario al momento de llamar al objeto
		 * pdao que tiene el metodo guardar verifica la cedula y le setea un rol 
		 * el mismo que servira para el logeo y recarga el metodo loadUsuario, retornando un
		 * String (Logueo), siendo este un nombre de un archivo html.
		 * 
		 * @return ListarPersonas
		 */
	public String Guardar1() {
		persona.setRol(2);
		pdao.guardar(persona);
		loadPersonas();
		return "ListarPersonas.xhtml";
	}

	/**
	 * este metodo permite encontrar un objeto a partir de un parametro de busqueda
	 * (cedula) y nos retornara un String (EditarPersonas que es un nombre de una pagina
	 * Xhtml
	 * 
	 * @param cedula
	 * @return
	 */
	public String listadatosEditar(int cedula) {
		persona = pdao.leer(cedula);
		//System.out.println("Cuenca " + persona.getNombre());
		return "EditarPersonas.xhtml";
	}

	/**
	 * este metodo permite agregar los usuarios a una lista para luego mostrarlos.
	 * 
	 * @param
	 * @return
	 */
	public void loadPersonas() {
		personas = pdao.listadoPersonas();
	}

	/**
	 * Este metodo recibe un parametro (cedula de Usuario) y este llama al objeto
	 * pdao(pdao de la clase PersonaDAO) y se le pase el parametro cedula para borrar 
	 * el usuario y recarga el metodo loadUsuarios
	 * 
	 * @param cedula
	 */
	public void Borrar(int cedula) {
		pdao.borrar(cedula);
		loadPersonas();
	}

	/**
	 * este metodo carga el usuario dado el parametro de correo y clave de inico
	 * luego verificar que tipo de usuario es con el campo rol este
	 * campo rol permitira ver si el usuario que se registra es administrador,
	 * usuarioy retorna un String (Listado_Publiciones).
	 * 
	 * @return Listado_Publiciones
	 */
	@SuppressWarnings({ "rawtypes" })
	public String listar() {
		personas = pdao.listadoPersonas();
		listadoLogin = pdao.getPersonasLogin(correoI, claveI);
		System.out.println(listadoLogin);
		
		for (int i = 0; i < listadoLogin.size(); i++) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", correoI);
			codigo = listadoLogin.get(i).getId();
			System.out.println(codigo);
			if (listadoLogin.get(i).getRol() == 1) {
				
				return " ";
			} else if (listadoLogin.get(i).getRol() == 2) {
				sesion.setUsuario(listadoLogin.get(i));
				return " ";
			} else if (listadoLogin.get(i).getRol() == 3) {
				sesion.setUsuario(listadoLogin.get(i));
			}
			return "Listado_Publiciones";

		}
		return null;
	}

		/**
	 * Este metodo realiza la validacion de la sesion y este llama al objeto
	 * pdao(pdao de la clase PersonaDAO) y retorna un String Login.
	 * 
	 */
	
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "Login.xhtml";
	}
	

	@PreDestroy
	public void close() {
		System.out.println("Cerrando");
	}

	/*
	 * getters and setters
	 */

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Persona> getUsuarios() {
		return personas;
	}

	public void setUsuarios(List<Persona> personas) {
		this.personas = personas;
	}

	public String getCorreoI() {
		return correoI;
	}

	public void setCorreoI(String correoI) {
		this.correoI = correoI;
	}

	public String getClaveI() {
		return claveI;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void setClaveI(String claveI) {
		this.claveI = claveI;
	}

	public String getContraseñaA() {
		return contraseñaA;
	}

	public void setContraseñaA(String contraseñaA) {
		this.contraseñaA = contraseñaA;
	}

	public String getContraseñaN() {
		return contraseñaN;
	}

	public void setContraseñaN(String contraseñaN) {
		this.contraseñaN = contraseñaN;
	}

	public PersonaDAO getPdao() {
		return pdao;
	}

	public void setPdao(PersonaDAO pdao) {
		this.pdao = pdao;
	}

}