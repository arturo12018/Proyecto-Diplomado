package dgtic.core.proyecto.controller.admin.Libro;


import dgtic.core.proyecto.entity.*;
import dgtic.core.proyecto.repository.AutoresRepository;
import dgtic.core.proyecto.repository.EditorialRepository;
import dgtic.core.proyecto.repository.IdiomaRepository;
import dgtic.core.proyecto.service.Libro.LibroService;
import dgtic.core.proyecto.util.Archivos;
import dgtic.core.proyecto.util.RenderPagina;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("admin/libros")
@SessionAttributes("libro")
public class LibroController {
    @Autowired
    LibroService libroService;

    @Autowired
    IdiomaRepository idiomaRepository;

    @Autowired
    AutoresRepository autoresRepository;

    @Autowired
    EditorialRepository editorialRepository;


    @Value("${ejemplo.imagen.ruta}")
    private String archivoRuta;


    @GetMapping("lista-libros")
    public String listaLibros(@RequestParam(name="page",defaultValue = "0") int page, Model model){
        Pageable pagReq= PageRequest.of(page,10);
        Page<Libro> libros=libroService.findAll(pagReq);
        RenderPagina<Libro> render=new RenderPagina<>("lista-libros",libros);
        model.addAttribute("page",render);
        model.addAttribute("libros",libros);

        model.addAttribute("contenido","Lista Libros");
        return "admin/libros/lista-libros";
    }

    @GetMapping("borrar-libros/{id}")
    public String borrarAdmin(@PathVariable("id") Long id, Model model, RedirectAttributes flash){
        try{
            libroService.borrar(id);
            flash.addFlashAttribute("success","Libro se borro bien");
            return "redirect:/admin/libros/lista-libros";
        }
        catch (Exception e){
            flash.addFlashAttribute("warning","Error al borrar libro: Poque ya tiene compras");
            return "redirect:/admin/libros/lista-libros";
        }


    }

    @GetMapping("alta-libros")
    public String altaLibro(Model model){
        Libro libro=new Libro();
        List<Idioma> idiomasSelect=idiomaRepository.findAll();
        List<Autores> autoresSelect=autoresRepository.findAll();
        List<Editorial> editorialSelect=editorialRepository.findAll();
        model.addAttribute("idiomasSelect",idiomasSelect);
        model.addAttribute("autoresSelect",autoresSelect);
        model.addAttribute("editorialSelect",editorialSelect);
        model.addAttribute("libro",libro);
        model.addAttribute("operacion","Alta libro");
        return  "admin/libros/alta-libros";
    }

    @PostMapping("alta-libros")
    public String altaLibro(@Valid @ModelAttribute("libro") Libro libro,
                            BindingResult result, Model model, RedirectAttributes flash) {


        if (result.hasErrors()) {
            for (FieldError e : result.getFieldErrors()) {
                System.out.println(e.getDefaultMessage());
                System.out.println(e.getCode());

                model.addAttribute("operacion", "Error en los datos");
            }

            List<Idioma> idiomasSelect = idiomaRepository.findAll();
            List<Autores> autoresSelect = autoresRepository.findAll();
            List<Editorial> editorialSelect = editorialRepository.findAll();
            model.addAttribute("autoresSelect", autoresSelect);
            model.addAttribute("idiomasSelect", idiomasSelect);
            model.addAttribute("editorialSelect", editorialSelect);

            return "admin/libros/alta-libros";
        }

        if (libro.getImagenPortada() == null || libro.getImagenPortada().isEmpty()) {
            libro.setImagenPortada("Sin_imagen_disponible.jpg");
        } else {
            String archivo = libro.getImagenPortada();
            String nuevoArchivo = libro.getTitulo() + "_" + libro.getIsbn() + "_" + archivo;
            Archivos.renombrar(archivoRuta, archivo, nuevoArchivo);
            libro.setImagenPortada(nuevoArchivo);
        }

        if (libroService.guardarLibro(libro)) {
            flash.addFlashAttribute("success", "Se almaceno con éxito");
            return "redirect:/admin/libros/lista-libros";
        }

        List<Idioma> idiomasSelect = idiomaRepository.findAll();
        List<Autores> autoresSelect = autoresRepository.findAll();
        List<Editorial> editorialSelect = editorialRepository.findAll();
        model.addAttribute("autoresSelect", autoresSelect);
        model.addAttribute("idiomasSelect", idiomasSelect);
        model.addAttribute("operacion", "Error en los datos");
        model.addAttribute("editorialSelect", editorialSelect);
        ObjectError er = new ObjectError("Duplicados", "ISBN existente");
        result.addError(er);

        return "admin/libros/alta-libros";
    }



