package cliente.servicios;

import cliente.utilidades.UtilidadesRegistroC;
import cliente.vista.Menu;
import servidor.controladores.ControladorGestionarEntradaSalidaInt;

public class AdministradorServidorControlEntradaSalida
{

    private static ControladorGestionarEntradaSalidaInt objRemoto;

    public static void main(String[] args)
    {
        int numPuertoRMIRegistry = 0;
        String direccionIpRMIRegistry = "";        

        System.out.println("Cual es el la direccion ip donde se encuentra el rmiregistry ");
        direccionIpRMIRegistry = cliente.utilidades.UtilidadesConsola.leerCadena();
        System.out.println("Cual es el numero de puerto por el cual escucha el rmiregistry ");
        numPuertoRMIRegistry = cliente.utilidades.UtilidadesConsola.leerEntero(); 

        objRemoto = (ControladorGestionarEntradaSalidaInt) UtilidadesRegistroC.obtenerObjRemoto(
                direccionIpRMIRegistry,
                numPuertoRMIRegistry, 
                "objServicioGestionUsuariosEntradaSalida");
        Menu objMenu= new Menu(objRemoto);
        objMenu.ejecutarMenuPrincipal();
    }
}