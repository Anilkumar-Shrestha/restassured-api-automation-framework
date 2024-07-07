package com.api;

import com.api.constants.FrameworkConstants;
import com.api.enums.ENV;
import com.api.listeners.AnnotationTransformer;
import com.api.listeners.ExtentReportListener;
import com.api.models.BaseSetup;
import com.api.utils.PropertiesManager;
import com.api.utils.helper.SetCredentialsForSuite;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;


import java.io.File;
import java.util.Objects;

import static com.api.utils.loggerator.Logger.getLogger;

@Listeners( { ExtentReportListener.class, AnnotationTransformer.class } )
public class TestBase {

    public static BaseSetup baseSetup = new BaseSetup();


    @SneakyThrows
    @BeforeSuite(alwaysRun = true)
    @Parameters({"env"})
    public void initialSetUp(@Optional("stage") String env) {
        getLogger().info("---------------- Test Suite setUp started -----------");

        baseSetup.setEnvironment(env);
        baseSetup.setAppApiUrl(PropertiesManager.getAppApiUrlForEnv(ENV.getEnvByValue(env)));
        getLogger().info(baseSetup.toString());

//		setting Up credentials
        if (Objects.equals(PropertiesManager.getProperty("credentialsFromLocalFile"), "true")) {
            SetCredentialsForSuite.setCredentialsFromLocalFile();
        } else {
            SetCredentialsForSuite.setCredentialsFromAws();
        }


        //        creating filed download directory if not present (used File.separator since when using "/" for windows it does not work in download.

        File downloadFolder = new File(FrameworkConstants.FIlE_DOWNLOAD_PATH);
        if (downloadFolder.exists()) {
            FileUtils.forceDelete(downloadFolder);
        }
        downloadFolder.mkdirs();
        getLogger().info("browser download directory set to " + FrameworkConstants.FIlE_DOWNLOAD_PATH);

    }


}
