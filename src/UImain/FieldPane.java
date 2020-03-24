package UImain;

import javafx.scene.layout.Pane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
public class FieldPane extends Pane {
	GridPane child=new GridPane();
	public FieldPane(String tituloCriterios, String[] criterios, String tituloValores, String[] valores, boolean[] habilitado) {
		child.add(new Label(tituloCriterios), 0, 0);
		child.add(new Label(tituloValores), 1, 0);
		if (habilitado==null) {
			habilitado=new boolean[criterios.length];
			for(int i=0;i<criterios.length;i++) {
				habilitado[i]=true;
			}
		}
		if (valores==null) {
			for(int i=0;i<criterios.length;i++){
				child.add(new Label(criterios[i]), 0, i+1);
				TextField hijo=new TextField();
				hijo.setEditable(habilitado[i]);
				child.add(hijo, 1, i+1);
			}
		}else {
			for(int i=0;i<criterios.length;i++){
				if(valores[i]==null) {
					child.add(new Label(criterios[i]), 0, i+1);
					TextField hijo=new TextField();
					hijo.setEditable(habilitado[i]);
					child.add(hijo, 1, i+1);
				}else {
					child.add(new Label(criterios[i]), 0, i+1);
					TextField hijo=new TextField(valores[i]);
					hijo.setEditable(habilitado[i]);
					child.add(hijo, 1, i+1);
				}
			}
		}
		child.setAlignment(Pos.CENTER);
		child.setHgap(10);
		child.setVgap(8);
		child.setPadding(new Insets(8,8,8,8));
	}
	/**  @arg criterio el criterio cuyo valor se quiere obtener
	 *   @return el valor del criterio cuyo nombre es 'criterio'
	*/
	public String getValue(String criterio) {
		return "";
	}
	public GridPane getChild() {
		return child;
	}
} 