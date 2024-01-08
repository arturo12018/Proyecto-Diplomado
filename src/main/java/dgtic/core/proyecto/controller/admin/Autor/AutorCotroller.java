package dgtic.core.proyecto.controller.admin.Autor;

import dgtic.core.proyecto.entity.Administrador;
import dgtic.core.proyecto.entity.Autores;
import dgtic.core.proyecto.entity.Usuario;
import dgtic.core.proyecto.repository.AutoresRepository;
import dgtic.core.proyecto.service.autores.AutoresService;
import dgtic.core.proyecto.service.autores.AutoresServiceImpl;
import dgtic.core.proyecto.util.RenderPagina;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("admin/autores")
public class AutorCotroller {

    @Autowired
    AutoresService autoresService;


    @GetMapping("lista-autores")
    public String listaAdministrador(@RequestParam(name="page",defaultValue = "0") int page, Model model){
        Pageable pagReq= PageRequest.of(page,10);
        Page<Autores> autores=autoresService.findAll(pagReq);
        RenderPagina<Autores> render=new RenderPagina<>("lista-autores",autores);
        model.addAttribute("page",render);
        model.addAttribute("autores",autores);

        model.addAttribute("contenido","Lista Autores");
        return "admin/autores/lista-autores";
    }

    @GetMapping("alta-autores")
    public String altaAutores(Model model){
        Autores autores=new Autores();
        model.addAttribute("autores",autores);
        model.addAttribute("contenido","Alta Autor");
        return "admin/autores/alta-autores";
    }

    @PostMapping("alta-autores")
    public String altaAutores(@Valid @ModelAttribute("autores") Autores autores, BindingResult result, Model model, RedirectAttributes flash){
        if(result.hasErrors()){
            for (FieldError e :result.getFieldErrors()) {
                System.out.println(e.getDefaultMessage());
                System.out.println(e.getCode());
                model.addAttribute("operacion", "Error en datos");
                return "admin/autores/alta-autores";
            }
        }
        autoresService.guardar(autores);
        flash.addFlashAttribute("success","Autor se almaceno con éxito");
        return "redirect:/admin/autores/lista-autores";
    }

    @GetMapping("modificar-autores/{id}")
    public String modificarAutores(@PathVariable("id")Integer id,Model model){
        Autores autores=autoresService.buscarPorId(id);
        model.addAttribute("autores",autores);
        model.addAttribute("operacion","Modificar Autor");
        return "admin/autores/alta-autores";
    }



}
