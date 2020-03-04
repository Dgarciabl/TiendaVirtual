package UImain.Opciones.Admin;
import UImain.Main;
import java.util.Scanner;
import UImain.OpcionDeMenu;
import gestorAplicacion.Administrador.Administrador;

public class OpcionCrearCategorias implements OpcionDeMenu {
	private Scanner in=new Scanner(System.in);
	@Override
	public void ejecutar() {
		Administrador admon=(Administrador)Main.usuario;
		System.out.println("Nombre de la Categoria:");
		String nombre=in.next();
		if (Main.isNumeric(nombre)) {
			System.out.println("El nombre no puede ser un numero");
		}
		else {
		System.out.println("Descripcion de la Categoria:");
		String descripcion=in.next();
		if (Main.isNumeric(descripcion)) {
			System.out.println("La descripcion no puede ser un numero");
		}
		else {
		admon.crearCategoria(nombre, descripcion);
			}
		}
	}
	@Override
	public String toString() {
		return "Crear Categorias";
	}

}
