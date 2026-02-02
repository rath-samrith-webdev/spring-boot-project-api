@echo off
echo Running Seeders...
call mvnw spring-boot:run -Dspring-boot.run.arguments=--seed
echo Done.
