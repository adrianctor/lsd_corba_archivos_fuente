
package servidor.controladores;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import servidor.DTO.UsuarioAccesadoDTO;

public interface ControladorGestionarEntradaSalidaInt extends Remote
{
    public int registrarEntrada(int prmIdentificacion) throws RemoteException;

    public int registrarSalida(int prmIdentificacion) throws RemoteException;
    
    public List<UsuarioAccesadoDTO> consultarUsuariosAccesados() throws RemoteException;
}