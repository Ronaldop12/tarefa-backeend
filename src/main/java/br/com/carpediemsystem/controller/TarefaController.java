package br.com.carpediemsystem.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.carpediemsystem.model.Tarefa;
import br.com.carpediemsystem.repositories.TarefaRepository;
import br.com.carpediemsystem.services.TarefaService;

@RestController
@RequestMapping("/api/tarefas")
@CrossOrigin(origins = { "http://localhost:4200" })
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;
    
    @Autowired
    private TarefaRepository tarefaRepository;

    // Listar todas as tarefas
    @GetMapping
    public ResponseEntity<Page<Tarefa>> getTarefas(Pageable pageable) {
        return ResponseEntity.ok(tarefaService.listarTarefasPaginadas(pageable));
    }


    // Buscar uma tarefa por ID
    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> getTarefaById(@PathVariable Long id) {
        Optional<Tarefa> tarefa = tarefaService.buscarPorId(id);
        if (tarefa.isPresent()) {
            return ResponseEntity.ok(tarefa.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Criar uma tarefa
    @PostMapping
    public ResponseEntity<?> criarTarefa(@RequestBody Tarefa tarefa) {
        try {
            Tarefa tarefaSalva = tarefaService.salvarTarefa(tarefa);
            return new ResponseEntity<>(tarefaSalva, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    // Atualizar uma tarefa
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        if (!tarefaService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        tarefa.setId(id);
        return ResponseEntity.ok(tarefaService.salvarTarefa(tarefa));
    }

    // Excluir uma tarefa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTarefa(@PathVariable Long id) {
        tarefaService.excluirTarefa(id);
        return ResponseEntity.noContent().build();
    }

    // Reordenar uma tarefa
    @PostMapping("/{id}/reordenar")
    public ResponseEntity<Void> reordenarTarefa(@PathVariable Long id, @RequestParam boolean moverParaCima) {
        tarefaService.reordenarTarefa(id, moverParaCima);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/check-name")
    public ResponseEntity<?> checkIfNameExists(@RequestParam String nome) {
        boolean exists = tarefaService.nomeTarefaExiste(nome, null);
        return ResponseEntity.ok(Collections.singletonMap("exists", exists));
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Tarefa>> buscarPorTermo(@RequestParam String termo) {
        try {
   
            List<Tarefa> tarefas = tarefaRepository.findByNomeContaining(termo);
            if (tarefas.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tarefas, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();  
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   
}



