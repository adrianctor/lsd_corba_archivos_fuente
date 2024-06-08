package servidor.Repositorios;

import java.util.LinkedList;
import java.util.List;
import servidor.DTO.UsuarioAccesadoDTO;

public class EntradaRepositoryImpl implements EntradaRepositoryInt {

    private final LinkedList<UsuarioAccesadoDTO> identificadores;

    public EntradaRepositoryImpl() {
        this.identificadores = new LinkedList();
    }

    @Override
    public boolean registrarEntrada(UsuarioAccesadoDTO objAccesoUsuarioDTO) {
        return this.identificadores.add(objAccesoUsuarioDTO);
    }

    @Override
    public boolean eliminarEntrada(int prmIdentificacion) {
        boolean bandera = false;
        for (int i = 0; i < this.identificadores.size(); i++) {
            if (this.identificadores.get(i).getIdentificacion() == prmIdentificacion) {
                this.identificadores.remove(i);
                bandera = true;
                break;
            }
        }
        return bandera;
    }

    @Override
    public boolean existeIdentificacion(int prmIdentificacion) {
        boolean bandera = false;
        for (int i = 0; i < this.identificadores.size(); i++) {
            if (this.identificadores.get(i).getIdentificacion() == prmIdentificacion) {
                bandera = true;
                break;
            }
        }
        return bandera;
    }

    @Override
    public List<UsuarioAccesadoDTO> listarUsuariosAccesados() {
        return this.identificadores;
    }

}
