package com.api.utils.owner;

import org.aeonbits.owner.Config;

@Config.Sources(value = "file:${user.dir}/src/test/resources/config/TestConfig.properties")
public interface TestConfig extends Config {
    Boolean credentialsFromLocalFile();
    Boolean overrideReports();
    Boolean retryFailedTests();
    int retryCount();
    String stage_base_uri();
    String prod_base_uri();
}
