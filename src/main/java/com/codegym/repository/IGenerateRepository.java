package com.codegym.repository;

import java.util.List;

public interface IGenerateRepository<E> {
    List<E> findStudentByName(String name);
    List<E> findAll();

    E findStudentById(Long id);

    void save(E e);

    void remove(Long id);
}
