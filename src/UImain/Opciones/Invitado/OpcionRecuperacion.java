package UImain.Opciones.Invitado;
import java.util.Scanner;
import UImain.OpcionDeMenu;
import gestorAplicacion.Usuario.Persona;
import UImain.Main;
public class OpcionRecuperacion implements OpcionDeMenu {
	private Scanner in = new Scanner(System.in);
	private Persona temp;
	@Override
	public void ejecutar() {
		System.out.println("Usuario:");
		String usu=in.next();
		if (encontrarUsuario(usu)) {
			System.out.println("Responder la Siguiente Pregunta");
			System.out.println(temp.getPregunta());
			String respuesta=in.next();
			if(temp.comprobarRespuesta(respuesta)) {
				System.out.println("La respuesta es correcta, Digitar la contraseña nueva");
				String contraseñaNueva=in.next();
				temp.recuperarContraseña(respuesta, contraseñaNueva);
			}else {
				System.out.println("La respuesta es erronea, vuelva a intentar mas tarde");
			}
		}else {
			System.out.println("Usuario no Encontrado");
		}
	}
	@Override
	public String toString() {
		return "Recuperar Contraseña";
	}
	private boolean encontrarUsuario(String usu) {
		for (int i=0;i<Main.Usuarios.size();i++) {
			temp=Main.Usuarios.get(i);
			if (usu.equals(temp.getUsuario())){
				return true;
			}
		}
		return false;
	}

}
