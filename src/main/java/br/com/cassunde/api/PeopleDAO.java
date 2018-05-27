package br.com.cassunde.api;

import java.util.List;

import br.com.cassunde.model.People;

public interface PeopleDAO {
    People create(People people);

    People get(int id);

    People update(People people);

    List<People> list();

    long count();

    void delete(int idPeople);
}
