@echo off
if "%1"=="stop" (
call:stop
goto :eof
)
if "%1"=="start" (
for /f "delims=" %%t in ('docker ps -a -f "name=nacos" -q') do set nacos_container=%%t
if "%nacos_container%" NEQ "" (
call:restart
pause
goto :eof
)
for /f "delims=" %%t in ('docker images -f "reference=nacos/nacos-server" -q') do set nacos_images=%%t
if "%nacos_images%" EQU "" (
call:pull
)
call:run
goto :eof
)
goto :eof

:pull
docker pull nacos/nacos-server

:run
docker run -d -p 8848:8848 --env MODE=standalone  --name nacos  nacos/nacos-server

:stop
docker stop nacos

:restart
docker start nacos