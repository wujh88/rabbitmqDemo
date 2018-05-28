package com.sinotech.util.log;

import org.apache.log4j.Category;
import org.apache.log4j.Priority;
import org.apache.log4j.spi.LoggingEvent;

/**
 * 解决log4j输出信息中单引号与双引号的混乱问题
 * @author Administrator
 *
 */
public class MyLoggingEvent extends LoggingEvent{

	private static final long serialVersionUID = -3499094864944744184L;

    public MyLoggingEvent(String fqnOfCategoryClass, Category logger, Priority level, Object message, Throwable throwable) {
        super(fqnOfCategoryClass, logger, level, message, throwable);
    }

    public String getThreadName() {
        String thrdName = super.getThreadName();
        if (thrdName.indexOf("'") != -1) {
            thrdName = thrdName.replaceAll("'", "''");
        }
        return thrdName;
    }

    public String getRenderedMessage() {
        String msg = super.getRenderedMessage();
        if (msg.indexOf("'") != -1) {
            msg = msg.replaceAll("'", "''");
        }
        return msg;
    }
}
