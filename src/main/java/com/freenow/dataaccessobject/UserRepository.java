package com.freenow.dataaccessobject;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freenow.domainobject.UserDO;

public interface UserRepository extends JpaRepository<UserDO, Long>
{
    Optional<UserDO> findByUsername(String userName);
}
