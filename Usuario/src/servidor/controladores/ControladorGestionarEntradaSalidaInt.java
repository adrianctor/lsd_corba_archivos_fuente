
package servidor.controladores;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import servidor.DTO.UsuarioDTO;

//Hereda de la clase Remote, lo cual la convierte en interfaz remota
public interface ControladorGestionarEntradaSalidaInt extends Remote
{
    public int registrarEntrada(int prmIdentificacion) throws RemoteException;

    public int registrarSalida(int prmIdentificacion) throws RemoteException;
    
    public String generarTicket(int prmIdentificacion, String prmAccion) throws RemoteException;
    
    public List<UsuarioDTO> consultarUsuariosAccesados() throws RemoteException;
}