package com.codegym.service;

import com.codegym.model.Student;
import com.codegym.repository.IStudentRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService implements IStudentService {
    @Autowired
    private IStudentRepository iStudentRepository;

    @Override
    public List<Student> findStudentByName(String name) {
        return iStudentRepository.findStudentByName(name);
    }

    @Override
    public List<Student> findAll() {
        return iStudentRepository.findAll();
    }

    @Override
    public void save(Student student) {
        iStudentRepository.save(student);
    }

    @Override
    public Student findStudentById(Long id) {
        return iStudentRepository.findStudentById(id);
    }

    @Override
    public void remove(Long id) {
        iStudentRepository.remove(id);
    }

}
