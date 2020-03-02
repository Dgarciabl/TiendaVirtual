package UImain;
import java.util.*;
import UImain.OpcionDeMenu;
public class MenuDeConsola {
	//Attributes
	public ArrayList<OpcionDeMenu> opciones;
	//constructors
	public MenuDeConsola(ArrayList<OpcionDeMenu> op) {
		this.opciones=op;
	}
	public MenuDeConsola() {
		this.opciones=new ArrayList<OpcionDeMenu>();
	}
	//Methods
	public void añadirOpcion(OpcionDeMenu op) {
		this.opciones.add(op);
	}
	public void eliminarOpcion(OpcionDeMenu op) {
		this.opciones.remove(op);
	}
	public void lanzarMenu() {
		for (int i=0; i<opciones.size();i++) {
			System.out.println(opciones.get(i).toString());
		}
	}
}
