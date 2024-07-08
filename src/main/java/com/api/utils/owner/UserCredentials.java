package com.api.utils.owner;

import org.aeonbits.owner.Config;

@Config.Sources(value="file:${user.dir}/src/test/resources/credentials/UserCredentials.properties")
public interface UserCredentials extends Config {

    String user1Email();
    String user1Password();
}
