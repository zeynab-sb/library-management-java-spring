package com.library.authentication.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class TokenService {

    private final SecureRandom random = new SecureRandom();
    private static final long RESEED = 100000L;
    private static final String instanceNo = "001";
    private static final AtomicLong COUNTER = new AtomicLong();

    public String createToken() {
        synchronized (this.random) {
            long r0 = this.random.nextLong();

            // random chance to reseed
            if (r0 % RESEED == 1L) {
                this.random.setSeed(this.random.generateSeed(8));
            }

            long r1 = this.random.nextLong();
            return this.instanceNo + Long.toString(r0, 36) + Long.toString(r1, 36) + COUNTER.getAndIncrement();
        }
    }
//        public static final String TOKEN_SECRET = "s4T2zOIWHMM1sxq";
//
//        public String createToken(Long userId) {
//            try {
//                Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
//                String token = JWT.create()
//                        .withClaim("userId", userId.toString())
//                        .withClaim("createdAt", new Date())
//                        .sign(algorithm);
//                return token;
//            } catch (UnsupportedEncodingException exception) {
//                exception.printStackTrace();
//                //log WRONG Encoding message
//            } catch (JWTCreationException exception) {
//                exception.printStackTrace();
//                //log Token Signing Failed
//            }
//            return null;
//        }
}
