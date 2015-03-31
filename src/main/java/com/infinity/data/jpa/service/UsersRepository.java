
package com.infinity.data.jpa.service;

import com.infinity.data.jpa.domain.Users;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author faulken
 */
public interface UsersRepository extends CrudRepository<Users, Integer> {

	
    @Override
    List<Users> findAll();

    
    @Override 
    Users findOne(Integer id);
    
    

}
