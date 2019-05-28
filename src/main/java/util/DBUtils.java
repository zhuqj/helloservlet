package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class DBUtils {
	private static BasicDataSource  ds ;
	static {
		//创建数据源对象
				ds = new BasicDataSource();
				//读取配置文件
				Properties prop = new Properties();
				InputStream is = DBUtils.class.getClassLoader()
						.getResourceAsStream("jdbc.properties");
				try {
					prop.load(is);
					String driver = prop.getProperty("driver");
					String url= prop.getProperty("url");
					String username = prop.getProperty("username");
					String password = prop.getProperty("password");
					//设置配置文件
					ds.setDriverClassName(driver);
					ds.setUrl(url);
					ds.setUsername(username);
					ds.setPassword(password);
					ds.setInitialSize(3);//设置初始连接数量
					ds.setMaxActive(5);//设置最大连接数量
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	}
	public static Connection getConn() throws Exception {
		//获取连接对象 注意导包别导错 异常抛出
		Connection conn = ds.getConnection();
		System.out.println(conn);
		return conn;
	}
}
