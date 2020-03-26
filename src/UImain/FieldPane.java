package UImain;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
public class FieldPane extends Pane {
	GridPane child=new GridPane();
	ArrayList<Label> titulos=new ArrayList<Label>();
	ArrayList<TextField> vals=new ArrayList<TextField>();
	public FieldPane(String tituloCriterios, String[] criterios, String tituloValores, String[] valores, boolean[] habilitado) {
		titulos.add(new Label(tituloCriterios));
		titulos.add(new Label(tituloValores));
		child.add(titulos.get(0), 0, 0);
		child.add(titulos.get(1), 1, 0);
		if (habilitado==null) {
			habilitado=new boolean[criterios.length];
			for(int i=0;i<criterios.length;i++) {
				habilitado[i]=true;
			}
		}
		if (valores==null) {
			for(int i=0;i<criterios.length;i++){
				titulos.add(new Label(criterios[i]));
				child.add(titulos.get(2+i), 0, i+1);
				vals.add(new TextField());
				vals.get(i).setEditable(habilitado[i]);
				child.add(vals.get(i), 1, i+1);
			}
		}else {
			for(int i=0;i<criterios.length;i++){
				if(valores[i]==null) {
					titulos.add(new Label(criterios[i]));
					child.add(titulos.get(2+i), 0, i+1);
					vals.add(new TextField());
					vals.get(i).setEditable(habilitado[i]);
					child.add(vals.get(i), 1, i+1);
				}else {
					titulos.add(new Label(criterios[i]));
					child.add(titulos.get(2+i), 0, i+1);
					vals.add(new TextField(valores[i]));
					vals.get(i).setEditable(habilitado[i]);
					child.add(vals.get(i), 1, i+1);
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
		int j=titulos.indexOf(new Label(criterio));
		return String.valueOf(vals.get(j));
	}
	public void setHabilitado(boolean[] habilitado) {
		for(int i=0;i<vals.size();i++) {
			vals.get(i).setEditable(habilitado[i]);
		}
	}
	public GridPane getChild() {
		return child;
	}
} 