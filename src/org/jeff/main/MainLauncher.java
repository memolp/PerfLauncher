package org.jeff.main;

/**
 * 服务性能压测工具启动器<br>
 *  -gui 启动将显示压测工具界面工具<br>
 *  -port xx 绑定端口，用于作为服务由远程客户端控制<br>
 *  -cmd xxx.json 控制台启动，需要传入压测的配置文件<br>
 * @author 覃贵锋
 *
 */
public class MainLauncher
{
	//private static final String JAVA_CLASS_PATH = "java.class.path";
	//private static final String JAVA_JAR_PATH = "libs";
	/** 启动加载时，设置classpath，方便动态加载jars */
	/*
	static
	{
		List<URL> jars = new ArrayList<URL>();
		// 获取当前程序的工作路径的绝对路径
		File initJar = new 	File("./");
		String InitialJarPath = initJar.getAbsolutePath();
		System.out.printf("[DEBUG] Startup Work Path:: %s \n", InitialJarPath);
		// 加载lib中的全部jar包。
		File[] jar_files = LauncherClassLoader.GetJarsFromPath(InitialJarPath + "/" + JAVA_JAR_PATH);
		if(jar_files != null)
		{
			// 将默认的lib中的jar包设置到加载器中
			StringBuilder str_path = new StringBuilder();
			try
			{
				for (File jar_file : jar_files)
				{
					URL url = jar_file.toURI().toURL();
					jars.add(url);
					str_path.append(File.pathSeparator);
					str_path.append(url.getPath());
					System.out.printf("[DEBUG] Add jar:%s \n", url.getPath());
				}
			}catch (Exception e)
			{
				e.printStackTrace();
			}
			String initialClasspath = System.getProperty(JAVA_CLASS_PATH);
			System.setProperty(JAVA_CLASS_PATH, initialClasspath + str_path.toString());
			// 创建加载器
			LauncherClassLoader.classLoader = LauncherClassLoader.CreateLoader(jars.toArray(new URL[jars.size()]));
		}else
		{
			// 创建加载器
			LauncherClassLoader.classLoader = new LauncherClassLoader(new URL[] {}, MainLauncher.class.getClassLoader());
		}
		
	}*/
	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args)
	{
		// 读取启动配置
		LauncherConfig config = new LauncherConfig();
		config.parseParams(args);
		// 启动引擎
		StartLaunchEngine controller = new StartLaunchEngine();
		controller.InitWithLaunch(config);
	}

}
