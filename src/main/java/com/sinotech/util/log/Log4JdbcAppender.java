package com.sinotech.util.log;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Category;
import org.apache.log4j.Priority;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.jdbc.JDBCAppender;
import org.apache.log4j.spi.LoggingEvent;

/**
 * 重写部分方法，解决log4j入库的相关问题
 * @author haakon
 *
 */
public class Log4JdbcAppender extends JDBCAppender{

	/**
	 * 解决log输出信息中单引号与双引号的混乱问题，保证入库sql语句的正常执行</br>
	 * by haakon 17-04-08
	 */
    @Override
    protected String getLogStatement(LoggingEvent event) {
        String fqnOfCategoryClass=event.fqnOfCategoryClass;
        Category logger=event.getLogger();
        Priority level=event.getLevel();
        Object message=event.getMessage();
        Throwable throwable=null;
        MyLoggingEvent bEvent=new MyLoggingEvent(fqnOfCategoryClass,logger,level,message,throwable);
        return super.getLogStatement(bEvent);
    }
    
    /**
     * 检测数据库连接，防止断开导致日志无法入库</br>
     * 网上有建议修改mysql配置文件中的参数wait_timeout，但该参数过大也不利于数据库性能</br>
     * by haakon 17-05-17</br>
     */
	@Override
	protected Connection getConnection(){
		try {
			// 判断连接是否存在，是否失效,如果失效,则重新获取连接
			if (connection == null || connection.isClosed()) {
				if (!DriverManager.getDrivers().hasMoreElements()) {
					setDriver("sun.jdbc.odbc.JdbcOdbcDriver");
				}
				LogLog.warn(connection == null?"···log4j与mysql尚未创建连接，需要创建":"···log4j与mysql的连接中断，重新创建连接" + new Date());
				connection = DriverManager.getConnection(databaseURL, databaseUser,
						databasePassword);
				LogLog.debug("已新建了log4j与mysql的连接" + new Date());
			}
		} catch (SQLException e) {
			LogLog.error(connection == null?"log4j与mysql尚未创建连接，需要创建":"log4j与mysql的连接中断，重新创建连接" + new Date());
		}

		return connection;
	}
}
