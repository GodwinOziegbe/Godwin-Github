package com.example.carRentalBackend.BasicSecurity;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet()),

    CUSTOMER(Sets.newHashSet(ApplicationUserPermission.CAR_READ,ApplicationUserPermission.CUSTOMER_READ,
            ApplicationUserPermission.RENTAL_READ,ApplicationUserPermission.RENTAL_WRITE )),

    ADMIN(Sets.newHashSet(ApplicationUserPermission.COURSE_READ,
            ApplicationUserPermission.COURSE_WRITE,ApplicationUserPermission.STUDENT_READ,
            ApplicationUserPermission.STUDENT_WRITE,ApplicationUserPermission.CAR_READ,
            ApplicationUserPermission.CAR_WRITE,ApplicationUserPermission.CUSTOMER_READ,
            ApplicationUserPermission.CUSTOMER_WRITE,ApplicationUserPermission.RENTAL_READ,
            ApplicationUserPermission.RENTAL_WRITE)),

    ADMINTRAINEE(Sets.newHashSet(ApplicationUserPermission.COURSE_READ,ApplicationUserPermission.STUDENT_READ
          ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority>permissions= getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return permissions;

    }
}
