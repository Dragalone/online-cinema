package com.example.onlinecinemabackend.service;

import com.example.onlinecinemabackend.aop.AccessCheckType;
import com.example.onlinecinemabackend.aop.Accessible;
import jakarta.servlet.http.HttpServletRequest;

public interface AccessCheckerService {

    boolean check(HttpServletRequest request, Accessible accessible);

    AccessCheckType getType();

}
