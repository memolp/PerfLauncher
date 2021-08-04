package org.jeff.main;


import java.lang.reflect.Method;
import java.net.URLClassLoader;

/**
 * 启动引擎
 * @author 覃贵锋
 */
public class StartLaunchEngine
{
	private static final String JAVA_ROBOT_ENGINE = "org.jeff.ptc.PerfCenterServiceEngine";
	private static final String JAVA_ROBOT_LOADER = "SetClassLoader";
	private static final String JAVA_ROBOT_CMD_MODE = "LaunchConsole";
	private static final String JAVA_ROBOT_GUI_MODE = "LaunchGUI";
	private static final String JAVA_ROBOT_SVR_MODE = "LaunchService";
	private static final String JAVA_ROBOT_RUNTIME_CLASS = "SetRuntimeClass";
	private static final String JAVA_ROBOT_DATA_CLASS = "SetDataClass";
	private static final String JAVA_ROBOT_COLLECTOR_CLASS = "SetCollectorClass";
	/**
	 * 初始化启动参数
	 * @param config
	 */
	public void InitWithLaunch(LauncherConfig config)
	{
		try
		{
			Class<?> initClass = LauncherClassLoader.LoadClass(JAVA_ROBOT_ENGINE);
			Object instance = initClass.getDeclaredConstructor().newInstance();
			// 设置加载器
			Method loader_method = initClass.getMethod(JAVA_ROBOT_LOADER, URLClassLoader.class);
			loader_method.invoke(instance, LauncherClassLoader.classLoader);
			// 设置Runtime类
			if(config.sceneClassName != null && !config.sceneClassName.isEmpty())
			{
				Method runtime_method = initClass.getMethod(JAVA_ROBOT_RUNTIME_CLASS, String.class);
				runtime_method.invoke(instance, config.sceneClassName);
			}
			// 设置Data解析类
			if(config.sceneDataClassName != null && !config.sceneDataClassName.isEmpty())
			{
				Method data_method = initClass.getMethod(JAVA_ROBOT_DATA_CLASS, String.class);
				data_method.invoke(instance, config.sceneDataClassName);
			}
			// 设置收集类
			if(config.sceneCollectorName != null && !config.sceneCollectorName.isEmpty())
			{
				Method collector_method = initClass.getMethod(JAVA_ROBOT_COLLECTOR_CLASS, String.class);
				collector_method.invoke(instance, config.sceneCollectorName);
			}
			// 启动模式
			if(config.mode == LauncherMode.CMD_MODE)
			{
				// 命令行启动
				Method cmd_method = initClass.getMethod(JAVA_ROBOT_CMD_MODE, String.class);
				cmd_method.invoke(instance, config.confPath);
			}else if(config.mode == LauncherMode.GUI_MODE)
			{
				// GUI模式启动
				Method gui_method = initClass.getMethod(JAVA_ROBOT_GUI_MODE);
				gui_method.invoke(instance);
			}else if(config.mode == LauncherMode.SVR_MODE)
			{
				// 服务模式启动
				Method svr_method = initClass.getMethod(JAVA_ROBOT_SVR_MODE, int.class);
				svr_method.invoke(instance, config.bindPort);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
