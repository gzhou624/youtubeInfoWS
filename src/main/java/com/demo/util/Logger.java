package com.demo.util;

import org.slf4j.LoggerFactory;

public class Logger {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(Logger.class);

    private Logger() {
    }

    public static void info(String message) {
        logger.info("[" + Thread.currentThread().getStackTrace()[2] + "]  " + message);

    }

    public static void info(String... message) {
        if (AppUtil.isNull(message)) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[" + Thread.currentThread().getStackTrace()[2] + "]");
        for (String mess : message) {
            sb.append(" ");
            sb.append(mess);
        }
        logger.info(sb.toString());
    }

    public static void error(String message) {
        logger.error("[" + Thread.currentThread().getStackTrace()[2] + "]  " + message);
    }

    public static void error(String... message) {
        if (AppUtil.isNull(message)) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[" + Thread.currentThread().getStackTrace()[2] + "]");
        for (String mess : message) {
            sb.append(" ");
            sb.append(mess);
        }
        logger.error(sb.toString());
    }

    public static void error(String message, Throwable t) {
        String mes = "[" + Thread.currentThread().getStackTrace()[2] + "]  " + message;
        logger.error(mes, t);
    }

    public static void error(Throwable t) {
        String mes = "[" + Thread.currentThread().getStackTrace()[2] + "]  ";
        logger.error(mes, t);
    }
}