    @PostMapping("modificar-libros")
    public String modificarLibro(@Valid @ModelAttribute("libro")Libro libro, BindingResult result, Model model, RedirectAttributes flash){


        if(result.hasErrors()){
            /*for (FieldError e :result.getFieldErrors()) {
                System.out.println(e.getDefaultMessage());
                System.out.println(e.getCode());
                model.addAttribute("operacion", "Error en los datos");

            }*/
            List<Idioma> idiomasSelect = idiomaRepository.findAll();
            List<Autores> autoresSelect = autoresRepository.findAll();
            List<Editorial> editorialSelect = editorialRepository.findAll();
            model.addAttribute("autoresSelect", autoresSelect);
            model.addAttribute("idiomasSelect", idiomasSelect);
            model.addAttribute("editorialSelect", editorialSelect);
            return "admin/libros/modificar-libros";
        }
        else{
                Libro libroComp=libroService.buscarPorId(libro.getIsbn());

                //if(!(libro.getImagenPortada().equals("Sin_imagen_disponible.jpg")||libro.getImagenPortada().equals(libro.getImagenPortada()))){
                  if(!(libro.getImagenPortada().equals(libroComp.getImagenPortada()))){
                    String archivo = libro.getImagenPortada();
                    String nuevoArchivo = libro.getTitulo() + "_" + libro.getIsbn() + "_" + archivo;
                    Archivos.renombrar(archivoRuta, archivo, nuevoArchivo);
                    libro.setImagenPortada(nuevoArchivo);
                }



            libroService.guardar(libro);
            flash.addFlashAttribute("success","Se almaceno con éxito");
            return "redirect:/admin/libros/lista-libros";
        }

    }

   @GetMapping("modificar-libros/{id}")
    public String saltoModificar(@PathVariable("id") Long id, Model model) {
       List<Idioma> idiomasSelect=idiomaRepository.findAll();
       List<Autores> autoresSelect=autoresRepository.findAll();
       List<Editorial> editorialSelect=editorialRepository.findAll();
       model.addAttribute("idiomasSelect",idiomasSelect);
       model.addAttribute("autoresSelect",autoresSelect);
       model.addAttribute("editorialSelect",editorialSelect);
       model.addAttribute("operacion","Modificar Libro");
        Libro libro = libroService.buscarPorId(id);
        model.addAttribute("anioPublicacion",libro.getAnioPublicacion());
        model.addAttribute("libro", libro);
        return "admin/libros/modificar-libros";
    }


    @PostMapping(value = "salvar")
    public String guardar(@RequestParam("imagenarchivo") MultipartFile multipartFile,Model model,
                          HttpSession session){
        if(!multipartFile.isEmpty()){
            String imagenNombre= Archivos.almacenar(multipartFile,archivoRuta);
            if(imagenNombre!=null){
                Libro libro=(Libro)session.getAttribute("libro");
                libro.setImagenPortada(imagenNombre);
            }

        }



       //Libro libro=new Libro();

        List<Idioma> idiomasSelect=idiomaRepository.findAll();
        List<Autores> autoresSelect=autoresRepository.findAll();
        List<Editorial> editorialSelect=editorialRepository.findAll();
        model.addAttribute("idiomasSelect",idiomasSelect);
        model.addAttribute("autoresSelect",autoresSelect);
        model.addAttribute("editorialSelect",editorialSelect);
        //model.addAttribute("libro",libro);
        model.addAttribute("operacion","Alta libro");
        return  "admin/libros/alta-libros";

    }

    @PostMapping(value = "salvar-modificacion")
    public String modificacionImagen(@RequestParam("imagenarchivo") MultipartFile multipartFile,Model model,
                          HttpSession session){
        if(!multipartFile.isEmpty()){
            String imagenNombre= Archivos.almacenar(multipartFile,archivoRuta);
            if(imagenNombre!=null){
                Libro libro=(Libro)session.getAttribute("libro");
                libro.setImagenPortada(imagenNombre);
            }

        }

        List<Idioma> idiomasSelect=idiomaRepository.findAll();
        List<Autores> autoresSelect=autoresRepository.findAll();
        List<Editorial> editorialSelect=editorialRepository.findAll();
        model.addAttribute("idiomasSelect",idiomasSelect);
        model.addAttribute("autoresSelect",autoresSelect);
        model.addAttribute("editorialSelect",editorialSelect);
        //model.addAttribute("libro",libro);
        model.addAttribute("operacion","Modificar libro");
        return  "admin/libros/modificar-libros";

    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
        webDataBinder.registerCustomEditor(Date.class,new CustomDateEditor(format,false));
    }



}
