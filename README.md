# TaskManager

## About the API

This API allows you to add users, as well as tasks for those users. You will be able to view all users that you've added and also their tasks. You can also delete those tasks via the API.

A task will be added with a default status of PENDING. The status is internally controlled and cannot be influenced via the API. After adding a task a cron job will run every 10 seconds, changing the status from PENDING to DONE.

## Database Migration Tool

The application makes use of the Flyway database migration tool. Any changes to the database should be added to the main/resources/db/migration directory.

## API Documentation

After running the application, you can access the API documentation using the following link:
http://localhost:8080/api/swagger-ui.html

## Unit Tests

Unit Tests makes use of a seperate application.properties file that can be located under the test/resources directory.

## Postman Tests

The postman tests can be located in the root directory of the project. You can import these test to manually call the API.
