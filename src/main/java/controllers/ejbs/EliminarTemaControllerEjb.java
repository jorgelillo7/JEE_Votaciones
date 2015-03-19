package controllers.ejbs;

import java.util.List;

import models.daos.DaoFactory;
import models.daos.TemaDao;
import models.daos.VotoDao;
import models.entities.Tema;
import controllers.EliminarTemaController;

public class EliminarTemaControllerEjb implements EliminarTemaController {

	private final String PASSWORD_CORRECTO = "666";

	@Override
	public boolean deleteTema(Integer id) {
		//TemaDao temaDao = DaoFactory.getFactory().getTemaDao();
		//VotoDao votoDao = DaoFactory.getFactory().getVotoDao();
		try {
			DaoFactory.getFactory().getTemaDao().deleteById(id);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	@Override
	public List<Tema> mostrarTemas() {
		return DaoFactory.getFactory().getTemaDao().findAll();
	}

	@Override
	public boolean passwordCorrecto(String password) {
		if (password.equals(PASSWORD_CORRECTO)) {
			return true;
		} else {
			return false;
		}

	}

}
