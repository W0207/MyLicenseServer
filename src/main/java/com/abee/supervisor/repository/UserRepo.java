package com.abee.supervisor.repository;

import com.abee.supervisor.domain.User;
import com.abee.supervisor.domain.UserPK;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xincong yao
 */
@Repository
public interface UserRepo extends CrudRepository<User, String> {

    User findByUserPK(UserPK userPK);

    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(nativeQuery = true, value = "update user " +
            "set state = 0 " +
            "where state = 1")
    void forbidAll();
}
