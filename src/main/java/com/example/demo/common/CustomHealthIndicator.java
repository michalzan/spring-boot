package com.example.demo.common;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

public class CustomHealthIndicator implements HealthIndicator {

    @Override
    public Health getHealth(boolean includeDetails) {
        Health health = Health.up().build();
        if (includeDetails) {
            health.getDetails().put("externa sluzba", check());
        }
        return health;
    }

    @Override
    public Health health() {
        return null;
    }

    private Boolean check(){
        return true;
    }

}
