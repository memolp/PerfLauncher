
rem "移除旧的class"
del script\*.class

::set PATH=".\jre1.8\bin";%PATH%
set WORK_PATH=%cd%
:: java使用的so库的目录
set JAVA_LIB=%WORK_PATH%\libs
set CP_PATH=.;PerfLauncher_app.jar;
set CMD=-cmd config.xml -scene org.genesis.robot.GenesisSceneMgr -data org.jeff.robot.concurrence.jython.JythonRuntimeData
::-Djava.library.path="%JAVA_LIB%"
:: 启动命令 -- 分库方式，方便快速修改业务代码而不需要包含第三方库 
::java -cp %CP_PATH% -Djava.library.path="%JAVA_LIB%" -Xmx6144m org.jeff.main.MainLauncher %CMD%

:: 启动命令 -- 此方式启动需要将第三方jar库也打进运行的jar中
java -jar -Xms256m -Xmx512m -Djava.library.path="%JAVA_LIB%" PerfLauncher_app.jar %CMD%
pause