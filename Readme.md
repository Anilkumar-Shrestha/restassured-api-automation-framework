# REST ASSURED - API automation framework

This project offers a robust RestAssured API framework designed with an efficient and scalable folder structure. It is tailored to enhance reusability and maintainability, making it easy to add new test cases without the hassle of framework setup and maintenance. Users can quickly fork or download the project and begin writing and executing tests with minimal configuration.

### Frameworks Technology used:
* [Java](https://www.java.com/en/) - programming language
* [TestNG](https://github.com/cbeust/testng) - Testing framework
* [Rest Assured](https://rest-assured.io/) - testing and validation of REST APIs.
* [Maven](https://maven.apache.org/) - Build management tool
* [ExtentReport](https://www.extentreports.com/docs/versions/5/java/index.html) - Reporting Framework
* [Logback](https://logback.qos.ch/) - Logger
* [Lombok](https://projectlombok.org/) - Autocode generator (getters, setters, constructors, builders)
* [AWS-Secret Manager](https://aws.amazon.com/secrets-manager/) - Storing and Retrieving secrets/Credentials.
* [Docker](https://www.docker.com/) - Packaging the Automation Framework for building and Executing.
* [Aeonbits.owner](https://github.com/matteobaccan/owner) - OWNER, an API to ease Java property files usage.

### Architecture diagram 

![archi_diagram.png](src/main/resources/readme.files/archi_diagram.png)


### Folder Structure definition

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
│   │   │               └─── <del> PropertiesManager.java      // to load the config and other properties files </del>  This has been replaced by aeonbits owner
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

### Contributions
Contributions to this project are welcome! If you have suggestions for improving existing framework, tests, adding new features, or enhancing the documentation, please feel free to submit a pull request.

If you have any questions / suggestions / comments on the report, please feel free to reach me at
- Email: <a href="mailto:nl.shrestha90@gmail.com?Subject=Restassured-api-automation-framework" target="_blank">`nl.shrestha90@gmail.com`</a>
- LinkedIn: <a href="https://www.linkedin.com/in/anil-kumar-shrestha/" target="_blank">`Anil Kumar Shrestha`</a>

---


:star: repo if you like it.

