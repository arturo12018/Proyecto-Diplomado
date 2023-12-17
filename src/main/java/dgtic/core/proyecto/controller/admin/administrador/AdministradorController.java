package dgtic.core.proyecto.controller.admin.administrador;


import dgtic.core.proyecto.entity.Administrador;
import dgtic.core.proyecto.entity.Rol;
import dgtic.core.proyecto.repository.RolRepository;
import dgtic.core.proyecto.service.administrador.AdministradorService;
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

import java.util.List;

@Controller
@RequestMapping("admin/administrador")
public class AdministradorController {

    @Autowired
    AdministradorService administradorService;

    @Autowired
    RolRepository rolRepository;

    @GetMapping("lista-administrador")
    public String listaAdministrador(@RequestParam(name="page",defaultValue = "0") int page, Model model){
        Pageable pagReq= PageRequest.of(page,10);
        Page<Administrador> administradors=administradorService.findAll(pagReq);
        RenderPagina<Administrador> render=new RenderPagina<>("lista-administrador",administradors);
        model.addAttribute("page",render);
        model.addAttribute("administradors",administradors);

        model.addAttribute("contenido","Lista Administrador");
        return "admin/administrador/lista-administrador";
    }

    @GetMapping("borrar-administrador/{id}")
    public String borrarAdmin(@PathVariable("id") Integer id, Model model, RedirectAttributes flash){
        administradorService.borrar(id);
        flash.addFlashAttribute("success","Cliente se borro bien");
        return "redirect:/admin/administrador/lista-administrador";
    }

    @GetMapping("alta-administrador")
    public String altaAdministrador(Model model){
        Administrador administrador=new Administrador();
        List<Rol> rolsSelct=rolRepository.findAll();
        model.addAttribute("administrador",administrador);
        model.addAttribute("operacion","Alta Administrador");
        model.addAttribute("rolsSelct",rolsSelct);
        return "admin/administrador/alta-administrador";
    }

    @PostMapping("alta-administrador")
    public String altaAdministrador(@Valid @ModelAttribute("administrador") Administrador administrador, BindingResult result, Model model, RedirectAttributes flash){
            if(result.hasErrors()){
                for (FieldError e :result.getFieldErrors()) {
                    System.out.println(e.getDefaultMessage());
                    System.out.println(e.getCode());

                    List<Rol> rolsSelct=rolRepository.findAll();
                    model.addAttribute("rolsSelct",rolsSelct);
                    model.addAttribute("operacion", "Error en datos");
                    return "admin/administrador/alta-administrador";
                }
            }
            try{
                administradorService.guardar(administrador);
                flash.addFlashAttribute("success","Administrador se almaceno con éxito");
                return "redirect:/admin/administrador/lista-administrador";
            }
            catch (Exception e){
                List<Rol> rolsSelct=rolRepository.findAll();
                model.addAttribute("rolsSelct",rolsSelct);
                ObjectError er=new ObjectError("Duplicados","Correo Duplicaodo");
                result.addError(er);
                return "admin/administrador/alta-administrador";
            }



    }

    @GetMapping("modificar-administrador/{id}")
    public String modificarAdministrador(@PathVariable("id") Integer id,Model model){
        List<Rol> rolsSelct=rolRepository.findAll();
        model.addAttribute("rolsSelct",rolsSelct);
        Administrador administrador=administradorService.buscarPorId(id);
        model.addAttribute("administrador",administrador);
        model.addAttribute("operacion","Modificar Administrador");
        return "admin/administrador/alta-administrador";

    }



}
