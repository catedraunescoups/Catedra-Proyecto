package ec.edu.ups.app.catedra.service;


import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ec.edu.ups.app.catedra.datos.ImagenDAO;
import ec.edu.ups.app.catedra.datos.PersonaDAO;
import ec.edu.ups.app.catedra.datos.PublicacionDAO;
import ec.edu.ups.app.catedra.modelo.Imagen;
import ec.edu.ups.app.catedra.modelo.Persona;
import ec.edu.ups.app.catedra.modelo.Publicacion;

@Path("/publicacion")
public class PublicacionWBSRest {
	
		@Inject
		private PublicacionDAO pdao;
		@Inject
		private PersonaDAO perdao;
		@Inject
		private ImagenDAO idao;

		@GET
		@Path("/listarpublicacionesEventos")
		@Produces("application/json")
		public List<Publicacion> getPublicacionesE(){	
			List<Publicacion> lista =  pdao.listadoPublicacionesEventos();
			
			return lista;
		}
		@GET
		@Path("/listarpublicacionesInformacion")
		@Produces("application/json")
		public List<Publicacion> getPublicacionesI(){	
			List<Publicacion> lista = pdao.listadoPublicacionesInformacion();
			return lista;
		}
		@GET
		@Path("/listarpublicacionesNoticias")
		@Produces("application/json")
		public List<Publicacion> getPublicacionesN(){	
			List<Publicacion> lista =pdao.listadoPublicacionesNoticias();
			return lista;
		}
		
		@GET
		@Path("/listarpublicacionesEP")
		@Produces("application/json")
		public List<Publicacion> getPublicacionesNo(){	
			List<Publicacion> lista = pdao.listadoPublicacionesEventos();
			return lista;
		}
		

		@GET
		@Path("/fotoperfil")
		@Produces("application/json")
		public List<Imagen> getImagen (@QueryParam("id") int id){
			return idao.ListaIP(id);
		}
		
}
