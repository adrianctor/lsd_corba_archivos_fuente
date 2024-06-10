package cliente.servicios;

import cliente.utilidades.UtilidadesConsola;
import cliente.utilidades.UtilidadesRegistroC;
import cliente.vista.Menu;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import servidor.controladores.ControladorGestionarEntradaSalidaInt;
import sop_corba.GestionProductosHelper;
import sop_corba.GestionProductosOperations;

public class AdministradorServidorControlEntradaSalida
{

    private static ControladorGestionarEntradaSalidaInt objRemoto;
    private static GestionProductosOperations ref;

    public static void main(String[] args) throws InvalidName
    {
        try {
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

            String[] vec = new String[4];
                vec[0] = "-ORBInitialHost";
                vec[1] = direccionIpRMIRegistry;
                vec[2] = "-ORBInitialPort";
                System.out.println("Ingrese el puerto donde escucha el n_s");
                vec[3] = UtilidadesConsola.leerCadena();

                // se crea e inicia el ORB
                ORB orb = ORB.init(vec, null);

                // se obtiene un link al name service
                org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
                NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

                // *** Resuelve la referencia del objeto en el N_S ***
                String name = "objProductos";
                ref = GestionProductosHelper.narrow(ncRef.resolve_str(name));

                System.out.println("Obtenido el manejador sobre el servidor de objetos: " + ref);
                ref.consultarProducto("123");
            Menu objMenu= new Menu(objRemoto,ref);
            objMenu.ejecutarMenuPrincipal();            
        } catch (InvalidName | CannotProceed | org.omg.CosNaming.NamingContextPackage.InvalidName | NotFound e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace(System.out);
        }
    }
}