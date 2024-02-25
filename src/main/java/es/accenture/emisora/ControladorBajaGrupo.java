package es.accenture.emisora;

import javax.sql.DataSource;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * clase controlador que procesa la peticion de baja de un grupo
 * 
 * @author Andrea Ravagli Castillo
 */
public class ControladorBajaGrupo implements IControlador {
	private static final String VISTA_GRUPOS = "GruposMusicales.jsp";

	private ModeloGrupo modelo;
	
	public ControladorBajaGrupo(DataSource dataSource) {
		modelo = new ModeloGrupo(dataSource);
	}

	@Override
	public String procesarPeticion(HttpServletRequest request, HttpServletResponse response) {
		Grupo grupo = null;
		
		int idGrupo = Integer.parseInt(request.getParameter("idGrupo"));
		
		try {
			grupo = modelo.getGrupo(idGrupo);
			modelo.eliminarGrupo(grupo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return VISTA_GRUPOS;
	}

}
