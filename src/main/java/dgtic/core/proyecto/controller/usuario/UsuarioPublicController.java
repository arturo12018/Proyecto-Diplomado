package dgtic.core.proyecto.controller.usuario;


import dgtic.core.proyecto.entity.Carrito;
import dgtic.core.proyecto.entity.Compra;
import dgtic.core.proyecto.entity.Libro;
import dgtic.core.proyecto.entity.Usuario;
import dgtic.core.proyecto.service.Libro.LibroService;
import dgtic.core.proyecto.service.compra.CompraService;
import dgtic.core.proyecto.service.usuario.UsuarioService;
import dgtic.core.proyecto.util.RenderPagina;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("user")
public class UsuarioPublicController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    CompraService compraService;


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
            flash.addFlashAttribute("success","Usuario se cero exitosamente");
            return "redirect:/login";
        }catch (Exception e){
            ObjectError er=new ObjectError("Duplicados","Correo Duplicaodo");
            result.addError(er);
            return "sign-in";
        }
    }


    /*@GetMapping("verificar-login-user")
    public String verificacionSesion(){
        return "redirect:/user/inicio-user";
    }*/

    @GetMapping("inicio-user")
    public String inicioUsuario(@RequestParam(name="page",defaultValue = "0") int page, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer id=usuarioService.buscarIdPorCorreo(authentication.getName());

        Pageable pagReq= PageRequest.of(page,10);
        Page<Compra> compra=compraService.listadosComprasPorID(id,pagReq);
        RenderPagina<Compra> render=new RenderPagina<>("inicio-user",compra);
        model.addAttribute("page",render);
        model.addAttribute("compra",compra);

        return "user/inicio-user";
    }






}
