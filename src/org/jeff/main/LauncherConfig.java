package org.jeff.main;

/**
 * # 启动器配置
 * @author 覃贵锋
 *
 */
public class LauncherConfig
{
	public static final String L_GUI_TAG = "-gui";
	public static final String L_BIND_PORT = "-port";
	public static final String L_CMD_TAG = "-cmd";
	public static final String L_SCENE_TAG = "-scene";
	public static final String L_DATA_TAG = "-data";
	public static final String L_JAR_TAG = "-jars";
	public static final String L_CLASS_TAG = "-classes";
	/** 启动模式 */
	public LauncherMode mode;
	/** 绑定端口 */
	public int bindPort = 6100;
	/** 使用后台启动需要使用配置启动 */
	public String confPath = null;
	/** 指定外部的场景管理器类 */
	public String sceneClassName = null;
	/** 指定外部的压测数据解析类 */
	public String sceneDataClassName = null;
	/** 指定外部的压测收集类 */
	public String sceneCollectorName = null;
	/** 指定外部的Lib */
	public String jarsPath = null;
	/** 指定外部的classes */
	public String classesPath = null;
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("Launcher [PORT]: %d \r\n", bindPort));
		return sb.toString();
	}
	
	/**
	 * # 解析启动参数
	 * @param args
	 * @throws IndexOutOfBoundsException
	 */
	public void parseParams(String[] args) throws IndexOutOfBoundsException
	{
		int index = 0;
		int size = args.length;
		while(index < size)
		{
			String cmd = args[index++];
			// 开始进行启动参数匹配
			if(cmd.equalsIgnoreCase(L_GUI_TAG))
			{
				this.mode = LauncherMode.GUI_MODE;
				continue;
			}
			// 绑定服务
			else if(cmd.equalsIgnoreCase(L_BIND_PORT))
			{
				if(index >= size)
				{
					throw new RuntimeException(String.format("[服务模式]  %s 需要指定监听端口", L_BIND_PORT));
				}
				this.mode = LauncherMode.SVR_MODE;
				this.bindPort = Integer.parseInt(args[index++]);
				continue;
			}
			// 命令行模式
			else if(cmd.equalsIgnoreCase(L_CMD_TAG))
			{
				if(index >= size)
				{
					throw new RuntimeException(String.format("[命令行模式] %s 需要添加json配置路径", L_CMD_TAG));
				}
				this.mode = LauncherMode.CMD_MODE;
				this.confPath = args[index++];
				continue;
			}
			// 压测场景管理器
			else if(cmd.equalsIgnoreCase(L_SCENE_TAG))
			{
				if(index >= size)
				{
					throw new RuntimeException(String.format("%s 需要指定场景类名", L_SCENE_TAG));
				}
				this.sceneClassName = args[index++];
				continue;
			}
			// 压测配置解析类
			else if(cmd.equalsIgnoreCase(L_DATA_TAG))
			{
				if(index >= size)
				{
					throw new RuntimeException(String.format("%s 需要指定数据解析类名", L_DATA_TAG));
				}
				this.sceneDataClassName = args[index++];
				continue;
			}
			// 额外的jar包路径
			else if(cmd.equalsIgnoreCase(L_JAR_TAG))
			{
				if(index >= size)
				{
					throw new RuntimeException(String.format("%s 需要指定路径", L_JAR_TAG));
				}
				this.jarsPath = args[index++];
				continue;
			}
			// classes
			else if(cmd.equalsIgnoreCase(L_CLASS_TAG))
			{
				if(index >= size)
				{
					throw new RuntimeException(String.format("%s 需要指定路径", L_CLASS_TAG));
				}
				this.classesPath = args[index++];
				continue;
			}
		}
	}
}
