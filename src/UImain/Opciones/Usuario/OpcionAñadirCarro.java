package UImain.Opciones.Usuario;
import UImain.*;
import gestorAplicacion.Usuario.*;
import gestorAplicacion.Administrador.*;
import java.util.*;
public class OpcionAñadirCarro implements OpcionDeMenu {
	
	@Override
	public void ejecutar() {
		Scanner in=new Scanner(System.in);
		if (Main.usuario instanceof Usuario) {
			Usuario user=(Usuario)Main.usuario;
			System.out.println(Main.inventario.toString());
			System.out.println("Ingrese el indice del producto");
			int op=in.nextInt();
			System.out.println("ingrese la cantidad que desea agregar");
			int can=in.nextInt();
			user.getCarro().AddInventario(new Detalle(Main.inventario.getInventario().get(op).getProducto(),can));
		}else {
			System.out.println("No es usuario valido");
		}
	}
	@Override
	public String toString() {
		return "Añadir producto carro";
	}
}
