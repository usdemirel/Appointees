package com.notsecure.Appointees.security;

import org.springframework.stereotype.Service;

@Service
public class AppointmentSecurityServiceImpl implements AppointmentSecurityService {
@Override
public boolean authorize() {
  return true;
}
}
