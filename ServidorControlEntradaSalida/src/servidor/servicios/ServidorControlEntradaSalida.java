/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.servicios;

import servidor.utilidades.UtilidadesRegistroC;
import servidor.utilidades.UtilidadesRegistroS;
import servidor.utilidades.UtilidadesConsola;
import java.rmi.RemoteException;
import servidor.Repositorios.EntradaRepositoryImpl;
import servidor.controladores.ControladorGestionarEntradaSalidaImpl;
import servidor.controladores.ControladorGestorReferenciasRemotasAdministradorImpl;
import servidor.controladores.ControladorGestorUsuariosEntradaSalidaInt;

public class ServidorControlEntradaSalida {

    public static void main(String[] args) throws RemoteException {

        int numPuertoRMIRegistry1, numPuertoRMIRegistry2;
        String direccionIpRMIRegistry ;

        System.out.println("Cual es el la direccion ip donde se encuentra el rmiRegistry ");
        direccionIpRMIRegistry = UtilidadesConsola.leerCadena();
        System.out.println("Cual es el numero de puerto por el cual escucha el rmiRegistry del servidor entrada y salida");
        numPuertoRMIRegistry1 = UtilidadesConsola.leerEntero();
        System.out.println("Cual es el numero de puerto por el cual escucha el rmiRegistry del servidor usuarios");
        numPuertoRMIRegistry2 = UtilidadesConsola.leerEntero();

        ControladorGestorReferenciasRemotasAdministradorImpl objRemoto1
                = new ControladorGestorReferenciasRemotasAdministradorImpl();

        ControladorGestorUsuariosEntradaSalidaInt objRemotoServidorUsuarios
                = obtenerReferenciaServidorUsuario(direccionIpRMIRegistry, numPuertoRMIRegistry2, "objServicioGestionUsuariosEntradaSalida");

        EntradaRepositoryImpl objRepositorio = new EntradaRepositoryImpl();
        ControladorGestionarEntradaSalidaImpl objRemoto2 = new ControladorGestionarEntradaSalidaImpl(objRepositorio, objRemotoServidorUsuarios, objRemoto1);//se leasigna el puerto de escucha del objeto remoto

        try {
            UtilidadesRegistroS.arrancarNS(numPuertoRMIRegistry1);
            UtilidadesRegistroS.RegistrarObjetoRemoto(objRemoto1, direccionIpRMIRegistry, numPuertoRMIRegistry1, "objServicioGestionReferencia");
            UtilidadesRegistroS.RegistrarObjetoRemoto(objRemoto2, direccionIpRMIRegistry, numPuertoRMIRegistry1, "objServicioGestionUsuariosEntradaSalida");

        } catch (Exception e) {
            System.err.println("No fue posible Arrancar el NS o Registrar el objeto remoto" + e.getMessage());
        }

    }

    private static ControladorGestorUsuariosEntradaSalidaInt obtenerReferenciaServidorUsuario(String dirIPNS, int puertoNS, String idObjetoRemoto) {
        ControladorGestorUsuariosEntradaSalidaInt objRemoto = (ControladorGestorUsuariosEntradaSalidaInt) UtilidadesRegistroC.obtenerObjRemoto(dirIPNS, puertoNS, idObjetoRemoto);
        return objRemoto;

    }

}
