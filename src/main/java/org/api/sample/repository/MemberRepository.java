package org.api.sample.repository;

import org.api.sample.model.Members;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends CrudRepository<Members, String> {

    Optional<Members> findById(String id);
    List<Members> findAll();
}
