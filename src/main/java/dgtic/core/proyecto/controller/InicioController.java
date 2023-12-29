package dgtic.core.proyecto.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class InicioController {

    @Value("Libreria")
    String nombreApp;

    @GetMapping("/")
    public String inicio(Model model){
        model.addAttribute("nombreAplicacion",nombreApp);
        return "inicio";
    }



    @GetMapping("/adminlogin")
    public String loginAdmin(@RequestParam(value = "error", required = false) String error, Model model){
        if(error != null){
            model.addAttribute("error", "Correo o contraseña incorrecto");
        }
        return "adminlogin";
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






}
