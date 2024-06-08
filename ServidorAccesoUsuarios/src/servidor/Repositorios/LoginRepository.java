package servidor.Repositorios;

import java.util.ArrayList;
import java.util.List;

import servidor.DTO.LoginDTO;


public class LoginRepository implements LoginRepositoryInt
{
    //TODO: ArrayList se puede crear como un List?
    private final ArrayList<LoginDTO> lstLogin;

    public LoginRepository()
    {        
        this.lstLogin = new ArrayList<>();
        this.lstLogin.add(new LoginDTO("administrador","10029594"));
    }

    @Override
    public boolean iniciarSesion(LoginDTO objLogin) {
        boolean accesado = false;
        System.out.println("Validando credenciales..." );
        for (LoginDTO loginDTO : lstLogin) {
            if (loginDTO.getUsername().equals(objLogin.getUsername()) && loginDTO.getContrasena().equals(objLogin.getContrasena())) {
                accesado = true;
                break;
            }
        }        
        return accesado;
    }
}
