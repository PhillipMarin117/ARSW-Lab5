package edu.escuelaing.arsw.app.controller;

import java.util.HashSet;

import edu.escuelaing.arsw.app.service.Juego;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppController {
    Juego n =  new Juego();;
    int vidas, picas, famas, num;
    HashSet<Integer> hashSet;
    String mensaje = "";

    @GetMapping("/")
    public String welcome(Model model){
        return "index";
    }

    @RequestMapping("/juego")
    public String empiezajuego(Model model){
        model.addAttribute("juegoini", n);
        model.addAttribute("listanumeros", hashSet);
        model.addAttribute("num", num);
        model.addAttribute("mensaje", mensaje);
        return "juego";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, params = { "play" })
    public String post(Integer num) {
        if (!n.isFinished()){
            this.num = num;
            n.play(num);
            picas = n.getPicas();
            famas = n.getFama();
            hashSet = n.getLastNumbers();
        } else{
            if(n.getVictoria()){
                mensaje= "ganaste ✧◝(⁰▿⁰)◜✧";
                System.out.println("ganaste ✧◝(⁰▿⁰)◜✧");
            } else{
                mensaje ="Perdiste ಠ_ಠ";
                System.out.println("Perdiste ಠ_ಠ");
            }
        }
        return "redirect:/juego";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, params = { "restart" })
    public String restart() {
        n.generateNumber();
        return "redirect:/juego";
    }
}