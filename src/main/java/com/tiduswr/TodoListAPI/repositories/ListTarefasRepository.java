package com.tiduswr.TodoListAPI.repositories;

import com.tiduswr.TodoListAPI.model.ListTarefas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListTarefasRepository extends JpaRepository<ListTarefas, Long> {
}
