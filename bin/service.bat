@echo off
setlocal enabledelayedexpansion

rem +========================================================================+
rem | Author: Silence H_VK (Windows version)                                 |
rem | Since: 2021-12-02                                                      |
rem +------------------------------------------------------------------------+

set SERVICE_NAME=#SERVICE_NAME#
set MAIN_CLASS=#MAIN_CLASS#
rem set JAVA_OPTS=

set BASE_DIR=%~dp0
set BASE_DIR=%BASE_DIR:~0,-1%

@REM Verify java env
if not "%JAVA_HOME%" == "" (
  "%JAVA_HOME%\bin\java" -version >nul 2>&1
) else (
  java -version >nul 2>&1
)
if errorlevel 1 (
  echo +========================================================================+
  echo ^| Error: Java Environment is not available, Please check your JAVA_HOME  ^|
  echo +------------------------------------------------------------------------+
  exit /b 1
)

@REM Which java to use
if "%JAVA_HOME%" == "" (
  set JAVA=java
  set JPS=jps
) else (
  set JAVA=%JAVA_HOME%\bin\java
  set JPS=%JAVA_HOME%\bin\jps
)

@REM Config directory to use
if "%SERVICE_CONF%" == "" (
  set SERVICE_CONF=%BASE_DIR%\..\conf
)

@REM Set JAVA_OPTS
if "%JAVA_OPTS%" == "" (
  set JAVA_OPTS=-Xms1g -Xmx1g -XX:+UseCompressedOops -XX:+HeapDumpOnOutOfMemoryError -Dfile.encoding=GBK
) else (
  set JAVA_OPTS=%JAVA_OPTS% -Dfile.encoding=GBK
)

@REM Set CLASS_PATH
set CLASS_PATH=%BASE_DIR%\..\lib\*;%SERVICE_CONF%;%BASE_DIR%\..\*


@REM Call start
"%JAVA%" %JAVA_OPTS% -cp "%CLASS_PATH%" %MAIN_CLASS%
pause