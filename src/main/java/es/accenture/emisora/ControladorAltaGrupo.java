package es.accenture.emisora;

import javax.sql.DataSource;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * clase controlador que procesa la peticion de alta de grupo
 * 
 * @author Andrea Ravagli Castillo
 */
public class ControladorAltaGrupo implements IControlador {
	private static final String VISTA_ALTA = "NuevoGrupo.jsp";
	private static final String VISTA_GRUPOS = "GruposMusicales.jsp";

	private ModeloGrupo modelo;
	
	public ControladorAltaGrupo(DataSource dataSource) {
		modelo = new ModeloGrupo(dataSource);
	}

	@Override
	public String procesarPeticion(HttpServletRequest request, HttpServletResponse response) {
		if(request.getAttribute("nombre") == null && request.getParameter("origen") == null && request.getParameter("creacion") == null && request.getParameter("genero") == null && request.getParameter("discografica") == null) {
			//se retorna el nombre de la vista para el alta de grupo
			return VISTA_ALTA;
		} else {
			//se procesan los datos para ser insertados
			try {
				String nombre = request.getParameter("nombre");
				String origen = request.getParameter("origen");
				int creacion = Integer.parseInt(request.getParameter("creacion"));
				String genero = request.getParameter("genero");
				int idDiscografica = Integer.parseInt(request.getParameter("discografica"));
				
				Grupo grupo = new Grupo();
				grupo.setNombre(nombre);
				grupo.setOrigen(origen);
				grupo.setCreacion(creacion);
				grupo.setGenero(genero);
				grupo.setIdDiscografica(idDiscografica);
				
				modelo.insertarGrupo(grupo);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return VISTA_GRUPOS;
		}
		
		
	}

}
