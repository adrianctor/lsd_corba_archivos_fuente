/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sop_corba.GestionProductosPOA;
import sop_corba.GestionProductosPackage.productoDTO;
import sop_corba.GestionProductosPackage.productoDTOCount;


public class GestionProductosImpl extends GestionProductosPOA{

    private HashMap<String, productoDTO> productos;
    
    public GestionProductosImpl()
    {
        this.productos = new HashMap();
    }
   
    @Override
    public boolean registrarProducto(productoDTO objProducto) {
        System.out.println("ingresando a registrar producto.");
        boolean bandera = false;
        if (productos.size() < 5) {
            productos.put(objProducto.codigoProducto, objProducto);
            bandera = true;
        }      
       return bandera;  
    }

    @Override
    public productoDTO consultarProducto(String codigo) {
        System.out.println("ingresando a consultar producto.");
        productoDTO objProducto = new productoDTO("-1", "-1", codigo, "dd/mm/aaaa", "hh:mm", false);
        if (this.productos.get(codigo) != null) {
            objProducto = this.productos.get(codigo);
        }        
        return objProducto;
    }

    @Override
    public productoDTOCount[] consultarProductos() {
        List<productoDTOCount> productoList = new ArrayList<>();

        for (Map.Entry<String, productoDTO> entry : productos.entrySet()) {
            productoDTOCount dtoCount = new productoDTOCount(entry.getValue(), 1); // Suponiendo que el count es 1 para cada producto
            productoList.add(dtoCount);
        }
        return productoList.toArray(new productoDTOCount[productoList.size()]);
    }
}
