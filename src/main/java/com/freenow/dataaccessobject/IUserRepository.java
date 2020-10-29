package com.freenow.dataaccessobject;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freenow.domainobject.UserDO;

@Repository
public interface IUserRepository extends JpaRepository<UserDO, Long>
{
    Optional<UserDO> findByUsername(String userName);
}
