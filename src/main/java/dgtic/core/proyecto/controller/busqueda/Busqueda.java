package dgtic.core.proyecto.controller.busqueda;

import dgtic.core.proyecto.entity.Estado;
import dgtic.core.proyecto.entity.Libro;
import dgtic.core.proyecto.repository.EstadoRepository;
import dgtic.core.proyecto.service.Libro.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class Busqueda {

    @Autowired
    EstadoRepository estadoRepository;

    @Autowired
    LibroService libroService;


    @GetMapping(value = "/listado-estados/{id}",produces = "application/json")
    public @ResponseBody List<Estado> buscarEstados(@PathVariable("id") Integer id){
        return estadoRepository.findByPais_Id(id);
    }




    //--------------Pendiente
    @GetMapping(value = "/buscar-libro/{dato}",produces = "application/json")
    public @ResponseBody List<Libro> buscarLibro(@PathVariable("dato") String dato){
        return libroService.busquedaPorPatron(dato);
    }
}
