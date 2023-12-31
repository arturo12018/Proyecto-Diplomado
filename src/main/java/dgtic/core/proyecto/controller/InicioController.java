package dgtic.core.proyecto.controller;


import dgtic.core.proyecto.entity.Usuario;
import dgtic.core.proyecto.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class InicioController {

    @Value("Libreria")
    String nombreApp;

    @Autowired
    RolRepository rolRepository;

    @GetMapping("/")
    public String inicio(Model model){
        model.addAttribute("nombreAplicacion",nombreApp);
        return "inicio";
    }



    @GetMapping("/login")
    public String loginAdmin(@RequestParam(value = "error", required = false) String error, Model model){
        if(error != null){
            model.addAttribute("error", "Correo o contraseña incorrecto");
        }
        return "login";
    }

    @GetMapping("/admin/inicio")
    public String inicioAdmin(Model model){
        model.addAttribute("contenido","Administracion");
        return "admin/inicio";
    }

    @GetMapping("/admin/sinPermisos")
    public String sinPermisos(Model model){
        return "admin/sinPermisos";
    }



    @GetMapping("/sign-in")
    public String signi(Model model){
        Usuario usuario=new Usuario();
        model.addAttribute("usuario",usuario);
        return "sign-in";
    }


    @GetMapping("/tipoUsuario")
    public String tipoUsuario(){

        //Redireccionamiento del login
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isUser = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_USER"));
        if (isUser) {
            // El usuario tiene el rol "ROLE_USER"
            return "redirect:/user/inicio-user";
        } else {
            // El usuario no tiene el rol "ROLE_USER"
            return "redirect:/admin/inicio";

        }



    }






}
