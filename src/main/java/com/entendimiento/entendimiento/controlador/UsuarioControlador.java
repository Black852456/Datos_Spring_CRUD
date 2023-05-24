package com.entendimiento.entendimiento.controlador;


import com.entendimiento.entendimiento.entidad.Usuario;
import com.entendimiento.entendimiento.servicio.UsuarioServicio;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/usuario")
@Slf4j
class UsuarioControlador {

    @Autowired
    private UsuarioServicio servicio;

    @GetMapping("/insert")
    public String insert(Model model){
        model.addAttribute("usuario",new Usuario());
        return "usuario/usuario";
    }

    @GetMapping("/listar")
    public String index(Model model){
        model.addAttribute("usuarios",servicio.listarUsuarios());
        return "usuario/listar";
    }

    /*@GetMapping("/listar")
    public ModelAndView index(){
        ModelAndView model = new ModelAndView();
        model.addObject("usuarios",servicio.listarUsuarios());
        model.setViewName("usuario/listar.html");
        return model;
    }*/

    @GetMapping("/actualizar/{id}")
    public String actualizar(@PathVariable("id") String id,Model model){
        if(verificacionUsuarioPorId(id)){
            model.addAttribute("usuario",servicio.encontrarUsuario(Long.parseLong(id)));
            return "usuario/usuario";
        }else{
            return "errors/Errors.html";
        }
    }

  /*
    @GetMapping("/actualizar/{id}")
    public ModelAndView actualizar(@PathVariable("id") String id){
        ModelAndView modelo = new ModelAndView();
        if(verificacionUsuarioPorId(id)){
            modelo.addObject("usuario",servicio.encontrarUsuario(Long.parseLong(id)));
            modelo.setViewName("usuario/usuario.html");
        }else{
            modelo.setViewName("errors/Errors.html");
        }
        return modelo;
    }
*/
    @PostMapping("/datos")
    public String datos(@Valid Usuario usuario, BindingResult result,Model model){
        if(result.hasErrors()){
            log.info("ERRORES");
            return "usuario/usuario";
        }else{
            servicio.dataUpdateSave(usuario);
            return "redirect:/usuario/listar";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") String id,Model model){
        if(verificacionUsuarioPorId(id)){
            servicio.eliminar(Long.parseLong(id));
            return "redirect:/usuario/listar";
        }
        return "redirect:/usuario/listar";
    }

    private boolean verificacionUsuarioPorId(String id){
        //Que el id sea numero
        Long idNumerico = 1L;
        try{
            idNumerico = Long.parseLong(id);
        }catch (Exception ex){
            return false;
        }
        if(servicio.encontrarUsuario(idNumerico)!=null){
            return true;
        }
        return false;
    }








}
