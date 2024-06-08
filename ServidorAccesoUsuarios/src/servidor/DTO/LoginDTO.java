/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.DTO;

import java.io.Serializable;


public class LoginDTO implements Serializable{
    //TODO: Implementar atributo final
    private final String username;
    private final String contrasena;

    public LoginDTO(String username, String contrasena) {
        this.username = username;
        this.contrasena = contrasena;
    }

    public String getUsername() {
        return username;
    }

    public String getContrasena() {
        return contrasena;
    }
    
}
