package sop_corba;


/**
* sop_corba/ClienteIntOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interface.idl
* s�bado 8 de junio de 2024 10:21:40 PM COT
*/

public interface ClienteIntOperations 
{
  void recibirMensaje (String usuario, String mensaje);
  void recibirContacto (String usuario);
  String obtenerNombre ();
  void borrarListaDeContactos ();
} // interface ClienteIntOperations