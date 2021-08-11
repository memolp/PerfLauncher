该目录存储jar包和dll动态库。
运行期间可以添加新的jar包目录到程序中，但是动态链接库只能放在当前目录。
当前依赖的库列表：
1. jython (jython-standalone-2.7.2.jar) 支持python写压测脚本，运行时会先编译成class
2. log4j (log4j-api-2.3.jar log4j-core-2.3.jar) 日志
3. jfreechart (jcommon-1.0.0.jar jfreechart-1.0.1.jar) 用于GUI显示图
4. sqlite (sqlite-jdbc-3.14.2.jar) 用于将压测的数据写入数据库