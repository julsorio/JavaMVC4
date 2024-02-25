package es.accenture.emisora;

import java.io.IOException;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletEmisora
 */
public class ServletEmisora extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name="jdbc/emisora")
	private DataSource poolConexiones;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEmisora() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String accion = request.getParameter("accion");
    	ControladorObtenerGrupos controladorObtenerGrupos = null;
    	ControladorDetalleGrupo controladorDetalleGrupo = null;
    	ControladorAltaGrupo controladorAltaGrupo = null;
    	ControladorModificar controladorModificar = null;
    	ControladorBajaGrupo controladorBajaGrupo = null;
    	String vista = null;
    	
		if(accion == null) {
			accion = "cargar";
		}

		try {
			switch (accion) {
			case "cargar":
				controladorObtenerGrupos = new ControladorObtenerGrupos(poolConexiones);
				vista = controladorObtenerGrupos.procesarPeticion(request, response);
				break;
			case "detalle":
				controladorDetalleGrupo = new ControladorDetalleGrupo(poolConexiones);
				vista = controladorDetalleGrupo.procesarPeticion(request, response);
				break;
			case "anadir":
			case "alta":
				controladorAltaGrupo = new ControladorAltaGrupo(poolConexiones);
				vista = controladorAltaGrupo.procesarPeticion(request, response);
				break;
			case "modificar":
			case "actualizar":
				controladorModificar = new ControladorModificar(poolConexiones);
				vista = controladorModificar.procesarPeticion(request, response);
				break;
			case "baja":
				controladorBajaGrupo = new ControladorBajaGrupo(poolConexiones);
				controladorBajaGrupo.procesarPeticion(request, response);
				break;
			default:
				controladorObtenerGrupos = new ControladorObtenerGrupos(poolConexiones);
				vista = controladorObtenerGrupos.procesarPeticion(request, response);
			}
			
			despachador(request, response, vista, accion);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    private void despachador(HttpServletRequest request, HttpServletResponse response, String vista, String accion) throws ServletException, IOException  {
    	
    	if(accion.equals("alta") || accion.equals("actualizar") || accion.equals("baja")) {
    		response.sendRedirect("ServletEmisora?accion=cargar");
    	} else {
    		RequestDispatcher dispatcher =  request.getRequestDispatcher(vista);
    		dispatcher.forward(request , response);
    	}
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
