package org.example.controleacesso.restcontrollers;

import jakarta.servlet.http.HttpServletRequest;
import org.example.controleacesso.entities.Usuario;
import org.example.controleacesso.repositories.UsuarioRepository;
import org.example.controleacesso.security.JWTTokenProvider;
import org.example.controleacesso.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("apis/user")
public class UsuarioRestController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @GetMapping
    public ResponseEntity<Object> getUsuarios(){
        //String token=httpServletRequest.getHeader("Authorization");
        //if(JWTTokenProvider.verifyToken(token)) {
            List<Usuario> usuarioList = usuarioService.getAll();
            if (usuarioList.isEmpty())
                return ResponseEntity.badRequest().body("sem dados");
            else
                return ResponseEntity.ok(usuarioList);
        //}
        //return  ResponseEntity.badRequest().body("Acesso não permitido");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUsuarioId(@PathVariable(name = "id") Long id){
        Usuario usuario=usuarioService.getById(id);
        if(usuario==null)
            return ResponseEntity.badRequest().body("Usuario não encontrado");
        else
            return ResponseEntity.ok(usuario);
    }
    @PostMapping
    public ResponseEntity<Object> addUsuario(@RequestBody Usuario usuario){
        Usuario novoUsuario = usuarioService.save(usuario);
        if(novoUsuario!=null)
            return ResponseEntity.ok(novoUsuario);
        else
            return ResponseEntity.badRequest().body("erro ao cadastrar o novo usuario");
    }
    @PutMapping
    public ResponseEntity<Object> updUsuario(@RequestBody Usuario usuario){
        Usuario novoUsuario = usuarioService.save(usuario);
        if(novoUsuario!=null)
            return ResponseEntity.ok(novoUsuario);
        else
            return ResponseEntity.badRequest().body("erro ao alterar o novo usuario");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delUsuario(@PathVariable Long id){
        if(usuarioService.delete(id))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().body("Erro ao apagar o usuário");
    }
//    @GetMapping("get-by-name/{name}")
//    public ResponseEntity<Object> getUsuarioNome(@PathVariable(name = "name") String nome){
//        Usuario usuario;
//        usuario=usuarioRepo.findByNome(nome);
//        if(usuario==null)
//            return ResponseEntity.badRequest().body("Usuario não encontrado");
//        else
//            return ResponseEntity.ok(usuario);
//    }
}
