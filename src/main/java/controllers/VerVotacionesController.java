package controllers;

import java.util.List;

import models.entities.Tema;
import models.utils.NivelEstudios;

public interface VerVotacionesController {
	List<Tema> getListaTemas();

	List<Long> getNumeroVotos();

	List<NivelEstudios> getListaNivelEstudios();

	List<List<String>> getListaMedia();
}
