# This is my first SpringBoot project.

## How to start:

- `git clone git@github.com:JimmyLv/SpringBoot-RESTful-MicroServices.git`
- `gradle clean build`
- open DataBase: `mongod`
- `gralde bootRun`

demo on heroku: [http://sb-restful-microservices.herokuapp.com](http://sb-restful-microservices.herokuapp.com)

## Some Notes:

### Gradle build script
  
- `jcenter()`: include mvn and other repo
- plugin: java、spring-boot
- compile/testCompile
- `gradle wrapper` (version: 2.5)

### build Project

- write code in `src/main/java`
- `gradle clean build` (then download dependencies)

### wirte code

- main application
- domain
- repository
- service
- resources
- controller
- configuration && properties
- note: package/import
- warning: info.jimmylv.info
- error: create another mongoDB

### `gradle bootRun`

- open mongoDB: `mongod`
- mongo shell: `mongo help`
- bootRun/jettyRun

### Unit Test

- JUnit: `SetUp(), `assertThat(, is())`
- Mockito: `@Mock`, `@InjectMocks`, `initMocks()`
- JUnitParams: `@RunWith(JUnitParamsRunner.class)`

```java
@Test
@Parameters
public void personIsAdult(int age, boolean valid) throws Exception {
    assertEquals(valid, new Person(age).isAdult());
}

private Object[] parametersForPersonIsAdult() {
    return new Object[]{
                 new Object[]{13, false},
                 new Object[]{17, false},
                 new Object[]{18, true},
                 new Object[]{22, true}
            };
}
```

### API Test

- [postman](https://www.getpostman.com/)
- post: `http://localhost:8080/saveperson`

```json
{
    "firstName": "Jimmy",
    "lastName": "lv",
    "profession": "Photography",
    "location": [
        119,
        -110
    ],
    "companies": [
        {
            "orgName": "ThoughtWorks",
            "headquarter": "chengdu"
        }
    ]
}
```

- get person list: `http://localhost:8080/persons`
- get person by id: `http://localhost:8080/person/?id=55b7191bd4c67ac8c00c89e9`

### Deploy

- `heroku login`
- `heroku keys:add ~/.ssh/id_rsa.pub`
- `heroku create`
- `git push heroku master`
- `heroku open`

**another config:**

- modify `buildscript`
- `apply plugin: 'heroku'`
- `heroku { someparameter}`
- add `Procfile`
- Default Tasks: `"clean", "build", "installApp"`
- add `system.properties`: `java.runtime.version=1.8`
- `./gradlew herokuCreateApp`
- `./gradlew herokuDeployApp`

**WTF! can not use mongose in heroku:**

>  !    Please verify your account to install this add-on plan (please enter a credit card) For more information, see [https://devcenter.heroku.com/categories/billing](https://devcenter.heroku.com/categories/billing) Verify now at [https://heroku.com/verify](https://heroku.com/verify)

### add checkstyle

- `apply plugin: 'checkstyle'`
- ./config/checkstyle/checkstyle.xml 
