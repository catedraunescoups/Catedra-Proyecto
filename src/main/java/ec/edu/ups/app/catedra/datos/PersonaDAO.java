package ec.edu.ups.app.catedra.datos;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import ec.edu.ups.app.catedra.modelo.Persona;
/**
 * 
 * @author Christian Flores
 */

@Stateless
public class PersonaDAO 
{
	
	@Inject
	private EntityManager em;
	

	/**
	 * Este metodo permite insertar en la base de datos reciviendo un parametro
	 * @param p
	 */
	public void insertar (Persona p) {
		
		em.persist(p);
	}
	/**
	 * Este metodo permite actualizar en la base de datos reciviendo un parametro
	 * @param p
	 */
	
	public void actualizar(Persona p) {
		
		em.merge(p);
	}
	/**
	 * Este metodo permite borrar una personas mediante un parametro
	 * @param id
	 */
	public void borrar(int id) {
		em.remove(leer(id));
	}
	/**
	 * Este metodo permite consultar una personas mediante un parametro
	 * @param id
	 */
	public Persona leer(int id) {
		return em.find(Persona.class, id);
	}
	/**
	 * Este metodo permite generar una lista de todas personas consultadas en la base de datos 
	 * @return persona
	 */
	public List<Persona> listadoPersonas()
	{
		//selects  entidades mapeadas usuario
		String jppql = "SELECT p FROM Persona p";
		Query query = em.createQuery(jppql,Persona.class);
		@SuppressWarnings("unchecked")
		List<Persona> listado =query.getResultList();
		return listado;
	}
	/**
	 * Este metodo permite gerar una lista de personas reciviendo de parametro un correo 
	 * y una contrasena consultados en la base de datos
	 * @return persona
	 */
	@SuppressWarnings("unchecked")
	public List<Persona> getPersonasLogin(String email,String contrasena)
	{
		
		String sql = "SELECT p FROM Persona p "
				+ "WHERE email = ? "
				+" AND contrasena = ?";
	
		Query q = em.createQuery(sql,Persona.class);
		q.setParameter(1, email);
		q.setParameter(2, contrasena);
		List<Persona> persona = q.getResultList();
		return persona;
	}
	
	/**
	 * Este metodo permite gerar una lista de personas reciviendo de parametro un correo 
	 * consultados en la base de datos
	 * @return personas
	 */
	
    @SuppressWarnings("unchecked")
	public List<Persona> getPersonasLoginRC(String correo)
    {
		String sql = "SELECT p FROM Persona p "
				+ "WHERE email = ? ";
		Query q = em.createQuery(sql,Persona.class);
		q.setParameter(1, correo);
		List<Persona> personas = q.getResultList();
		return personas;
	}
    /**
	 * Este metodo permite validar la cedula reciviendo de parametro una cedula 
	 * @return boolean
	 */
    
	public boolean ValidarCedula(String cedula)
    {
		Persona persona = em.find(Persona.class, cedula);
		if(persona == null) {
			return true;
		}
		else
			return false;
	}
	/**
	 * este metodo permite guardar una persona resiviendo como parametro la persona 
	 * y verificando que los campos que resiva sena diferentes de nulos.
	 * @param p
	 */
	public void guardar(Persona p)
	{
		Persona p1 = leer(p.getId());
		try {
		if (p1 == null) {
        		insertar(p);
        	
		} 
		else
			actualizar(p);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
				
		}
}