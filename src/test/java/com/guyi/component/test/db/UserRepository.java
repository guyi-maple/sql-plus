package com.guyi.component.test.db;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author guyi
 */
public interface UserRepository extends JpaRepository<User, String> {

    User findByAge(int age);

}
