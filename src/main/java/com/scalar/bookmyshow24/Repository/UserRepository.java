package com.scalar.bookmyshow24.Repository;

import com.scalar.bookmyshow24.Models.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.function.Function;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //JPARepository -> Inbuilt support for all type of SQL queries.


    @Override
    Optional<User> findById(Long aLong);

    @Override
    User save(User user);

    Optional<User> findByEmail (String email);
}
