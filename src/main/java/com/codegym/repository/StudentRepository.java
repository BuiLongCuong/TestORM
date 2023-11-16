package com.codegym.repository;

import com.codegym.model.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class StudentRepository implements IStudentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Student> findStudentByName(String name) {
        TypedQuery<Student> query = entityManager.createQuery("SELECT s FROM Student s WHERE LOWER(s.name) LIKE CONCAT('%', LOWER(:name), '%')", Student.class);
        query.setParameter("name", name);
        return query.getResultList();
    }


    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = entityManager.createQuery("select s from Student s", Student.class);
        return query.getResultList();
    }

    @Override
    public Student findStudentById(Long id) {
        TypedQuery<Student> query = entityManager.createQuery("select s from Student s where s.id=:id", Student.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void save(Student student) {
        if (student.getId() != null) {
            entityManager.merge(student);
        } else {
            entityManager.persist(student);
        }
    }

    @Override
    public void remove(Long id) {
        Student student = findStudentById(id);
        if (student != null) {
            entityManager.remove(student);
        }
    }
}
