package com.packt.webstore.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

//this class is needed in order for the SecurityConfig to be loaded on start up
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
}