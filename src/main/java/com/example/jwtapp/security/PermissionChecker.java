package com.example.jwtapp.security;

import com.example.jwtapp.model.User;

public class PermissionChecker {

    /**
     * Checks whether the given user has permission to perform a specific action on a module.
     *
     * @param user   The authenticated user
     * @param module The name of the module (e.g., "ProjectDashboard")
     * @param action The action to check ("view", "create", "edit", "delete")
     * @return true if the user has permission, false otherwise
     */
    public static boolean hasPermission(User user, String module, String action) {
        // Full access for ADMIN and CEO
        if ("ROLE_ADMIN".equals(user.getRole()) || "ROLE_CEO".equals(user.getRole())) {
            return true;
        }

        // Check module-specific permissions
        return user.getPermissions().stream()
                .filter(p -> p.getModule().equalsIgnoreCase(module))
                .anyMatch(p -> {
                    switch (action.toLowerCase()) {
                        case "view": return p.isCanView();
                        case "create": return p.isCanCreate();
                        case "edit": return p.isCanEdit();
                        case "delete": return p.isCanDelete();
                        default: return false;
                    }
                });
    }
}
