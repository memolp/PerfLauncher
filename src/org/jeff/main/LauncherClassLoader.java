package org.jeff.main;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.*;

/**
 * 动态类加载对象，用于加载外部的jar和class
 * @author 覃贵锋
 */
public class LauncherClassLoader extends URLClassLoader
{
    /**
     * 单例对象
     */
    public static LauncherClassLoader classLoader;
    /**
     * jar的后缀
     */
    public static final String JAR_EXT_NAME = ".jar";
    /**
     * 缓存URLS
     */
    private List<URL> _cacheURL = null;

    /**
     * 动态加载
     *
     * @param urls
     */
    public LauncherClassLoader(URL[] urls)
    {
        super(urls);
        classLoader = this;
        _cacheURL = Arrays.asList(urls);
    }

    /**
     * 设置classpath，添加导出class的文件的根路径。
     *
     * @param class_path
     */
    public void SetClassPath(String class_path)
    {
        // 先判断传入的路径是否合法
        File file_cls = new File(class_path);
        if (!file_cls.isDirectory()) {
            return;
        }
        // 添加class路径
        try {
            URL url = file_cls.toURI().toURL();
            if (_cacheURL.equals(url)) //去重
            {
                return;
            }
            _cacheURL.add(url);
            this.addURL(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置jar包的路径
     *
     * @param jar_path
     */
    public void SetJarsPath(String jar_path)
    {
        try
        {
            File[] jar_files = this.GetJarsFromPath(jar_path);
            // 加入之前先过滤已有的
            for (File jar : jar_files) {
                URL url = jar.toURI().toURL();
                if (_cacheURL.equals(url)) // 去重
                {
                    continue;
                }
                _cacheURL.add(url);
                this.addURL(url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取jar_path的jars列表
     *
     * @param jar_path
     * @return
     */
    public static File[] GetJarsFromPath(String jar_path)
    {
        // 先判断传入的路径是否合法
        File file_jar = new File(jar_path);
        if (!file_jar.isDirectory()) {
            return null;
        }
        // 遍历路径找到全部的jar
        return file_jar.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(JAR_EXT_NAME);
            }
        });
    }

    /**
     * 创建动态加载器
     *
     * @param urls
     * @return
     */
    public static LauncherClassLoader CreateLoader(URL[] urls)
    {
        PrivilegedAction<LauncherClassLoader> action = new PrivilegedAction<LauncherClassLoader>()
        {
            @Override
            public LauncherClassLoader run() {
                return new LauncherClassLoader(urls);
            }
        };
        return AccessController.doPrivileged(action);
    }

    /**
     * 加载Class
     * @param class_name
     * @return
     */
    public static Class<?> LoadClass(String class_name) throws ClassNotFoundException
    {
        return classLoader.loadClass(class_name);
    }
}
