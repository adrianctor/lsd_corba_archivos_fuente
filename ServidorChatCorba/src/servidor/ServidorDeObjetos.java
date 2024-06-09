/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package servidor;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import sop_corba.ServidorInt;
import sop_corba.ServidorIntHelper;

/**
 *
 * @author GUILLERMO_PEÑA
 */
public class ServidorDeObjetos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
        String[] vec = new String[4];
        vec[0] = "-ORBInitialHost";
        System.out.println("Ingrese la dirección IP donde escucha el n_s");
        vec[1] = UtilidadesConsola.leerCadena();
        vec[2] = "-ORBInitialPort";
        System.out.println("Ingrese el puerto donde escucha el n_s");
        vec[3] = UtilidadesConsola.leerCadena();
      
      // crea e iniciia el ORB
      ORB orb = ORB.init(vec, null);

      // obtiene la referencia del rootpoa & activate el POAManager
      POA rootpoa = 
      POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
      rootpoa.the_POAManager().activate();

      // crea el servant y lo registra con el ORB
      ServidorImpl convref = new ServidorImpl();
       
      // obtiene la referencia del objeto desde el servant
      org.omg.CORBA.Object obj = 
      rootpoa.servant_to_reference(convref);
      ServidorInt href = ServidorIntHelper.narrow(obj);
	  
      // obtiene la base del contexto de nombrado
      org.omg.CORBA.Object objref =
          orb.resolve_initial_references("NameService");
      // Usa NamingContextExt el cual es parte de la especificacion 
      // Naming Service (INS).
      NamingContextExt ncref = NamingContextExtHelper.narrow(objref);

      // Realiza el binding de la referencia de objeto en el N_S
      String name = "ServidorChat";
      NameComponent path[] = ncref.to_name( name );
      ncref.rebind(path, href);

      System.out.println("El Servidor esta listo y esperando ...");

      // esperan por las invocaciones desde los clientes
      orb.run();
    } 
	
      catch (Exception e) {
        System.err.println("ERROR: " + e);
        e.printStackTrace(System.out);
      }
	  
      System.out.println("Saliendo ...");

    }
    
}
