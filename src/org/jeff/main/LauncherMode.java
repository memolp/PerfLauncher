package org.jeff.main;

/**
 * 启动模式<br>
 *  1. GUI界面模式， 将启动GUI界面式的交互界面，压测数据将全部展示在界面上<br>
 *  2. 控制台模式，以命令行启动，会在控制台中输出数据<br>
 *  3. 后台服务模式， 用于分布式压测，以服务的方式启动，由连接过来的客户端进行控制。<br>
 * @author 覃贵锋
 *
 */
public enum LauncherMode
{
	GUI_MODE,		// GUI模式
	CMD_MODE,		// 控制台模式
	SVR_MODE		// 服务模式
}
