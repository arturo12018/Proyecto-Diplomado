package dgtic.core.proyecto.controller.admin.editoriales;


import dgtic.core.proyecto.entity.Autores;
import dgtic.core.proyecto.entity.Editorial;
import dgtic.core.proyecto.service.editorial.EditorialesService;
import dgtic.core.proyecto.util.RenderPagina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "editoriales/lista-editoriales";
    }
}
