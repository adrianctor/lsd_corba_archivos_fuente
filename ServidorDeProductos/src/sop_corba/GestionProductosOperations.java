package sop_corba;


/**
* sop_corba/GestionProductosOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interface.idl
* lunes 10 de junio de 2024 12:20:13 PM COT
*/

public interface GestionProductosOperations 
{
  boolean registrarProducto (sop_corba.GestionProductosPackage.productoDTO objProducto);
  sop_corba.GestionProductosPackage.productoDTO consultarProducto (String codigo);
  sop_corba.GestionProductosPackage.productoDTOCount[] consultarProductos ();
} // interface GestionProductosOperations
