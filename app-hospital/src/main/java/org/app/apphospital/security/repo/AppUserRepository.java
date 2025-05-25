package org.app.apphospital.security.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.app.apphospital.security.entities.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
    AppUser findByUsername(String username);
}
