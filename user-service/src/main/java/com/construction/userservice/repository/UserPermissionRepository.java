package com.construction.userservice.repository;

import com.construction.userservice.entity.User;
import com.construction.userservice.entity.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPermissionRepository extends JpaRepository<UserPermission, Long> {
    List<UserPermission> findByUser(User user);
}
