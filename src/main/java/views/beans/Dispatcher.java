package views.beans;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.ejbs.ControllerEjbFactory;
import models.entities.Tema;
import models.entities.Voto;
import models.utils.NivelEstudios;

@WebServlet("/jsp/*")
public class Dispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String PATH_ROOT_VIEW = "/viewsJSP/";

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String action = request.getPathInfo().substring(1);
		String view;
		request.setCharacterEncoding("UTF-8");
		switch (action) {
		case "nuevoTema":
			NuevoTemaView nuevoTemaView = new NuevoTemaView();
			request.setAttribute(action, nuevoTemaView);
			view = action;
			break;
		case "eliminarTema":
			EliminarTemaView eliminarTemaView = new EliminarTemaView();
			eliminarTemaView.setControllerFactory(new ControllerEjbFactory());
			eliminarTemaView.mostrarListaTemas();
			request.setAttribute("temaView", eliminarTemaView);
			view = action;
			break;
		case "añadirVoto":
			AñadirVotoView añadirVotoView = new AñadirVotoView();
			añadirVotoView.setVoto(new Voto());
			añadirVotoView.setControllerFactory(new ControllerEjbFactory());
			añadirVotoView.updateView();
			request.setAttribute("añadirVotoView", añadirVotoView);
			view = action;
			break;
		case "verVotaciones":
			VerVotacionesView verVotacionesView = new VerVotacionesView();
			verVotacionesView.setControllerFactory(new ControllerEjbFactory());
			verVotacionesView.configurarTabla();
			request.setAttribute("votacionesView", verVotacionesView);
			view = action;
			break;
		default:
			view = "home";
		}

		this.getServletContext()
				.getRequestDispatcher(PATH_ROOT_VIEW + view + ".jsp")
				.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getPathInfo().substring(1);
		String view = "home";
		boolean passwordOk = false;
		request.setCharacterEncoding("UTF-8");
		switch (action) {
		case "nuevoTema":
			String pregunta = (request.getParameter("pregunta"));
			String categoria = (request.getParameter("categoria"));
			NuevoTemaView nuevoTemaView = new NuevoTemaView(categoria, pregunta);
			nuevoTemaView.setControllerFactory(new ControllerEjbFactory());
			request.setAttribute(action, nuevoTemaView);
			view = nuevoTemaView.process();
			break;
		case "eliminarTema":
			String password = String.valueOf(request.getParameter("password"));
			EliminarTemaView eliminarTemaView = new EliminarTemaView();
			eliminarTemaView.setPassword(password);
			eliminarTemaView.setControllerFactory(new ControllerEjbFactory());
			passwordOk = eliminarTemaView.checkPassword();
			request.setAttribute("eliminarTemaView", eliminarTemaView);
			view = "home";
			break;

		case "eliminarTemaAutenticado":
			int temaid = Integer.valueOf(request.getParameter("temaABorrar"));
			EliminarTemaView eliminarTemaViewAutenticated = new EliminarTemaView();
			eliminarTemaViewAutenticated
					.setControllerFactory(new ControllerEjbFactory());
			eliminarTemaViewAutenticated.setId(temaid);
			eliminarTemaViewAutenticated.eliminarTema();
			view = "home";
			break;
		case "añadirVoto":
			int temaSeleccionado = Integer.valueOf(request
					.getParameter("temaAVotar"));
			AñadirVotoView añadirVotoView = new AñadirVotoView();
			añadirVotoView.setControllerFactory(new ControllerEjbFactory());
			añadirVotoView.setIdTema(temaSeleccionado);

			String nivel = request.getParameter("nivelEstudios");
			añadirVotoView.setNivelEstudios(NivelEstudios.valueOf(nivel));

			int valoracion = Integer
					.valueOf(request.getParameter("valoracion"));
			añadirVotoView.setValoracion(valoracion);
			
			añadirVotoView.setIp(request.getRemoteAddr());
  
			view = añadirVotoView.saveVoto();

			break;
		default:
			view = "home";
		}

		if (passwordOk) {
			response.sendRedirect("eliminarTema");
		} else {
			this.getServletContext()
					.getRequestDispatcher(PATH_ROOT_VIEW + view + ".jsp")
					.forward(request, response);
		}

	}

}
