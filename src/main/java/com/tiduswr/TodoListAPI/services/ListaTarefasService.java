package com.tiduswr.TodoListAPI.services;

import com.tiduswr.TodoListAPI.model.ListTarefas;
import com.tiduswr.TodoListAPI.model.Tarefa;
import com.tiduswr.TodoListAPI.repositories.ListTarefasRepository;
import com.tiduswr.TodoListAPI.repositories.TarefaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class ListaTarefasService {

    @Autowired
    private ListTarefasRepository listTarefasRepository;

    @Autowired
    private TarefaRepository tarefaRepository;

    public Map<String, Object> verificarCampos(BindingResult bindingResult){
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", HttpStatus.UNPROCESSABLE_ENTITY.value());
        errorResponse.put("message", "Validation error");

        Map<String, String> errors = new HashMap<>();
        for(FieldError error : bindingResult.getFieldErrors()){
            System.out.println(error);
            errors.put(error.getField(), error.getDefaultMessage());
        }
        errorResponse.put("errors", errors);
        return errorResponse;
    }

    @Transactional(readOnly = false)
    public ListTarefas salvarTarefa(ListTarefas listTarefas) throws DataAccessException {
        List<Tarefa> tarefas = listTarefas.getTarefas();
        for(Tarefa tarefa : tarefas){
            tarefa.setListTarefas(listTarefas);
        }
        return listTarefasRepository.save(listTarefas);
    }

    @Transactional(readOnly = true)
    public List<ListTarefas> listarTodos() {
        return listTarefasRepository.findAll();
    }

    @Transactional(readOnly = true)
    public ListTarefas buscarPorId(Long id) throws EntityNotFoundException{
        return listTarefasRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Lista não encontrada")
        );
    }

    @Transactional(readOnly = false)
    public void excluirTarefa(Long id) throws DataAccessException, EntityNotFoundException {
        listTarefasRepository.deleteById(id);
    }

    @Transactional(readOnly = false)
    public ListTarefas atualizarTarefa(Long id, ListTarefas listTarefas) throws EntityNotFoundException{
        final ListTarefas bdListTarefas = buscarPorId(id);
        bdListTarefas.setDescricao(listTarefas.getDescricao());
        bdListTarefas.setDataLimite(listTarefas.getDataLimite());

        List<Tarefa> tarefas = listTarefas.getTarefas();
        List<Tarefa> bdTarefas = bdListTarefas.getTarefas();
        updateTarefas(bdListTarefas, tarefas, bdTarefas);

        return listTarefasRepository.save(bdListTarefas);
    }

    private void updateTarefas(ListTarefas bdListTarefas, List<Tarefa> tarefas, List<Tarefa> bdTarefas){
        List<Tarefa> bdTarefasExcluidas = bdTarefas
                .stream()
                .filter(t -> !tarefas.contains(t))
                .toList();
        List<Tarefa> tarefasNovas = tarefas
                .stream()
                .filter(t -> !bdTarefas.contains(t))
                .toList();
        List<Tarefa> bdTarefasParaUpdate = bdTarefas
                .stream()
                .filter(t -> !(bdTarefasExcluidas.contains(t) || tarefasNovas.contains(t)))
                .toList();

        for(Tarefa tarefa : tarefasNovas){
            tarefa.setListTarefas(bdListTarefas);
            bdTarefas.add(tarefa);
        }

        for(Tarefa tarefa : bdTarefasExcluidas){
            tarefa.setListTarefas(null);
            bdTarefas.remove(tarefa);
        }

        for(Tarefa tarefa : bdTarefasParaUpdate){
            Optional<Tarefa> tarefaRecebida = tarefas.stream()
                    .filter(t -> t.equals(tarefa))
                    .findFirst();
            tarefaRecebida.ifPresent((t) -> tarefa.update(t));
        }
    }

}
