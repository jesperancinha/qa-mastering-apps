package org.jesperancinha.car.lease.security;

public class JwtConstants {

    public static final String SECRET = "SECRET_KEY";
    public static final long EXPIRATION_TIME = 900_000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
