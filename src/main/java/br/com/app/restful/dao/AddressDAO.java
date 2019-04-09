package br.com.app.restful.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.Predicate;
import javax.ws.rs.NotFoundException;

import br.com.app.restful.model.Address;

public class AddressDAO extends AbstractDAO<Address> implements DAO<Address> {

    public static final List<String> FIELDS = Arrays.asList("description", "street", "client");
    public static final List<Predicate> RESTRICTIONS = new ArrayList<>();
    public static final List<String> JOINS = Arrays.asList("client");
    
    @Override
    public Address find(final Long id) throws NotFoundException {
    	try {
    		return findWithCriteria(id, FIELDS);
    	} catch (NoResultException e) {
			throw new NotFoundException("Address not found");
		}
    }
    
    public List<Address> findByStreet(final String street) throws NotFoundException {
    	RESTRICTIONS.add(builder.like(root.get("street"), "%" + street + "%"));
    	return listWithCriteria(FIELDS, RESTRICTIONS);
    }

    @Override
	public List<Address> list() {    	
    	return listWithCriteria(FIELDS, RESTRICTIONS, JOINS);
	}

}
