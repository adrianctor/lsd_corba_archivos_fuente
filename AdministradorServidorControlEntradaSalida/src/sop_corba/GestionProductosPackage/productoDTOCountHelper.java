package sop_corba.GestionProductosPackage;


/**
* sop_corba/GestionProductosPackage/productoDTOCountHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interface.idl
* lunes 10 de junio de 2024 12:20:13 PM COT
*/

abstract public class productoDTOCountHelper
{
  private static String  _id = "IDL:sop_corba/GestionProductos/productoDTOCount:1.0";

  public static void insert (org.omg.CORBA.Any a, sop_corba.GestionProductosPackage.productoDTOCount that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static sop_corba.GestionProductosPackage.productoDTOCount extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [2];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = sop_corba.GestionProductosPackage.productoDTOHelper.type ();
          _members0[0] = new org.omg.CORBA.StructMember (
            "producto",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
          _members0[1] = new org.omg.CORBA.StructMember (
            "count",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (sop_corba.GestionProductosPackage.productoDTOCountHelper.id (), "productoDTOCount", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static sop_corba.GestionProductosPackage.productoDTOCount read (org.omg.CORBA.portable.InputStream istream)
  {
    sop_corba.GestionProductosPackage.productoDTOCount value = new sop_corba.GestionProductosPackage.productoDTOCount ();
    value.producto = sop_corba.GestionProductosPackage.productoDTOHelper.read (istream);
    value.count = istream.read_long ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, sop_corba.GestionProductosPackage.productoDTOCount value)
  {
    sop_corba.GestionProductosPackage.productoDTOHelper.write (ostream, value.producto);
    ostream.write_long (value.count);
  }

}