package com.abee.supervisor.repository;

import com.abee.supervisor.domain.User;
import com.abee.supervisor.domain.UserPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xincong yao
 */
@Repository
public interface UserRepo extends CrudRepository<User, String> {

    User findByUserPK(UserPK userPK);
}
