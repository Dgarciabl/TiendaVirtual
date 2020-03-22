package UImain;

import javafx.scene.layout.Pane;

public class FieldPane extends Pane {
	String tituloCriterio;
	String[] criterios;
	String tituloValores;
	String[] valores;
	boolean[] habilitado;
	/**  crea un nuevo objeto de tipo FieldPanel
	 *   @arg tituloCriterios titulo para la columna "Criterio"
	 *   @arg criterios array con los nombres de los criterios
	 *   @arg tituloValores titulo para la columna "valor"
	 *   @arg valores array con los valores iniciales; Si 'null', no hay valores iniciales
	 *   @arg habilitado array con los campos no-editables por el usuario; Si 'null', todos son editables
	 */
	public FieldPane(String tituloCriterios, String[] criterios, String tituloValores, String[] valores, boolean[] habilitado) {
		this.tituloCriterio=tituloCriterios;
		this.criterios=criterios;
		this.tituloValores=tituloValores;
		if(valores==null) {
			this.valores=new String[criterios.length];
		}else {
			this.valores=valores;
		}
		if(habilitado==null) {
			this.habilitado=new boolean[criterios.length];
		}else {
			this.habilitado=habilitado;
		}
	}
	/**  @arg criterio el criterio cuyo valor se quiere obtener
	 *   @return el valor del criterio cuyo nombre es 'criterio'
	*/
	public String getValue(String criterio) {
		return tituloCriterio;
	}
} 