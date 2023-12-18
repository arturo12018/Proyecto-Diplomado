package dgtic.core.proyecto.controller.admin.editoriales;


import dgtic.core.proyecto.entity.Autores;
import dgtic.core.proyecto.entity.Editorial;
import dgtic.core.proyecto.entity.Usuario;
import dgtic.core.proyecto.service.editorial.EditorialesService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("admin/editoriales")
public class EditorialesController {

    @Autowired
    EditorialesService editorialesService;

    @GetMapping("lista-editoriales")
    public String listaEditoriales(@RequestParam(name="page",defaultValue = "0") int page, Model model){
        Pageable pagReq= PageRequest.of(page,10);
        Page<Editorial> editorials=editorialesService.findAll(pagReq);
        RenderPagina<Editorial> render=new RenderPagina<>("lista-editoriales",editorials);
        model.addAttribute("page",render);
        model.addAttribute("editorials",editorials);

        model.addAttribute("contenido","Lista Editoriales");
        return "admin/editoriales/lista-editoriales";
    }

    @GetMapping("alta-editorial")
    public String altaUsuario(Model model){
        Editorial editorial=new Editorial();
        model.addAttribute("operacion","Alta de Editorial");
        model.addAttribute("editorial",editorial);
        return "admin/editoriales/alta-editorial";
    }

    @PostMapping("alta-editorial")
    public String altaEditorial(@Valid @ModelAttribute("editorial") Editorial editorial, BindingResult result, Model model, RedirectAttributes flash){
        if(result.hasErrors()){
            for (FieldError e :result.getFieldErrors()) {
                System.out.println(e.getDefaultMessage());
                System.out.println(e.getCode());
                model.addAttribute("operacion", "Error en datos");
                return "admin/editoriales/alta-editorial";
            }
        }
        editorialesService.guardar(editorial);
        flash.addFlashAttribute("success","Autor se almaceno con éxito");
        return "redirect:/admin/editoriales/lista-editoriales";
    }

   @GetMapping("modificar-editorial/{id}")
    public String modificarEditorial(@PathVariable("id")Integer id,Model model){
        Editorial editorial=editorialesService.buscarPorId(id);
       model.addAttribute("editorial",editorial);
        model.addAttribute("operacion","Modificar Editorial");
        return "admin/editoriales/alta-editorial";
    }



}
