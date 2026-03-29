package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class MensagemController {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping("/api/usuarios")
    public List<Usuario> listarUsuariosApi() {
        return repository.findAll();
    }

    @PostMapping("/api/usuarios")
    public Usuario cadastrar(@RequestBody Usuario novo) {
        novo.setEmail(novo.getNome().toLowerCase().replace(" ", "") + "@ads2026.com");
        return repository.save(novo);
    }

    @DeleteMapping("/api/usuarios/{id}")
    public void excluir(@PathVariable Long id) {
        repository.deleteById(id);
    }
    @PutMapping("/api/usuarios/{id}")
    @CrossOrigin(origins = "*")
    public Usuario editar(@PathVariable Long id, @RequestBody Usuario dadosNovos) {
        // 1. Achamos o usuário atual no banco
        Usuario usuarioDoBanco = repository.findById(id).get();

        // 2. Atualizamos o nome com o que veio do site
        usuarioDoBanco.setNome(dadosNovos.getNome());

        // 3. Geramos o e-mail novo baseado no nome editado
        usuarioDoBanco.setEmail(dadosNovos.getNome().toLowerCase().replace(" ", "") + "@ads2026.com");

        // 4. Salvamos de volta no MySQL
        return repository.save(usuarioDoBanco);
    }
}
