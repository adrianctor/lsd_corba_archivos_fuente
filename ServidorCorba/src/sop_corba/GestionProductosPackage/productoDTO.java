package sop_corba.GestionProductosPackage;


/**
* sop_corba/GestionProductosPackage/productoDTO.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interface.idl
* jueves 23 de mayo de 2024 11:46:09 AM COT
*/

public final class productoDTO implements org.omg.CORBA.portable.IDLEntity
{
  public String codigoUsuario = null;
  public String tipoProducto = null;
  public String codigoProducto = null;
  public String fechaEntrada = null;
  public String horaEntrada = null;

  public productoDTO ()
  {
  } // ctor

  public productoDTO (String _codigoUsuario, String _tipoProducto, String _codigoProducto, String _fechaEntrada, String _horaEntrada)
  {
    codigoUsuario = _codigoUsuario;
    tipoProducto = _tipoProducto;
    codigoProducto = _codigoProducto;
    fechaEntrada = _fechaEntrada;
    horaEntrada = _horaEntrada;
  } // ctor

} // class productoDTO