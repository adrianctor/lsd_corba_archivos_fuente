package servidor.controladores;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import servidor.DTO.UsuarioEntradaSalidaDTO;
public interface ControladorGestorUsuariosEntradaSalidaInt extends Remote
{
    public boolean registrarUsuarioEntradaSalida(UsuarioEntradaSalidaDTO objProducto) throws RemoteException;

    public int consultarCantidadUsuariosEntradaSalida() throws RemoteException; 

    public UsuarioEntradaSalidaDTO consultarUsuarioEntradaSalida(int identificacion) throws RemoteException;

    public List<UsuarioEntradaSalidaDTO> listarUsuariosEntradaSalida()throws RemoteException;
}


