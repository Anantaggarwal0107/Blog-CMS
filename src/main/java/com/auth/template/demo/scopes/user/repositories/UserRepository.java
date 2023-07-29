package com.auth.template.demo.scopes.user.repositories;


import com.auth.template.demo.scopes.user.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {
    @Override
    List<User> findAll();

    @Query("select e from #{#entityName} as e where e.email = ?1")
    Optional<User> findFirstByEmail(final String email);

    Optional<User> findUsersByNickname(final String nickname);

    @Query("select e from #{#entityName} as e order by e.email asc")
    List<User> findAllByOrderByEmailAsc();

    List<User> findUsersByLastnameContaining(final String search);

    List<User> findUsersByFirstnameContaining(final String search);

    List<User> findUsersByEmailContaining(final String search);
}
