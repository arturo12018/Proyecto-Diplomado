package dgtic.core.proyecto.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class InicioController {

    @Value("Libreria")
    String nombreApp;

    @GetMapping("/")
    public String inicio(Model model){
        model.addAttribute("nombreAplicacion",nombreApp);
        return "inicio";
    }

    @GetMapping("/principal")
    public String principal(Model model){
        model.addAttribute("contenido","Inicio");
        return "principal";
    }

    @GetMapping("/adminlogin")
    public String loginAdmin(){
        return "adminlogin";
    }

    @GetMapping("/admin/inicio")
    public String inicioAdmin(Model model){
        model.addAttribute("contenido","Administracion");
        return "admin/inicio";
    }




}
