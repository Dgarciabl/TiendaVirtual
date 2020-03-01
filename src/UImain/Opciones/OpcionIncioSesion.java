package UImain.Opciones;
import java.util.Scanner;
import java.util.ArrayList;
import UImain.OpcionDeMenu;
import gestorAplicacion.Usuario.Persona;
public class OpcionIncioSesion implements OpcionDeMenu {
	private Scanner in=new Scanner(System.in);
	private ArrayList<Persona> db;
	private boolean access=false;
	@Override
	public void ejecutar() {
		Sesion();
		if (!access) {
			System.out.println("Clave o usuario Errado");
		}else {
			
		}
	}
	public void Sesion() {
		System.out.println("Usuario:");
		String usu=in.next();
		System.out.println("Contraseña:");
		String key=in.next();
		if (InicioSesion(usu, key)) {
			access=true;
		}
	}
	@Override
	public String toString() {
		return "Iniciar Sesion";
	}
	private boolean InicioSesion(String usu, String key) {
		for (int i=0;i<db.size();i++) {
			Persona temp=db.get(i);
			if (usu.equals(temp.getUsuario())){
				if (temp.comprobarContraseña(key)) {
					return true;
				}
			}
		}
		return false;
	}
	public void cargarBase(ArrayList<Persona> db) {
		this.db=db;
	}
}