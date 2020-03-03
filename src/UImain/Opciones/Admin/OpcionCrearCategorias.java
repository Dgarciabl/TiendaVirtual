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
		System.out.println("Descripcion de la Categoria:");
		String descripcion=in.next();
		admon.crearCategoria(nombre, descripcion);
	}
	@Override
	public String toString() {
		return "Crear Categorias";
	}

}
