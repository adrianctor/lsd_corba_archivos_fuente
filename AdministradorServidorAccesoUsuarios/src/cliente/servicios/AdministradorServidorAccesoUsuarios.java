package cliente.servicios;

import cliente.callback.ControladorCallbackImpl;
import cliente.utilidades.UtilidadesRegistroC;
import cliente.vista.Menu;
import java.rmi.RemoteException;
import servidor.DTO.LoginDTO;
import servidor.controladores.ControladorGestorCredencialesUsuariosInt;
import servidor.controladores.ControladorGestorReferenciasRemotasAdministradorInt;
import servidor.controladores.ControladorGestorUsuariosEntradaSalidaInt;

public class AdministradorServidorAccesoUsuarios {

    private static ControladorGestorUsuariosEntradaSalidaInt objRemoto1;
    private static ControladorGestorReferenciasRemotasAdministradorInt objRemoto2;
    private static ControladorGestorCredencialesUsuariosInt objRemoto3;

    public static void main(String[] args) {
        try {
            int numPuertoRMIRegistry1 = 0, numPuertoRMIRegistry2 = 0;
            String direccionIpRMIRegistry = "";

            System.out.println("Cual es el la dirección ip donde se encuentra el rmiregistry ");
            direccionIpRMIRegistry = cliente.utilidades.UtilidadesConsola.leerCadena();
            System.out.println("Cual es el número de puerto por el cual escucha el rmiregistry del servidor de usuarios");
            numPuertoRMIRegistry1 = cliente.utilidades.UtilidadesConsola.leerEntero();
            System.out.println("Cual es el número de puerto por el cual escucha el rmiregistry del servidor de entrada y salida");
            numPuertoRMIRegistry2 = cliente.utilidades.UtilidadesConsola.leerEntero();

            objRemoto1 = (ControladorGestorUsuariosEntradaSalidaInt) UtilidadesRegistroC.obtenerObjRemoto(
                    direccionIpRMIRegistry,
                    numPuertoRMIRegistry1,
                    "objServicioGestionUsuariosEntradaSalida");
            objRemoto2 = (ControladorGestorReferenciasRemotasAdministradorInt) UtilidadesRegistroC.obtenerObjRemoto(
                    direccionIpRMIRegistry,
                    numPuertoRMIRegistry2,
                    "objServicioGestionReferencia");
            objRemoto3 = (ControladorGestorCredencialesUsuariosInt) UtilidadesRegistroC.obtenerObjRemoto(
                    direccionIpRMIRegistry,
                    numPuertoRMIRegistry1, "objServicioGestionCredencialesUsuario");

            ControladorCallbackImpl objRemoteCallBack = new ControladorCallbackImpl();
            objRemoto2.registrarReferencia(objRemoteCallBack);

            System.out.println("\n================ INGRESO DEL SISTEMA =================");
            boolean accesado = false;
            while (!accesado) {
                try {
                    System.out.println("\nIngrese las credenciales asignadas: ");
                    String username = leerCredencial("Usuario: ");
                    String password = leerCredencial("Contrasena: ");

                    LoginDTO objLoginDTO = new LoginDTO(username, password);
                    if (objRemoto3.iniciarSesion(objLoginDTO)) {
                        accesado = true;
                    } else {
                        System.out.println("\nLas credenciales ingresadas son invalidas.");
                    }
                } catch (RemoteException ex) {
                    System.out.println("La operación no se pudo completar, intente nuevamente..." + ex.getMessage());
                }
            }

            Menu objMenu = new Menu(objRemoto1);
            objMenu.ejecutarMenuPrincipal();

        } catch (RemoteException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static String leerCredencial(String msj) {
        boolean bandera = false;
        String credencial;
        do {
            System.out.println(msj);
            credencial = cliente.utilidades.UtilidadesConsola.leerCadena();
            bandera = (credencial.length() >= 8 && credencial.length() <= 15);
            if (!bandera) {
                System.out.println("\nInválido. La credencial debe contener entre 8 y 15 caracteres. Intente nuevamente.\n");
            }
        } while (!bandera);
        return credencial;

    }
}
