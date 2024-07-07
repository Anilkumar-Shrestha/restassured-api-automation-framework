# REST ASSURED - API automation framework

This project is created for simple RestAssured API framework so that anyone can download/fork to use with below details:

 ```text
├───.ci
│   └───deployments                                     // consists of DockerFile or Jenkins file
├───.github                                             // github pr template or github actions
├───extent-test-report                                  // html report are kept here
├───src     
│   ├───main        
│   │   ├───java        
│   │   │   └───com     
│   │   │       └───api     
│   │   │           ├───applications        
│   │   │           │   └───authenticate                // Applications common method like authtoken extract method
│   │   │           ├───constants                       // contains constants used across frameworks like RESOURCES_FOLDER_PATH
│   │   │           ├───customexceptions                // contains custom exceptions to log your won exception for better traceability
│   │   │           ├───enums                           // all the enums like ENV
│   │   │           ├───listeners                       // All listeners like ExtentReport/RetryFailer listeners
│   │   │           ├───models      
│   │   │           │   ├───builders                    //wrapper builders to build request-specification
│   │   │           │   └───pojo        
│   │   │           │       └───booking                 // pojo classes with builder design
│   │   │           └───utils                           // all utilities
│   │   │               ├───aws                 
│   │   │               ├───faker
│   │   │               ├───helper
│   │   │               ├───loggerator
│   │   │               └───reporter
│   │   │               └───PropertiesManager.java      // to load the config and other properties files
│   │   └───TestBase.java                               // TestBase to do initial set up before suites are executed
│   │   └───resources
│   └───test
│       ├───downloads
│       │   └───common
│       ├───java
│       │   └───com
│       │       └───api                                     // all test classes
│       │           ├───auth
│       │           ├───deleteRequests
│       │           ├───getRequests
│       │           ├───patchRequests
│       │           ├───ping
│       │           ├───postRequests
│       │           └───putRequests
│       └───resources                                       // all resources needed for tests
│           ├───config
│           ├───credentials
│           └───json
│               └───schema

```

This project uses : https://restful-booker.herokuapp.com/apidoc/index.html for all kind of requests sample.
