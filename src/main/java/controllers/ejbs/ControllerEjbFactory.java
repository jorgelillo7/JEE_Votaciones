package controllers.ejbs;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
 
import controllers.AņadirVotoController;
import controllers.ControllerFactory;
import controllers.EliminarTemaController;
import controllers.NuevoTemaController;
import controllers.VerVotacionesController;

@ManagedBean(name = "controllerFactory")
@SessionScoped
public class ControllerEjbFactory extends ControllerFactory {

	private NuevoTemaController nuevoTemaController;
	private EliminarTemaController eliminarTemaController;
	private AņadirVotoController aņadirVotoController;
	private VerVotacionesController verVotacionesController;

	public ControllerEjbFactory() {
	}

	@Override
	public NuevoTemaController getNuevoTemaControler() {
		if (nuevoTemaController == null) {
			nuevoTemaController = new NuevoTemaControllerEjb();
		}
		return nuevoTemaController;
	}

	@Override
	public EliminarTemaController getEliminarTemaController() {
		if (eliminarTemaController == null) {
			eliminarTemaController = new EliminarTemaControllerEjb();
		}
		return eliminarTemaController;
	}

	@Override
	public AņadirVotoController getAņadirVotoController() {
		if (aņadirVotoController == null) {
			aņadirVotoController = new AņadirVotoControllerEjb();
		}
		return aņadirVotoController;
	}

	@Override
	public VerVotacionesController getVerVotacionesController() {
		if (verVotacionesController == null) {
			verVotacionesController = new VerVotacionesControllerEjb();
		}
		return verVotacionesController;
	}

}
