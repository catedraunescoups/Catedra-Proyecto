package ec.edu.ups.app.catedra.service;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ec.edu.ups.app.catedra.datos.PersonaDAO;
import ec.edu.ups.app.catedra.modelo.Persona;
import ec.edu.ups.app.catedra.modelo.Publicacion;


@Path("/personas")
public class PersonasWBSRest {
	@Inject
	private PersonaDAO pdao;
	
	@POST
	@Path("/guardar")
	@Produces("application/json")
	@Consumes("application/json")
	public Respuesta resgitrarUsuario(Persona usu) {
		Respuesta resp = new Respuesta();
		try {
			pdao.insertar(usu);
			resp.setCodigo(1);
			resp.setMensaje("Registro satisfactorio");
		}catch(Exception e) {
			resp.setCodigo(-1);
			resp.setMensaje("Error en registro");
		}			
		return resp;
	}
	
	
	@GET
	@Path("/perfil")
	@Produces("application/json")
	public List<Persona> getPersona (@QueryParam("email") String email,@QueryParam("contrasena") String contrasena){
		
		return pdao.getPersonasLogin(email, contrasena);
	}
	
	@GET
	@Path("/listarpersonas")
	@Produces("application/json")
	public List<Persona> getPersonas(){		 
		List<Persona> lista = pdao.listadoPersonas();
		return lista;
	}

}
