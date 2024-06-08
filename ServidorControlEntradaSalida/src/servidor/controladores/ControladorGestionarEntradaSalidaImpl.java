package servidor.controladores;

import cliente.DTO.EventoDTO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import servidor.DTO.UsuarioEntradaSalidaDTO;
import servidor.DTO.UsuarioAccesadoDTO;
import servidor.Repositorios.EntradaRepositoryInt;

public class ControladorGestionarEntradaSalidaImpl extends UnicastRemoteObject implements ControladorGestionarEntradaSalidaInt {

    private final EntradaRepositoryInt objEntradasRepository;
    private final ControladorGestorUsuariosEntradaSalidaInt objRemotoServidorUsuarios;
    private final ControladorGestorReferenciasRemotasAdministradorImpl objRemotoRefsAdmin;

    public ControladorGestionarEntradaSalidaImpl(
            EntradaRepositoryInt objEntradasRepository,
            ControladorGestorUsuariosEntradaSalidaInt objRemotoServidorUsuarios,
            ControladorGestorReferenciasRemotasAdministradorImpl objRemotoRefsAdmin) throws RemoteException {
        super(); //se asigna un puerto de escucha al OR
        this.objEntradasRepository = objEntradasRepository;
        this.objRemotoServidorUsuarios = objRemotoServidorUsuarios;
        this.objRemotoRefsAdmin = objRemotoRefsAdmin;
    }

    @Override
    public int registrarEntrada(int prmIdentificacion) throws RemoteException {
        System.out.println("\nIniciando proceso de verificacion de identificación: " + prmIdentificacion + "...");
        int codigo = 0;
        EventoDTO objEventoDTO = null;
        UsuarioEntradaSalidaDTO objUsuarioDTO = objRemotoServidorUsuarios.consultarUsuarioEntradaSalida(prmIdentificacion);
        UsuarioAccesadoDTO objAccesoUsuarioDTO;
        if (objUsuarioDTO == null) {
            codigo = 1;
            objEventoDTO = new EventoDTO("Entrada no exitosa, el usuario " + prmIdentificacion + " no existe", "Entrada");
        } else if (objEntradasRepository.existeIdentificacion(prmIdentificacion)) {
            //Si el usuario existe y esta adentro retorna 2
            codigo = 2;
            objEventoDTO = new EventoDTO("Entrada no exitosa, el usuario " + prmIdentificacion + " esta dentro", "Entrada");
        } else {
            //Si el usuario existe y no esta adentro se retorna 3
            objAccesoUsuarioDTO = new UsuarioAccesadoDTO(prmIdentificacion);
            objEntradasRepository.registrarEntrada(objAccesoUsuarioDTO);
            codigo = 3;
            objEventoDTO = new EventoDTO("Entrada exitosa del usuario " + prmIdentificacion, "Entrada");
        }

        this.objRemotoRefsAdmin.notificar(objEventoDTO, objUsuarioDTO);
        return codigo;
    }

    @Override
    public int registrarSalida(int prmIdentificacion) throws RemoteException {
        System.out.println("\nIniciando eliminación de acceso de identificación: " + prmIdentificacion + "...");
        int codigo = 0;
        EventoDTO objEventoDTO;
        UsuarioEntradaSalidaDTO objUsuarioDTO = objRemotoServidorUsuarios.consultarUsuarioEntradaSalida(prmIdentificacion);
        if (objUsuarioDTO == null) {
            codigo = 1;
            objEventoDTO = new EventoDTO("Info: Salida no exitosa, el usuario " + prmIdentificacion + " no existe", "Salida");
        } else if (!objEntradasRepository.existeIdentificacion(prmIdentificacion)) {
            //Si el usuario existe y no esta adentro retorna 2
            codigo = 2;
            objEventoDTO = new EventoDTO("Info: Salida no exitosa, el usuario " + prmIdentificacion + " no está dentro", "Salida");
        } else {
            //Si el usuario existe y esta adentro se retorna 3
            objEntradasRepository.eliminarEntrada(prmIdentificacion);
            objEventoDTO = new EventoDTO("Info: Salida exitosa del usuario " + prmIdentificacion, "Salida");
            codigo = 3;
        }

        this.objRemotoRefsAdmin.notificar(objEventoDTO, objUsuarioDTO);
        return codigo;
    }

    @Override
    public List<UsuarioAccesadoDTO> consultarUsuariosAccesados() throws RemoteException {
        System.out.println("\nIniciando proceso de obtencion de listado de usuarios dentro de las instalaciones...");

        return this.objEntradasRepository.listarUsuariosAccesados();
    }

    @Override
    public String generarTicket(int prmIdentificacion, String prmAccion) throws RemoteException {
        UsuarioEntradaSalidaDTO objUsuarioDTO = this.objRemotoServidorUsuarios.consultarUsuarioEntradaSalida(prmIdentificacion);
        List<UsuarioAccesadoDTO> objLstAccesoUsuarioDTO = this.objEntradasRepository.listarUsuariosAccesados();

        UsuarioAccesadoDTO accesoUsuarioDTO = null;
        for (int i = 0; i < objLstAccesoUsuarioDTO.size(); i++) {
            if (objLstAccesoUsuarioDTO.get(i).getIdentificacion() == prmIdentificacion) {
                accesoUsuarioDTO = objLstAccesoUsuarioDTO.get(i);
                break;
            }
        }
        String datos = objUsuarioDTO.getRol() + "\n"
                + objUsuarioDTO.getNombre() + " " + objUsuarioDTO.getApellidos() + "\n";
        String concat;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm: a");
        //TODO: Locale deprecadao
        DateTimeFormatter formatterFecha = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
        DateTimeFormatter formatterS = DateTimeFormatter.ofPattern("h:mm a  dd 'de' MMMM 'de' yyyy");
        if (prmAccion.equals("e")) {
            assert accesoUsuarioDTO != null;
            concat = datos.concat("Hora y fecha de acceso: " + accesoUsuarioDTO.getHoraEntrada().format(formatter) + " " + accesoUsuarioDTO.getFechaEntrada().format(formatterFecha));
        } else {
            LocalDateTime fechaDeSalida = LocalDateTime.now();
            concat = datos.concat("Hora y fecha de salida: " + fechaDeSalida.format(formatterS));
        }

        return concat;
    }

}
