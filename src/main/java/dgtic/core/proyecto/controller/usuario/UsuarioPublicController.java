package dgtic.core.proyecto.controller.usuario;


import dgtic.core.proyecto.entity.*;
import dgtic.core.proyecto.repository.UsuarioRepository;
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
import org.springframework.security.crypto.password.PasswordEncoder;
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


    @Autowired
    PasswordEncoder passwordEncoder;


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
            flash.addFlashAttribute("success","Usuario se creo exitosamente");
            return "redirect:/login";
        }catch (Exception e){
            ObjectError er=new ObjectError("Duplicados","Correo Duplicado");
            result.addError(er);
            return "sign-in";
        }
    }



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


    @GetMapping("cambiar-contrasenia")
    public String cambiarContraeniaAdmin(Model model){
        ContraseniaNueva contraseniaNueva =new ContraseniaNueva();
        model.addAttribute("contraseniaNueva",contraseniaNueva);
        return "user/cambiar-contrasenia";
    }

    @PostMapping("cambiar-contrasenia")
    public String cambiarContraeniaAdmin(@Valid @ModelAttribute("contraseniaNueva") ContraseniaNueva contraseniaNueva, BindingResult result, Model model, RedirectAttributes flash) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioService.buscarPorCorreo(authentication.getName());

        if (result.hasErrors()) {
            for (FieldError e : result.getFieldErrors()) {
                System.out.println(e.getDefaultMessage());
                System.out.println(e.getCode());

                flash.addFlashAttribute("error", e.getDefaultMessage());
                return "redirect:/user/cambiar-contrasenia";
            }
        }

        if (passwordEncoder.matches(contraseniaNueva.getAntigua(), usuario.getPassword()) && !(contraseniaNueva.getAntigua().equals(contraseniaNueva.getNueva()))) {
            usuario.setConstrasena(contraseniaNueva.getNueva());
            usuarioService.guardar(usuario);
            flash.addFlashAttribute("success", "Contraseña se cambio con éxito");

        } else {
            if (contraseniaNueva.getAntigua().equals(contraseniaNueva.getNueva())) {
                flash.addFlashAttribute("error", "Error misma contraseña");

            } else {
                flash.addFlashAttribute("error", "Error con la contraseña Actual");

            }


        }

        contraseniaNueva = new ContraseniaNueva();
        model.addAttribute("contraseniaNueva", contraseniaNueva);
        return "redirect:/user/cambiar-contrasenia";

    }






}
