package es.accenture.emisora;

import javax.sql.DataSource;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * clase controlador que procesa la peticion de modificar un grupo
 * 
 * @author Andrea Ravagli Castillo
 */
public class ControladorModificar implements IControlador {
	private static final String VISTA_MODIFICAR = "ActualizarGrupo.jsp";
	private static final String VISTA_GRUPOS = "GruposMusicales.jsp";

	private ModeloGrupo modelo;
	
	public ControladorModificar(DataSource dataSource) {
		modelo = new ModeloGrupo(dataSource);
	}

	@Override
	public String procesarPeticion(HttpServletRequest request, HttpServletResponse response) {
		if(request.getParameter("accion") != null && request.getParameter("accion").equals("modificar")) {
			//se retorna el nombre de la vista para modificar el grupo
			int idGrupo = Integer.parseInt(request.getParameter("idGrupo"));
			Grupo grupo = null;
			
			try {
				grupo = modelo.getGrupo(idGrupo);
				request.setAttribute("datosGrupo", grupo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return VISTA_MODIFICAR;
		} else {
			//se procesan los datos para ser insertados
			try {
				int idGrupo = Integer.parseInt(request.getParameter("id"));
				String nombre = request.getParameter("nombre");
				String origen = request.getParameter("origen");
				int creacion = Integer.parseInt(request.getParameter("creacion"));
				String genero = request.getParameter("genero");
				int idDiscografica = Integer.parseInt(request.getParameter("discografica"));
				
				Grupo grupo = new Grupo();
				grupo.setId(idGrupo);
				grupo.setNombre(nombre);
				grupo.setOrigen(origen);
				grupo.setCreacion(creacion);
				grupo.setGenero(genero);
				grupo.setIdDiscografica(idDiscografica);
				
				modelo.actualizarGrupo(grupo);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return VISTA_GRUPOS;
		}
	}

}
