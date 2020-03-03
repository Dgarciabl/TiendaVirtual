package UImain.Opciones.Invitado;
import java.util.Scanner;
import gestorAplicacion.Usuario.Persona;
import gestorAplicacion.Administrador.Administrador;
import UImain.Main;
import UImain.OpcionDeMenu;
public class OpcionInicioSesion implements OpcionDeMenu {
	private Scanner in=new Scanner(System.in);
	private Persona temp;
	@Override
	public void ejecutar() {
		Sesion();
		if (Sesion()) {
			Main.usuario=temp;
			if(temp instanceof Administrador) {
				Main.nivel=0;
			}else {
				Main.nivel=1;
			}
			
		}else {
			System.out.println("Clave o usuario Errado");
		}
	}
	private boolean Sesion() {
		System.out.println("Usuario:");
		String usu=in.next();
		System.out.println("Contraseña:");
		String key=in.next();
		if (InicioSesion(usu, key)) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public String toString() {
		return "Iniciar Sesion";
	}
	private boolean InicioSesion(String usu, String key) {
		for (int i=0;i<Main.Usuarios.size();i++) {
			temp=Main.Usuarios.get(i);
			if (usu.equals(temp.getUsuario())){
				if (temp.comprobarContraseña(key)) {
					return true;
				}
			}
		}
		return false;
	}
}