package es.accenture.emisora;

import javax.sql.DataSource;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Clase encargada de procesar la peticion de mostrar el detalle del grupo
 * 
 * @author Andrea Ravagli Castillo
 */
public class ControladorDetalleGrupo implements IControlador {
	private static final String VISTA_DETALLE = "DetalleGrupo.jsp";
	
	private ModeloGrupo modelo;
	
	public ControladorDetalleGrupo(DataSource dataSource) {
		modelo = new ModeloGrupo(dataSource);
	}

	@Override
	public String procesarPeticion(HttpServletRequest request, HttpServletResponse response) {
		Grupo grupo = null;
		
		int idGrupo = Integer.parseInt(request.getParameter("idGrupo"));
		
		try {
			grupo = modelo.getGrupo(idGrupo);
			modelo.getComponentes(grupo);
			request.setAttribute("detalleGrupo", grupo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return VISTA_DETALLE;
	}

}
