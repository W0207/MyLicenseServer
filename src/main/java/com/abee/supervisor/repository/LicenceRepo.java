package com.abee.supervisor.repository;

import com.abee.supervisor.domain.Licence;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.Executors;

/**
 * @author xincong yao
 */
@Repository
public interface LicenceRepo extends CrudRepository<Licence, String> {

    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(nativeQuery = true, value = "update licence " +
            "set current_number = current_number + 1 " +
            "where serial_number = :serialNumber " +
            "and current_number < type")
    int addCurrentNumber(@Param("serialNumber") String serialNumber);

    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(nativeQuery = true, value = "update licence " +
            "set current_number = current_number - 1 " +
            "where serial_number = :serialNumber " +
            "and current_number > 0")
    int subCurrentNumber(@Param("serialNumber") String serialNumber);

    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(nativeQuery = true, value = "update licence " +
            "set current_number = 0 ")
    void resetAll();
}
