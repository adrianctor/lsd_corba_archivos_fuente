package sop_corba;


/**
* sop_corba/ServidorIntHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interface.idl
* domingo 9 de junio de 2024 04:46:13 PM COT
*/

abstract public class ServidorIntHelper
{
  private static String  _id = "IDL:sop_corba/ServidorInt:1.0";

  public static void insert (org.omg.CORBA.Any a, sop_corba.ServidorInt that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static sop_corba.ServidorInt extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (sop_corba.ServidorIntHelper.id (), "ServidorInt");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static sop_corba.ServidorInt read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_ServidorIntStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, sop_corba.ServidorInt value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static sop_corba.ServidorInt narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof sop_corba.ServidorInt)
      return (sop_corba.ServidorInt)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      sop_corba._ServidorIntStub stub = new sop_corba._ServidorIntStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static sop_corba.ServidorInt unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof sop_corba.ServidorInt)
      return (sop_corba.ServidorInt)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      sop_corba._ServidorIntStub stub = new sop_corba._ServidorIntStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}