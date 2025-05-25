package org.app.apphospital.security.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.app.apphospital.security.entities.AppRole;

public interface AppRoleRepository extends JpaRepository<AppRole, String> {
}