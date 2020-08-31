@Echo off
cd /D "%~dp0"
call mvn test
pause