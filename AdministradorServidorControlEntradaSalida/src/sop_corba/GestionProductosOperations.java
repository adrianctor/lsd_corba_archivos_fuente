package sop_corba;


/**
* sop_corba/GestionProductosOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interface.idl
* s�bado 8 de junio de 2024 10:21:40 PM COT
*/

public interface GestionProductosOperations 
{
  boolean registrarProducto (sop_corba.GestionProductosPackage.productoDTO objProducto);
  sop_corba.GestionProductosPackage.productoDTO consultarProducto (String codigo);
} // interface GestionProductosOperations
