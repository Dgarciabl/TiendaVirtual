package UImain.Opciones.Usuario;
import UImain.*;
import java.util.*;

public class OpcionCambiarContrase�a implements OpcionDeMenu {
	public void ejecutar() {
		if(Main.usuario!=null) {		
			Scanner Ing= new Scanner (System.in);
			System.out.println("Ingrese su Contrase�a:");
			String s=Ing.next();
			if (Main.usuario.comprobarContrase�a(s)) {
				System.out.println("Digite su nueva contrase�a");
				String k= Ing.next();
				Main.usuario.setContrase�a(s, k);
			}
			else {
			System.out.println("Contrase�a incorrecta");
			}
		}
		
	}
	

}
