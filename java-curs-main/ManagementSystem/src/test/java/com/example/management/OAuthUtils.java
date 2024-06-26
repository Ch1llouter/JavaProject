package com.example.management;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.asList;

public class OAuthUtils {

    public static String getAccessToken() {
        return "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6InNDUUpNYnRMYlJIZHVicUZZNHdBNCJ9.eyJpc3MiOiJodHRwczovL2Rldi00cGQ0dnBvc3phYnN4eG4xLnVzLmF1dGgwLmNvbS8iLCJzdWIiOiJiMHd0bkVXTWc5VGhnZzdaZG9HQWtxZDMxTGdWaDI4RkBjbGllbnRzIiwiYXVkIjoiaHR0cHM6Ly9kZXYtNHBkNHZwb3N6YWJzeHhuMS51cy5hdXRoMC5jb20vYXBpL3YyLyIsImlhdCI6MTcxODgxMDkzNywiZXhwIjoxNzE4ODk3MzM3LCJndHkiOiJjbGllbnQtY3JlZGVudGlhbHMiLCJhenAiOiJiMHd0bkVXTWc5VGhnZzdaZG9HQWtxZDMxTGdWaDI4RiJ9.hT1O2s5f-VxIt2kswxaF9CYdeyVvY_ET666ZloCcmKKjOynyqxTWDGDyFg2eRQxJiBBK9j0I5D28ynNpmGNCk_tCy7DYjvBugHCAY5dN_wcxBOdAcUAsXVtdcYxgfm6yrddLaGVRbB-5XjGehEZxdJYRHePKM196A5UUObAFhsRbzWZMXIdkj-xPbRT3ci5nBxIfU4lkjZnOOk4v-s-oFyuv0oZdSnk2p-JqpRgXnEZ4Clhv21fkVWxCK-0fEtGHXggOVusGgNq--bhDE3ElelSXSrr3d0MNK4RO3WZJadOzFqxuviLyEPIwGSMToUBCzM7X-lTXkOxpu2NPDi3P-w";
    }

    public static OAuth2User createOAuth2User(String name, String email) {

        Map<String, Object> authorityAttributes = new HashMap<>();
        authorityAttributes.put("key", "value");

        GrantedAuthority authority = new OAuth2UserAuthority(authorityAttributes);

        Map<String, Object> attributes = new HashMap<>();
        attributes.put("sub", "1234567890");
        attributes.put("name", name);
        attributes.put("email", email);

        return new DefaultOAuth2User(asList(authority), attributes, "sub");
    }

    public static Authentication getOauthAuthenticationFor(OAuth2User principal) {

        Collection<? extends GrantedAuthority> authorities = principal.getAuthorities();

        String authorizedClientRegistrationId = "my-oauth-client";

        return new OAuth2AuthenticationToken(principal, authorities, authorizedClientRegistrationId);
    }
}
