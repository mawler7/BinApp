package com.management.warehouse.service;

import com.management.warehouse.model.PortalUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserService {

    PortalUser findByLogin(String login);

    Page<PortalUser> getUsersPaginated(Pageable pageable);

    Page<PortalUser> findByLastNameBeginsWith(String lastName, Pageable pageable);

    void save(PortalUser portalUser);

    void deleteUser(int id);

    PortalUser findById(int id);

    void updateUser(PortalUser portalUser);
}
