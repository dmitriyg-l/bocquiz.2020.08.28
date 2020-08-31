@Echo off
cd /D "%~dp0"
call mvn spring-boot:run
if not "%ERRORLEVEL%" == "0" pause