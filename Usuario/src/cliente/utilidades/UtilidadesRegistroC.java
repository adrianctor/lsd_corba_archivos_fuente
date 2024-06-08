package cliente.utilidades;

import java.rmi.Naming;
import java.rmi.Remote;

public class UtilidadesRegistroC {

    public static Remote obtenerObjRemoto(String dirIP, int puerto, String nameObjReg) {
        String URLRegistro;
        URLRegistro = "rmi://" + dirIP + ":" + puerto + "/" + nameObjReg;
        try {
            return Naming.lookup(URLRegistro);
        } catch (Exception e) {
            System.out.println("Error: No se pudo obtener el objeto remoto. " + e.getMessage());
            return null;
        }
    }

}
