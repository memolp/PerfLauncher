
rem "�Ƴ��ɵ�class"
del script\*.class

::set PATH=".\jre1.8\bin";%PATH%
set WORK_PATH=%cd%
:: javaʹ�õ�so���Ŀ¼
set JAVA_LIB=%WORK_PATH%\libs
set CP_PATH=.;PerfLauncher_app.jar;
set CMD=-cmd config.xml -scene org.genesis.robot.GenesisSceneMgr -data org.jeff.robot.concurrence.jython.JythonRuntimeData
::-Djava.library.path="%JAVA_LIB%"
:: �������� -- �ֿⷽʽ����������޸�ҵ����������Ҫ������������ 
::java -cp %CP_PATH% -Djava.library.path="%JAVA_LIB%" -Xmx6144m org.jeff.main.MainLauncher %CMD%

:: �������� -- �˷�ʽ������Ҫ��������jar��Ҳ������е�jar��
java -jar -Xms256m -Xmx512m -Djava.library.path="%JAVA_LIB%" PerfLauncher_app.jar %CMD%
pause