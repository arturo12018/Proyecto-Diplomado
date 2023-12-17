package dgtic.core.proyecto.controller.admin.usuario;


import dgtic.core.proyecto.entity.Rol;
import dgtic.core.proyecto.entity.Usuario;
import dgtic.core.proyecto.service.usuario.UsuarioService;
import dgtic.core.proyecto.service.usuario.UsuarioServiceImpl;
import dgtic.core.proyecto.util.RenderPagina;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("admin/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("lista-usuarios")
    public String listaUsuarios(@RequestParam(name="page",defaultValue = "0") int page, Model model){
        Pageable pagReq= PageRequest.of(page,10);
        Page<Usuario> usuarios=usuarioService.findAll(pagReq);
        RenderPagina<Usuario> render=new RenderPagina<>("lista-usuarios",usuarios);
        model.addAttribute("page",render);
        model.addAttribute("usuarios",usuarios);

        model.addAttribute("contenido","Lista Usuarios");
        return "admin/usuario/lista-usuarios";
    }

    @GetMapping("borrar-usuarios/{id}")
    public String borrarAdmin(@PathVariable("id") Integer id, Model model, RedirectAttributes flash){
        try{
            usuarioService.borrar(id);
            flash.addFlashAttribute("success","Cliente se borro bien");
            return "redirect:/admin/usuario/lista-usuarios";
        }catch (Exception e){
            flash.addFlashAttribute("warning","Error al borrar cliente");
            return "redirect:/admin/usuario/lista-usuarios";
        }

    }

    @GetMapping("alta-usuario")
    public String altaUsuario(Model model){
        Usuario usuario=new Usuario();
        model.addAttribute("operacion","Alta de Usuario");
        model.addAttribute("usuario",usuario);
        return "admin/usuario/alta-usuario";
    }

    @PostMapping("alta-usuario")
    public String altaUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model, RedirectAttributes flash){
        if(result.hasErrors()){
            for (FieldError e :result.getFieldErrors()) {
                System.out.println(e.getDefaultMessage());
                System.out.println(e.getCode());
                model.addAttribute("operacion", "Error en datos");
                return "admin/usuario/alta-usuario";
            }
        }
        try{
            usuarioService.guardar(usuario);
            flash.addFlashAttribute("success","Administrador se almaceno con éxito");
            return "redirect:/admin/usuario/lista-usuarios";
        }catch (Exception e){
            ObjectError er=new ObjectError("Duplicados","Correo Duplicaodo");
            result.addError(er);
            return "admin/usuario/alta-usuario";
        }
    }

    @GetMapping("modificar-usuario/{id}")
    public String modificarUsuario(@PathVariable("id")Integer id,Model model){
        Usuario usuario=usuarioService.buscarPorId(id);
        model.addAttribute("usuario",usuario);
        model.addAttribute("operacion","Modificar Usuario");
        return "admin/usuario/alta-usuario";
    }



}
