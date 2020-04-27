package com.abee.supervisor.repository;

import com.abee.supervisor.domain.Organization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xincong yao
 */
@Repository
public interface OrganizationRepo extends CrudRepository<Organization, Integer> {

    Organization findByUsernameAndPassword(String username, String password);
}
