package UImain.Opciones.Usuario;
import UImain.*;
import java.util.*;

public class OpcionCambiarContraseña implements OpcionDeMenu {
	public void ejecutar() {
		if(Main.usuario!=null) {		
			Scanner Ing= new Scanner (System.in);
			System.out.println("Ingrese su Contraseña:");
			String s=Ing.next();
			if (Main.usuario.comprobarContraseña(s)) {
				System.out.println("Digite su nueva contraseña");
				String k= Ing.next();
				Main.usuario.setContraseña(s, k);
			}
			else {
			System.out.println("Contraseña incorrecta");
			}
		}
		
	}
	

}
