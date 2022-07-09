package com.example.carRentalBackend.BasicSecurity;

public enum ApplicationUserPermission {
    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    COURSE_READ("course:read"),
    COURSE_WRITE("course:write"),
    CAR_READ("car:read"),
    CAR_WRITE("car:write"),
    CUSTOMER_READ("customer:read"),
    CUSTOMER_WRITE("customer:write"),
    RENTAL_READ("rental:read"),
    RENTAL_WRITE("rental:write");
    private final String permission;


    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
