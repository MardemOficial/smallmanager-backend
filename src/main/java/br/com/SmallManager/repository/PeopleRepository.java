package br.com.SmallManager.repository;

import br.com.SmallManager.domain.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PeopleRepository extends JpaRepository<People, UUID> {
}
