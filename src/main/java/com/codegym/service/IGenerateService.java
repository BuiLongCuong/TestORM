package com.codegym.service;

import com.codegym.model.Student;

import java.util.List;

public interface IGenerateService<T> {
    List<T> findStudentByName(String name);
    List<T> findAll();

    void save(T t);

    T findStudentById(Long id);

    void remove(Long id);
}
