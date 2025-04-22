package org.example.controleacesso.restcontrollers;

import org.example.controleacesso.services.AcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("autenticacao")
public class AcessoRestController {
    @Autowired
    AcessoService acessoService;

    @GetMapping("/{nome}/{senha}")
    ResponseEntity<Object> autenticar(@PathVariable String nome, @PathVariable String senha){
        String token=acessoService.autenticar(nome, senha);
        if(token!=null)
            return ResponseEntity.ok(token);
        else{
            return ResponseEntity.badRequest().body("Acesso não permitido");
        }
    }

}

