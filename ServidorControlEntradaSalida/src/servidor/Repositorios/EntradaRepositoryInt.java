
package servidor.Repositorios;

import java.util.List;
import servidor.DTO.UsuarioAccesadoDTO;

public interface EntradaRepositoryInt
{    
    public boolean registrarEntrada(UsuarioAccesadoDTO objAccesoUsuarioDTO);
    public boolean eliminarEntrada(int prmIdentificacion);
    public boolean existeIdentificacion(int prmIdentificacion);
    public List<UsuarioAccesadoDTO> listarUsuariosAccesados();
}


