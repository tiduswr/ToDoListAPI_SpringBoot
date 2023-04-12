package com.tiduswr.TodoListAPI.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "lista_tarefas")
public class ListTarefas extends GenericEntity{

    @Column(name = "lista_tarefas_id")
    private Long id;

    @NotNull @NotBlank
    @Column(name = "descricao")
    private String descricao;

    @NotNull
    @Column(name = "data_limite", columnDefinition = "DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataLimite;

    @NotNull
    @Valid
    @OneToMany(mappedBy = "listTarefas", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tarefa> tarefas;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(LocalDate dataLimite) {
        this.dataLimite = dataLimite;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
