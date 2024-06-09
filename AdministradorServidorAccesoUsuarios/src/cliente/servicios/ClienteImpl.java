/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente.servicios;

import sop_corba.ClienteIntPOA;

/**
 *
 * @author GUILLERMO_PEÑA
 */
public class ClienteImpl extends ClienteIntPOA{
    
    private GUICliente GUIC;

    public ClienteImpl(GUICliente GUIC) {
        this.GUIC = GUIC;
    }
    
    

    @Override
    public void recibirMensaje(String usuario, String mensaje) {
        GUIC.fijarTexto(usuario, mensaje);
    }

    @Override
    public void recibirContacto(String usuario) {
        GUIC.fijarContacto(usuario);
    }

    @Override
    public String obtenerNombre() {
        return GUIC.obtenerNombre();
    }

    @Override
    public void borrarListaDeContactos() {
        GUIC.borrarLista();
    }
    
    
    
    
}
