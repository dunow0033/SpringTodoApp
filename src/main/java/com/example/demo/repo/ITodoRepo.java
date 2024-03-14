package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Todo;


@Repository
public interface ITodoRepo extends JpaRepository<Todo, Long>{

}
