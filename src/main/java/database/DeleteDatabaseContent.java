package database;

import java.util.Properties;

public class DeleteDatabaseContent implements DatabaseDeleter{

    private String dbUrl;
    private Properties sqLiteConfig;

    DeleteDatabaseContent(String dbUrl, Properties sqLiteConfig) {
        this.dbUrl = dbUrl;
        this.sqLiteConfig = sqLiteConfig;
    }
}
