package servidor.controladores;

import cliente.callback.ControladorCallbackInt;
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface ControladorGestorReferenciasRemotasAdministradorInt extends Remote
{
    public boolean registrarReferencia(ControladorCallbackInt referencia) throws RemoteException;
}


