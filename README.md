# This is my first SpringBoot project.

## How to start:

git clone 

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
