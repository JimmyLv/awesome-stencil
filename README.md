# This is my first SpringBoot project.

## Gradle build script
  
- jcenter()
- plugin
- compile/testCompile
- `gradle wrapper` (version: 2.5)

## build Project

- write code in `src/main/java`
- `gradle clean build` (then download dependencies)

## wirte code

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

## `gradle bootRun`

- open mongoDB: `mongod`
- mongo shell: `mongo help`
- bootRun/jettyRun

## API Test

- [postman](https://www.getpostman.com/)
- post: http://localhost:8080/saveperson

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

- get: http://localhost:8080/persons
