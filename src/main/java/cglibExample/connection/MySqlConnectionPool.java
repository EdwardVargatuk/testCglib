package cglibExample.connection;


import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


public class MySqlConnectionPool {


    private static final String DB_CONFIG_FILENAME = "/dbConfig.properties";
    private static final String DB_CONFIG_PARAM_URL = "database.url";
    private static final String DB_CONFIG_PARAM_DB_NAME = "database.dbName";
    private static final String DB_CONFIG_PARAM_USER_NAME = "database.userName";
    private static final String DB_CONFIG_PARAM_USER_PASSWORD = "database.userPassword";
    private static final String DB_CONFIG_PARAM_DRIVER = "database.driver";
    private static final String DB_CONFIG_PARAM_MAX_CONNECTIONS = "database.maxConnections";
    private static final String DB_CONFIG_PARAM_CONNECTION_PROPERTIES = "database.connectionProperties";
    private static final Logger log = LogManager.getLogger(MySqlConnectionPool.class.getName());


    private static BasicDataSource connectionPool;

    static {
        try {
            Properties properties = new Properties();
            InputStream paths = MySqlConnectionPool.class.getResourceAsStream(DB_CONFIG_FILENAME);
            properties.load(paths);
            String dbUrl = properties.getProperty(DB_CONFIG_PARAM_URL) + "/" + properties.getProperty(DB_CONFIG_PARAM_DB_NAME);

            connectionPool = new BasicDataSource();
            connectionPool.setDriverClassName(properties.getProperty(DB_CONFIG_PARAM_DRIVER));
            connectionPool.setUrl(dbUrl);
            connectionPool.setUsername(properties.getProperty(DB_CONFIG_PARAM_USER_NAME));
            connectionPool.setPassword(properties.getProperty(DB_CONFIG_PARAM_USER_PASSWORD));
            connectionPool.setMaxTotal(Integer.parseInt(properties.getProperty(DB_CONFIG_PARAM_MAX_CONNECTIONS)));
            connectionPool.setConnectionProperties(properties.getProperty(DB_CONFIG_PARAM_CONNECTION_PROPERTIES));
            log.log(Level.INFO, "create connection");
        } catch (IOException | RuntimeException e) {
            log.log(Level.ERROR, "exception " + e);
            e.printStackTrace();
        }
    }

    public MySqlConnectionPool() {
    }

    public static Connection getConnection() throws SQLException {
        return connectionPool.getConnection();
    }

}

