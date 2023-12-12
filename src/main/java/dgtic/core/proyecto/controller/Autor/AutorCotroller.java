package dgtic.core.proyecto.controller.Autor;

import dgtic.core.proyecto.entity.Administrador;
import dgtic.core.proyecto.entity.Autores;
import dgtic.core.proyecto.repository.AutoresRepository;
import dgtic.core.proyecto.service.autores.AutoresService;
import dgtic.core.proyecto.service.autores.AutoresServiceImpl;
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
@RequestMapping("autores")
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
        return "autores/lista-autores";
    }
}
