package com.api.utils.helper;


import com.api.utils.PropertiesManager;
import com.api.utils.aws.GetSecret;
import org.json.simple.parser.ParseException;

import static com.api.utils.helper.PasswordEncryptionDecryption.getDecryptString;
import static com.api.utils.loggerator.Logger.getLogger;

public class SetCredentialsForSuite {

    public static PropertiesManager credentialProperties;

    public static String user1Email;
    public static String user1Password;

    private SetCredentialsForSuite(){

    }


    public static void setCredentialsFromAws() throws ParseException {
        getLogger().info("Retrieving credentials from aws secret manager");
        user1Email = GetSecret.getSecretKeyValue("user1Email");
        user1Password = getDecryptString(GetSecret.getSecretKeyValue("user1Password"));

    }

    public static void setCredentialsFromLocalFile() throws ParseException {
        getLogger().info("Retrieving credentials from local user credentials properties file");
        user1Email = PropertiesManager.getProperty("user1Email");
        user1Password = getDecryptString(PropertiesManager.getProperty("user1Password"));


    }

}
