package dgtic.core.proyecto.controller.usuario;


import dgtic.core.proyecto.entity.Usuario;
import dgtic.core.proyecto.service.usuario.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
@RequestMapping("user")
public class UsuarioPublicController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("alta-usuario")
    public String altaUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model, RedirectAttributes flash){
        usuario.setEstadoActivo(true);
        if(result.hasErrors()){
            for (FieldError e :result.getFieldErrors()) {
                System.out.println(e.getDefaultMessage());
                System.out.println(e.getCode());
                model.addAttribute("operacion", "Error en datos");
                return "sign-in";
            }
        }
        try{
            usuarioService.guardar(usuario);
            flash.addFlashAttribute("success","Administrador se almaceno con éxito");
            return "redirect:/login";
        }catch (Exception e){
            ObjectError er=new ObjectError("Duplicados","Correo Duplicaodo");
            result.addError(er);
            return "sign-in";
        }
    }


}
