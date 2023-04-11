package com.tiduswr.TodoListAPI.web.controller;

import com.tiduswr.TodoListAPI.model.ListTarefas;
import com.tiduswr.TodoListAPI.services.ListaTarefasService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tarefas/list")
public class ListTarefasRestController {

    @Autowired
    private ListaTarefasService listTarefasService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid ListTarefas listTarefas, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(listTarefasService.verificarCampos(bindingResult));
        }else{
            try{
                ListTarefas response = listTarefasService.salvarTarefa(listTarefas);
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(response);
            }catch(DataAccessException e){
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .build();
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid ListTarefas listTarefas,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(listTarefasService.verificarCampos(bindingResult));
        } else {
            try {
                ListTarefas response = listTarefasService.atualizarTarefa(id, listTarefas);
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(response);

            } catch (EntityNotFoundException e) {
                Map<String, Object> error = new HashMap<>();
                error.put("error", e.getMessage());
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(error);

            } catch (DataAccessException e) {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(e);
            }
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws ResponseStatusException{
        try {
            listTarefasService.excluirTarefa(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public List<ListTarefas> listAll(){
        return listTarefasService.listarTodos();
    }

    @GetMapping("/{id}")
    public ListTarefas findById(@PathVariable Long id) throws ResponseStatusException{
        try {
            return listTarefasService.buscarPorId(id);
        }catch(EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
