package database;

import org.sqlite.SQLiteConfig;

import java.util.Properties;

public class DatabaseHandler implements Database {

    private String dbUrl;
    private Properties sqLiteConfig;
    private DatabaseSelector dbSelector;
    private DatabaseInserter dbInserter;
    private DatabaseDeleter dbDeleter;

    private DatabaseHandler(String dbUrl) {
        this.dbUrl = dbUrl;
        initSqLiteConfig();
        new DDL(dbUrl, sqLiteConfig).createTablesIfNotExists();
    }

    private void initSqLiteConfig() {
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);
        sqLiteConfig = config.toProperties();
    }

    public static Database getDatabase() {
        return new DatabaseHandler("jdbc:sqlite:simple_database.sqlite");
    }

    public static Database getDatabase(String dbPath) {
        return new DatabaseHandler("jdbc:sqlite:" + dbPath);
    }

    @Override
    public DatabaseSelector getSelector() {
        if (dbSelector == null) {
            dbSelector = new SelectDatabaseContent(dbUrl, sqLiteConfig);
        }
        return dbSelector;
    }

    @Override
    public DatabaseInserter getInserter() {
        if (dbInserter == null) {
            dbInserter = new InsertDatabaseContent(dbUrl, sqLiteConfig);
        }
        return dbInserter;
    }

    @Override
    public DatabaseDeleter getDeleter() {
        if (dbDeleter == null) {
            dbDeleter = new DeleteDatabaseContent(dbUrl, sqLiteConfig);
        }
        return dbDeleter;
    }
}
