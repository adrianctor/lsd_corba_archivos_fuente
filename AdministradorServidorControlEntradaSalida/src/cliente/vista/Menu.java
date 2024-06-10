package cliente.vista;

import cliente.servicios.GUICliente;
import cliente.utilidades.UtilidadesConsola;
import java.rmi.RemoteException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import servidor.DTO.UsuarioAccesadoDTO;
import servidor.controladores.ControladorGestionarEntradaSalidaInt;
import sop_corba.GestionProductosOperations;
import sop_corba.GestionProductosPackage.productoDTO;
import sop_corba.GestionProductosPackage.productoDTOCount;

public class Menu {

    private final ControladorGestionarEntradaSalidaInt objRemoto;
    private final GestionProductosOperations objRef;
    private GUICliente objChatGrupal;

    public Menu(ControladorGestionarEntradaSalidaInt objRemoto, GestionProductosOperations objRef) {
        this.objRemoto = objRemoto;
        this.objRef = objRef;
    }

    public void ejecutarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("\n=============== Menu ==============");
            System.out.println("||1. Consultar usuarios accesados ||");
            System.out.println("||2. Listar productos registrados ||");
            System.out.println("||3. Iniciar chat grupal          ||");
            System.out.println("||4. Salir                        ||");
            System.out.println("Digite una opción:");
            opcion = UtilidadesConsola.leerEntero();
            switch (opcion) {
                case 1:
                    listarUsuariosAccesados();
                    break;
                case 2:
                    listarProductos();
                    break;
                case 3:
                    iniciarChat();
                    break;
                case 4:
                    System.out.println("\nSaliendo...");
                    break;
                default:
                    System.out.println("\nOpción incorrecta");
                    break;
            }
        } while (opcion != 4);
    }

    private void listarUsuariosAccesados() {
        System.out.println("\n========== Listado de usuarios accesados ==========");
        try {

            List<UsuarioAccesadoDTO> lstUsuarios = this.objRemoto.consultarUsuariosAccesados();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm a");
            DateTimeFormatter formatterFecha = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
            if (lstUsuarios != null) {
                System.out.printf("\n%-10s  %-15s  %-10s \n", "Codigo", "Hora Entrada", "Fecha Entrada");
                for (UsuarioAccesadoDTO objUsuarioDTO : lstUsuarios) {                  
                    System.out.printf("%-10s  %-15s  %-10s \n", objUsuarioDTO.getIdentificacion(), objUsuarioDTO.getHoraEntrada().format(formatter), objUsuarioDTO.getFechaEntrada().format(formatterFecha));
                }
            }

            assert lstUsuarios != null;
            System.out.println("\nCantidad de usuarios al interior de las instalaciones: "+lstUsuarios.size());
        } catch (RemoteException e) {
            System.out.println("\nLa operación no se pudo completar, intente nuevamente..." + e.getMessage());
        }
    }
    
    public void listarProductos(){
        System.out.println("\n========== Listado de productos accesados ==========");
        try {
            // Llamada al método remoto para obtener la lista de productos
            productoDTOCount[] productoList = objRef.consultarProductos();
            if (productoList == null || productoList.length == 0) {
                System.out.println("No hay productos para mostrar.");
                return;
            }

            // Formateadores de fecha y hora
            DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm a", new Locale("es", "ES"));
            DateTimeFormatter formatterFecha = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy", new Locale("es", "ES"));

            // Encabezado de la tabla
            System.out.printf("| %-10s | %-10s | %-15s | %-10s | %-15s | %-20s |\n",
                    "Código del Usuario", 
                    "Tipo Producto",
                    "Codigo Producto", 
                    "Estado",
                    "Hora entrada", 
                    "Fecha Entrada");
            System.out.println("|--------------------|---------------|-----------------|------------|-----------------|----------------------|");

            // Iterar sobre la lista de productos e imprimir los detalles
            for (productoDTOCount objDTOProductoCount : productoList) {
                productoDTO objDTOProducto = objDTOProductoCount.producto;

                System.out.printf("|      %-10s    | %-10s    |      %-10s | %-10s | %-15s | %-20s |\n", 
                        objDTOProducto.codigoUsuario, 
                        objDTOProducto.tipoProducto,
                        objDTOProducto.codigoProducto, 
                        objDTOProducto.estado,
                        objDTOProducto.horaEntrada,
                        objDTOProducto.fechaEntrada);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    public void iniciarChat(){
        objChatGrupal = new GUICliente();
        objChatGrupal.setVisible(true);
    }
}
