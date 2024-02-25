package es.accenture.emisora;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Interface que sera implementada por los controladores
 * 
 * @author Andrea Ravagli Castillo
 */
public interface IControlador {
	/**
	 * metodo encargado de procesar la peticion
	 * @param request
	 * @param response
	 * @return el nombre de la vista
	 */
	String procesarPeticion(HttpServletRequest request, HttpServletResponse response);
}
