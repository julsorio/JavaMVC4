package es.accenture.emisora;

import java.util.List;

import javax.sql.DataSource;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Clase encargada de procesar la peticion de mostrar el listado de grupos
 * @author Andrea Ravagli Castillo
 */
public class ControladorObtenerGrupos implements IControlador {
	
	private static final String VISTA_GRUPOS = "GruposMusicales.jsp";
	
	private ModeloGrupo modelo;
	
	public ControladorObtenerGrupos(DataSource dataSource) {
		modelo = new ModeloGrupo(dataSource);
	}

	@Override
	public String procesarPeticion(HttpServletRequest request, HttpServletResponse response) {
		List<Grupo> listaGrupos = null;
		
		try {
			listaGrupos = modelo.getGrupos();
			request.setAttribute("gruposMusicales", listaGrupos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return VISTA_GRUPOS;
	}

}
