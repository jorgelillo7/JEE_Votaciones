package controllers;

public abstract class ControllerFactory {
	public abstract NuevoTemaController getNuevoTemaControler();

	public abstract EliminarTemaController getEliminarTemaController();

	public abstract AņadirVotoController getAņadirVotoController();

	public abstract VerVotacionesController getVerVotacionesController();

}
