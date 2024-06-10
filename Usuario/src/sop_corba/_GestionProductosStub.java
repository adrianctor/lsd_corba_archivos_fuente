package sop_corba;


/**
* sop_corba/_GestionProductosStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interface.idl
* domingo 9 de junio de 2024 04:46:13 PM COT
*/

public class _GestionProductosStub extends org.omg.CORBA.portable.ObjectImpl implements sop_corba.GestionProductos
{

  public boolean registrarProducto (sop_corba.GestionProductosPackage.productoDTO objProducto)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("registrarProducto", true);
                sop_corba.GestionProductosPackage.productoDTOHelper.write ($out, objProducto);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return registrarProducto (objProducto        );
            } finally {
                _releaseReply ($in);
            }
  } // registrarProducto

  public sop_corba.GestionProductosPackage.productoDTO consultarProducto (String codigo)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("consultarProducto", true);
                $out.write_string (codigo);
                $in = _invoke ($out);
                sop_corba.GestionProductosPackage.productoDTO $result = sop_corba.GestionProductosPackage.productoDTOHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return consultarProducto (codigo        );
            } finally {
                _releaseReply ($in);
            }
  } // consultarProducto

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:sop_corba/GestionProductos:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _GestionProductosStub
