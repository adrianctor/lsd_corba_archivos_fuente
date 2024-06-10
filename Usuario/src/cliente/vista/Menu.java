package cliente.vista;

import cliente.servicios.GUICliente;
import cliente.utilidades.UtilidadesConsola;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import servidor.controladores.ControladorGestionarEntradaSalidaInt;
import sop_corba.GestionProductosOperations;
import sop_corba.GestionProductosPackage.productoDTO;

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
            System.out.println("\n|| ============ Menú =========== ||");
            System.out.println("|| 1. Entrar a las instalaciones. ||");
            System.out.println("|| 2. Salir de las instalaciones. ||");
            System.out.println("|| 3. Consultar producto registrado.||");
            System.out.println("|| 4. Iniciar chat con administradores.||");
            System.out.println("|| 5. Salir                       ||");
            System.out.println("Digite la opción:");
            opcion = UtilidadesConsola.leerEntero();
            switch (opcion) {
                case 1:
                    entrarAInstalaciones();
                    break;
                case 2:
                    salirDeInstalaciones();
                    break;
                case 3:
                    consultarProductoRegistrado();
                    break;
                case 4:
                    iniciarChat();
                    break;
                case 5:
                    System.out.println("\nVuelve pronto...");
                    break;
                default:
                    System.out.println("\nError: Opción incorrecta");
            }
        } while (opcion != 4);
    }

    private int leerIdentificacion(String prmMensaje) {
        boolean esIdValido;
        int identificacion;
        String id;
        do {
            System.out.println(prmMensaje);
            identificacion = UtilidadesConsola.leerEntero();
            id = String.valueOf(identificacion);
            esIdValido = (id.length() == 8);
            if (!esIdValido) {
                System.out.println("\nError: El código debe ser de 8 caracteres.\n");
            }
        } while (!esIdValido);

        return identificacion;
    }

    private void entrarAInstalaciones() {
        System.out.println("\n========= Entrada a las instalaciones =========");
        try {
            int identificacion;
            identificacion = leerIdentificacion("Digite la identificación: ");
            int resultado = objRemoto.registrarEntrada(identificacion); //Invocación método remoto
            switch (resultado) {
                case 1:
                    System.out.println("\nError: el usuario no existe.");
                    break;
                case 2:
                    System.out.println("\nError: el usuario existe ya está dentro.");
                    break;
                case 3:
                    System.out.println("\nExito: Acceso concedido.\n");
                    solicitarProducto(String.valueOf(identificacion),true);
                    System.out.println(objRemoto.generarTicket(identificacion, "e"));
                    break;
            }
        } catch (RemoteException e) {
            System.out.println("\nError: La operación no se pudo completar, intente nuevamente...(" + e.getMessage() + ")");
        }
    }

    private void salirDeInstalaciones() {
        System.out.println("\n========= Salida =========");
        try {
            int identificacion;
            identificacion = leerIdentificacion("Digite la identificación: ");
            int respuesta = objRemoto.registrarSalida(identificacion); //Invocación método remoto
            switch (respuesta) {
                case 1:
                    System.out.println("\nError: el usuario no existe.");
                    break;
                case 2:
                    System.out.println("\nError: el usuario existe y no está dentro.");
                    break;
                case 3:
                    System.out.println("\nExito: Salida concedida.\n");
                    solicitarProducto(String.valueOf(identificacion),false);
                    System.out.println(objRemoto.generarTicket(identificacion, "s"));
                    break;
            }
        } catch (RemoteException e) {
            System.out.println("\nError: La operación no se pudo completar, intente nuevamente... (" + e.getMessage() + ")");
        }
    }

    private void consultarProductoRegistrado() {
        System.out.println("\n========= Consultar Producto Registrado =========");
        try {
            String codigo;
            System.out.print("Digite el código del producto: ");
            codigo = UtilidadesConsola.leerCadena();
            System.out.print("\n");
            productoDTO resultado = objRef.consultarProducto(codigo);
            if (!"-1".equals(resultado.codigoUsuario)) {
//                System.out.println("--- Producto Encontrado ---");
                mostrarProducto(resultado);
            } else {
                System.out.println("El producto no existe.");
            }
        } catch (Exception e) {
            System.out.println("\nError: La operación no se pudo completar, intente nuevamente... (" + e.getMessage() + ")");
        }
    }
    
    private void iniciarChat(){
        objChatGrupal = new GUICliente();
        objChatGrupal.setVisible(true);
        
    }
    private void mostrarProducto(productoDTO objProducto) {
        if (objProducto.codigoProducto.equals("-1")) {
            System.out.println("No existe el producto consultado.");
        } else {
            System.out.println(" :: Producto ::");
            System.out.println("Codigo Usuario: " + objProducto.codigoUsuario);
            System.out.println("Codigo Producto: " + objProducto.codigoProducto);
            System.out.println("Tipo Producto: " + objProducto.tipoProducto);
            System.out.println("Fecha Entrada: " + objProducto.fechaEntrada);
            System.out.println("Hora Entrada: " + objProducto.horaEntrada);
            System.out.println("Estado: " + objProducto.estado);
        }
    }

    private void solicitarProducto(String codigoUsuario, boolean estado) {
        int opcion;
        String mensaje;
        if (estado)
            mensaje = "¿Desea registrar un producto? 1 para si, 2 para no";  
        else
            mensaje = "¿Desea recuperar un producto? 1 para si, 2 para no";

        try {
            do {
                System.out.println(mensaje);
                opcion = UtilidadesConsola.leerEntero();
                if (opcion == 2) {
                    break;
                }
                String codigoProd, tipoProd, sFecha, sHora;
                if (estado) {
                    System.out.println("Ingrese el código del producto: ");
                    codigoProd = UtilidadesConsola.leerCadena();
                    System.out.println("Ingrese el tipo del producto: ");
                    tipoProd = UtilidadesConsola.leerCadena();
                    Date fecha = new Date();
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    sFecha = format.format(fecha);
                    SimpleDateFormat formatHour = new SimpleDateFormat("hh:mm");
                    sHora = formatHour.format(fecha);

                    productoDTO producto = new productoDTO(codigoUsuario, tipoProd, codigoProd, sFecha, sHora, estado);
                    //System.out.println(producto.codigoUsuario+"|"+producto.codigoProducto+"|"+producto.tipoProducto+"|"+producto.fechaEntrada+"|"+producto.horaEntrada);
                    boolean bandera = objRef.registrarProducto(producto);
                    if (bandera) {
                        System.out.println("Producto registrado exitosamente!!!");
                    } else {
                        System.out.println("El producto no pudo ser registrado");
                        mensaje = "¿Desea reintentar el registro? 1 para si, 2 para no";
                    }
                    mensaje = "¿Desea registrar otro producto? 1 para si, 2 para no";                    
                }else{
                    System.out.println("Ingrese el código del producto arecuperar: ");
                    codigoProd = UtilidadesConsola.leerCadena();
                    productoDTO objProducto = objRef.consultarProducto(codigoProd);
                    objProducto.estado = estado;
                    boolean bandera = objRef.registrarProducto(objProducto);
                    if (bandera) {
                        System.out.println("Producto recuperado exitosamente!!!");
                    } else {
                        System.out.println("El producto no pudo ser extraer");
                        mensaje = "¿Desea reintentar la recuperacion del producto? 1 para si, 2 para no";
                    }
                    mensaje = "¿Desea recuperar otro producto? 1 para si, 2 para no";
                }
            } while (opcion != 2);

        } catch (Exception e) {
            System.out.println("\nError: La operación no se pudo completar, intente nuevamente... (" + e.getMessage() + ")");
        }
    }
}
