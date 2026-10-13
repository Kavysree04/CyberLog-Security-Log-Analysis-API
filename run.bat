@echo off
setlocal

REM Build the Spring Boot project using Maven wrapper if available.
if exist mvnw.cmd (
    echo Building with Maven Wrapper...
    call mvnw.cmd clean package -DskipTests
) else if exist mvnw (
    echo Building with Maven Wrapper (Linux/Mac shell)...
    call ./mvnw clean package -DskipTests
) else (
    echo Building with Maven...
    mvn clean package -DskipTests
)

if errorlevel 1 (
    echo Build failed.
    exit /b %errorlevel%
)

REM Find the built JAR in the target folder.
set "JAR="
for %%f in (target\*.jar) do (
    set "JAR=%%~ff"
)

if not defined JAR (
    echo No JAR file found in target folder.
    exit /b 1
)

echo Starting application: %JAR%
java -jar "%JAR%"
