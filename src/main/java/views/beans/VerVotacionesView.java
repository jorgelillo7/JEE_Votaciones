package views.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.apache.logging.log4j.LogManager;

import models.entities.Tema;
import models.entities.Voto;
import models.utils.NivelEstudios;
import controllers.ControllerFactory;

@ManagedBean
public class VerVotacionesView {
	@ManagedProperty(value = "#{controllerFactory}")
	private ControllerFactory controllerFactory;

	private String errorMsg;
	private String successMsg;
 
	public List<Tema> temas;
	public List<Long> votosPorTema;
	public List<NivelEstudios> listaNiveles;
	public List<List<String>> medias;


	public List<Long> getVotosPorTema() {
		return votosPorTema;
	}

	public void setVotosPorTema(List<Long> votosPorTema) {
		this.votosPorTema = votosPorTema;
	}

	public List<Tema> getTemas() {
		return temas;
	}

	public void setTemas(List<Tema> temas) {
		this.temas = temas;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getSuccessMsg() {
		return successMsg;
	}

	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}

	public void setControllerFactory(ControllerFactory controllerFactory) {
		this.controllerFactory = controllerFactory;
	}

	public List<NivelEstudios> getListaNiveles() {
		return listaNiveles;
	}

	public void setListaNiveles(List<NivelEstudios> listaNiveles) {
		this.listaNiveles = listaNiveles;
	}
	
	public List<List<String>> getMedias() {
		return medias;
	}

	public void setMedias(List<List<String>> medias) {
		this.medias = medias;
	}

	
	@PostConstruct
	public void configurarTabla() {
		temas = this.controllerFactory.getVerVotacionesController()
				.getListaTemas();
		
		votosPorTema = this.controllerFactory.getVerVotacionesController().getNumeroVotos();
		
		listaNiveles = this.controllerFactory.getVerVotacionesController().getListaNivelEstudios();
		
		medias = this.controllerFactory.getVerVotacionesController().getListaMedia();
	}

}
