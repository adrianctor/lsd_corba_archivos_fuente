package sop_corba;


/**
* sop_corba/ServidorIntOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interface.idl
* lunes 10 de junio de 2024 12:20:13 PM COT
*/

public interface ServidorIntOperations 
{
  boolean registrarCliente (sop_corba.ClienteInt objcllbck, String usuario);
  void enviarMensaje (String usuario, String mensaje);
  boolean desconectarCliente (sop_corba.ClienteInt objcllbck, String usuario);
  sop_corba.ServidorIntPackage.datosUsuario[] obtenerUsuariosConectados ();
} // interface ServidorIntOperations
