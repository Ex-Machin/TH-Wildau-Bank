package com.dmytro.authorities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    default List<Authority> findByUsername(String username) {
        return null;
    }
}
