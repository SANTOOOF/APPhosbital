package org.app.apphospital.security.service;

import org.app.apphospital.security.entities.AppRole;
import org.app.apphospital.security.entities.AppUser;

public interface AccountService {
    AppUser addNewUser(String username, String password, String email, String confirmPassword);

    AppRole addNewRole(String role);

    void addRoleToUser(String username, String role);

    void removeRoleFromUser(String username, String role);
    AppUser loadUserByUsername(String username);
}
