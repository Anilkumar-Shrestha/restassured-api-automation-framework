//package com.api.utils;
//
//import com.api.customexceptions.FrameworkException;
//import com.api.enums.ENV;
//import org.testng.annotations.Optional;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Objects;
//import java.util.Properties;
//import java.util.stream.Stream;
//
//import static com.api.constants.FrameworkConstants.RESOURCES_FOLDER_PATH;
//import static com.api.utils.loggerator.Logger.getLogger;
//
///*
//Replaced by aeonbits owner
// */
//public class PropertiesManager {
//
//    private static final Properties props;
//
//    static {
//        props = new Properties();
//        loadProperties();
//    }
//
//    private static void loadProperties() {
//        listFilesInDirectory(new File(RESOURCES_FOLDER_PATH))
//                .filter(file -> file.getName().contains(".properties"))
//                .forEach(file -> {
//                    getLogger().info("Loading property file {}", file.getName());
//                    try (InputStream in = new FileInputStream(file.getAbsolutePath())) {
//                        props.load(in);
//                    } catch (IOException e) {
//                        getLogger().error("Properties not loaded", e.getCause());
//                    }
//
//                });
//    }
//
//    public synchronized static String getProperty(String key) {
//        if (Objects.isNull(props.getProperty(key))) {
//            throw new FrameworkException("Property name - " + key + " is not found. Please check the config.properties");
//        }
//        String property = props.getProperty(key);
//        getLogger().debug("Return value {} for property key {}", property, key);
//        return property;
//    }
//
//    public synchronized static String getProperty(String key, @Optional String defaultValue) {
//        String property = props.getProperty(key);
//        getLogger().debug("Return value {} for property key {} " , property,key);
//        return (property == null) ? defaultValue : property;
//    }
//
//
//    public synchronized static String getAppApiUrlForEnv(ENV env) {
//        String retVal = "";
//        switch (env) {
//            case STAGE:
//                retVal = props.getProperty("stage_base_uri");
//                break;
//            case PROD:
//                retVal = props.getProperty("prod_base_uri");
//                break;
//            default:
//                throw new IllegalArgumentException("Invalid Environment:" + env);
//        }
//        return retVal.endsWith("/") ? retVal.substring(0, retVal.length() - 1) : retVal;
//    }
//
//
//
//    // Recursive method to list all files in a directory and its subdirectories
//    private static Stream<File> listFilesInDirectory(File directory) {
//        return Stream.of(Objects.requireNonNull(directory.listFiles()))
//                .flatMap(file -> file.isDirectory() ? listFilesInDirectory(file) : Stream.of(file));
//    }
//
//}
