package br.com.cassunde.pessistence.impl;

import static br.com.cassunde.pessistence.impl.JpaUtils.transactional;

import java.util.List;

import javax.inject.Inject;

import br.com.cassunde.api.BasicDAO;
import br.com.cassunde.api.PeopleDAO;
import br.com.cassunde.model.People;

public class PeopleDAOImpl implements PeopleDAO {

    private final BasicDAO basicDAO;

    @Inject
    public PeopleDAOImpl(BasicDAO basicDAO) {
        this.basicDAO = basicDAO;
    }

    @Override
    public People create(People people) {
        basicDAO.create(people);
        return people;
    }

    @Override
    public People get(int idPeople) {
        return (People) basicDAO.get(People.class, idPeople);
    }

    @Override
    public People update(People people) {
        return (People) basicDAO.update(people);
    }

    @Override
    public List<People> list() {
    	@SuppressWarnings("unchecked")
		List<People> re = (List<People>) transactional(em -> em.createQuery("from People",People.class).getResultList(),"error");
    	return re;
    }

    @Override
    public long count() {
        return basicDAO.count(People.class);
    }

    @Override
    public void delete(int idPeople) {
        transactional(entityManager -> {
            final People people = entityManager.find(People.class, idPeople);
            entityManager.remove(people);
            return null;
        }, String.format("Error ao deletar ", idPeople));
    }
}
