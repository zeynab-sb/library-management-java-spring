package com.library.authentication.service;

import java.security.SecureRandom;
import java.util.concurrent.atomic.AtomicLong;

public class TokenService {

    private final SecureRandom random = new SecureRandom();
    private static final long RESEED = 100000L;
    private static final String instanceNo = "001";
    private static final AtomicLong COUNTER = new AtomicLong();

    public String createToken() {
        synchronized (this.random) {
            long r0 = this.random.nextLong();

            if (r0 % RESEED == 1L) {
                this.random.setSeed(this.random.generateSeed(8));
            }

            long r1 = this.random.nextLong();
            return this.instanceNo + Long.toString(r0, 36) + Long.toString(r1, 36) + COUNTER.getAndIncrement();
        }
    }
}
