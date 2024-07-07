package com.api.enums;

import java.util.stream.Stream;

import static com.api.utils.loggerator.Logger.getLogger;

public enum ENV {
    STAGE("stage"),
    PROD("prod");

    private final String value;

    ENV(String value){
        this.value= value;
    }

    public String getValue() {
        return value;
    }

    public static ENV getEnvByValue(String env) {
        ENV environment = Stream.of(ENV.values()).filter(s -> s.getValue().trim().toLowerCase().equalsIgnoreCase(env))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Unsupported env " + env));
        getLogger().info(env + " env found and now environment set as "+ environment);
        return environment;
    }
}
