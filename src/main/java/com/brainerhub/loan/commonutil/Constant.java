package com.brainerhub.loan.commonutil;

public class Constant {

    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String EXCEPTION = "Something went wrong";
    public static final String INVALID_USER_PASS = "Invalid email or password";
    public static final String LOGIN_SUCCESS = "Login successful";

    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5 * 60 * 60;
    public static final String SIGNING_KEY = "devglan123r";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTHORITIES_KEY = "scopes";

    public static final String GetEmailFromTokenException = "An error occurred during getting username from token.";
    public static final String TokenExpiredException = "the token is expired and not valid anymore.";
    public static final String SignatureException = "Authentication Failed. Username or Password not valid.";
    public static final String ADMIN_EMAIL = "brainerhub@gmail.com";

}
