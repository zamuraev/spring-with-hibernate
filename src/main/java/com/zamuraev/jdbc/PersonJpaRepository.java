package com.zamuraev.jdbc;

import com.zamuraev.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class PersonJpaRepository {

    private final EntityManager entityManager;

    @Autowired
    public PersonJpaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Person findById(int id) {
        return entityManager.find(Person.class, id);
    }

    public List<Person> findAll(){
        TypedQuery<Person> namedQuery = entityManager.createNamedQuery("find_all_persons", Person.class);
        return namedQuery.getResultList();
    }


    public Person update(Person person) {
        return entityManager.merge(person);
    }

    public Person insert(Person person) {
        return entityManager.merge(person);
    }

    public Person delete(int id) {
        Person person = findById(id);
        entityManager.remove(person);
    }

}
