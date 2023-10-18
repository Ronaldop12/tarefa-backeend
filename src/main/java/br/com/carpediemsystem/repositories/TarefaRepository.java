package br.com.carpediemsystem.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.carpediemsystem.model.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    Optional<Tarefa> findByNome(String nome);
    
    List<Tarefa> findByNomeContaining(String termo);
    
    Page<Tarefa> findAllByOrderByOrdemApresentacao(Pageable pageable);
	
}
