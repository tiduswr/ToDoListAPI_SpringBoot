package com.tiduswr.TodoListAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tarefas")
public class Tarefa extends GenericEntity{

    @NotNull @NotBlank
    @Column(name = "descricao", nullable = false, length = 100)
    private String descricao;

    @NotNull
    @Column(name = "concluido", nullable = false, columnDefinition = "BOOLEAN")
    private Boolean done;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "lista_tarefas_id")
    private ListTarefas listTarefas;

    public ListTarefas getListTarefas() {
        return listTarefas;
    }

    public void setListTarefas(ListTarefas listTarefas) {
        this.listTarefas = listTarefas;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
