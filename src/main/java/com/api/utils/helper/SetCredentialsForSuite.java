package com.api.utils.helper;


import com.api.utils.aws.GetSecret;
import com.api.utils.owner.UserCredentials;
import org.aeonbits.owner.ConfigFactory;
import org.json.simple.parser.ParseException;

import static com.api.utils.helper.PasswordEncryptionDecryption.getDecryptString;
import static com.api.utils.loggerator.Logger.getLogger;

public class SetCredentialsForSuite {

    static UserCredentials userCredentialConfig = ConfigFactory.create(UserCredentials.class);

    public static String user1Email;
    public static String user1Password;


    private SetCredentialsForSuite() {

    }


    public static void setCredentialsFromAws() throws ParseException {
        getLogger().info("Retrieving credentials from aws secret manager");
        user1Email = GetSecret.getSecretKeyValue("user1Email");
        user1Password = getDecryptString(GetSecret.getSecretKeyValue("user1Password"));

    }

    public static void setCredentialsFromLocalFile() throws ParseException {
        getLogger().info("Retrieving credentials from local user credentials properties file");
        user1Email = userCredentialConfig.user1Email();
        user1Password =  getDecryptString(userCredentialConfig.user1Password());


    }

}
