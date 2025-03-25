package br.com.SmallManager.repository;

import br.com.SmallManager.model.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser, UUID> {

    Optional<SystemUser> findByNameAndPassword(String name, String password);

    Optional<SystemUser> findByName(String name);

}
