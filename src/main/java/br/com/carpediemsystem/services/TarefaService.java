package br.com.carpediemsystem.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.carpediemsystem.model.Tarefa;
import br.com.carpediemsystem.repositories.TarefaRepository;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public Optional<Tarefa> buscarPorId(Long id) {
        return tarefaRepository.findById(id);
    }

    // Método para listar todas as tarefas sem paginação
    public List<Tarefa> listarTodasTarefas() {
        return tarefaRepository.findAllByOrderByOrdemApresentacao(PageRequest.of(0, Integer.MAX_VALUE)).getContent();
    }

    // Método para listar as tarefas com paginação
    public Page<Tarefa> listarTarefasPaginadas(Pageable pageable) {
        return tarefaRepository.findAllByOrderByOrdemApresentacao(pageable);
    }


    public Tarefa salvarTarefa(Tarefa tarefa) {
        // Ensure custo is not null
        if (tarefa.getCusto() == null) {
            throw new RuntimeException("Custo não pode ser null!");
        }

        // Verificar duplicidade de nome usando o método nomeTarefaExiste
        if (nomeTarefaExiste(tarefa.getNome(), tarefa.getId())) {
            throw new RuntimeException("Nome da tarefa já existe!");
        }

        // Se for uma nova tarefa, definir ordemApresentacao
        if (tarefa.getId() == null) {
            Integer maxOrder = tarefaRepository.findAll().stream()
                    .map(Tarefa::getOrdemApresentacao)
                    .max(Integer::compareTo)
                    .orElse(0);
            tarefa.setOrdemApresentacao(maxOrder + 1);
        }

        return tarefaRepository.save(tarefa);
    }

    public boolean nomeTarefaExiste(String nome, Long idAtual) {
        Optional<Tarefa> tarefaExistente = tarefaRepository.findByNome(nome);
        return tarefaExistente.isPresent() && (idAtual == null || !tarefaExistente.get().getId().equals(idAtual));
    }

    public void excluirTarefa(Long id) {
        tarefaRepository.deleteById(id);
    }

    // Reordenar tarefas
    public void reordenarTarefa(Long id, boolean moverParaCima) {
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada!"));

        List<Tarefa> todasTarefas = listarTodasTarefas();
        int indexAtual = todasTarefas.indexOf(tarefa);

        if (moverParaCima) {
            if (indexAtual == 0) return; // Já está no topo
            Collections.swap(todasTarefas, indexAtual, indexAtual - 1);
        } else {
            if (indexAtual == todasTarefas.size() - 1) return; // Já está no final
            Collections.swap(todasTarefas, indexAtual, indexAtual + 1);
        }

        // Atualizar a ordem de apresentação no banco de dados
        for (int i = 0; i < todasTarefas.size(); i++) {
            todasTarefas.get(i).setOrdemApresentacao(i + 1);
            tarefaRepository.save(todasTarefas.get(i));
        }
    }
}
