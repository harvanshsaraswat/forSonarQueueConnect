package in.harsh.loginDemo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.harsh.loginDemo.model.Hero;
import jakarta.annotation.security.RolesAllowed;



@CrossOrigin("*")
@RestController
@RequestMapping("/api/heroes")
public class HeroController {
     private List<Hero> someHeroes = List.of(
                new Hero(1, "Ken"),    
                new Hero(2, "Yannick"),
                new Hero(3, "Pieter"));
      

    @GetMapping
    @RolesAllowed({"heroes-user", "heroes-admin"})
    public List<Hero> heroes() {
           return someHeroes;
    }
    @GetMapping("/{id}")
    @RolesAllowed("heroes-admin")
    public Hero hero(@PathVariable("id") String id) {
           return someHeroes.stream()
           .filter(h -> Integer.toString(h.getId()).equals(id))
           .findFirst()
           .orElse(null);
    }
    
    @GetMapping
    @RolesAllowed({"heroes-user", "heroes-admin"})
    public Hero hero() {
           return someHeroes;
    }
}