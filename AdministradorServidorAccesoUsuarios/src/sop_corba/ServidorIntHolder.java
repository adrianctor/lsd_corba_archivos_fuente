package sop_corba;

/**
* sop_corba/ServidorIntHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interface.idl
* jueves 30 de mayo de 2024 11:35:08 AM COT
*/

public final class ServidorIntHolder implements org.omg.CORBA.portable.Streamable
{
  public sop_corba.ServidorInt value = null;

  public ServidorIntHolder ()
  {
  }

  public ServidorIntHolder (sop_corba.ServidorInt initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = sop_corba.ServidorIntHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    sop_corba.ServidorIntHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return sop_corba.ServidorIntHelper.type ();
  }

}