
package com.infinity.data.jpa.service;

import com.infinity.data.jpa.domain.Candidate;



import java.util.List;


import org.springframework.data.repository.Repository;

/**
 *
 * @author faulken
 */
public interface CandidateRepository extends Repository<Candidate, Long> {

	
    List<Candidate> findAll();

    Candidate findOne(Long id);
   
    Candidate findById(Long id);



	void save(Candidate c1);
    
    

}
