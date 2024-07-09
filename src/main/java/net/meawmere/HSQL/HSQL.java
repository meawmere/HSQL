package net.meawmere.HSQL;

import net.meawmere.HSQL.events.IEventsListener;
import net.meawmere.HSQL.events.source.READY;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.List;

public class HSQL {
    protected String url;
    protected List<IEventsListener> listeners;

    public HSQL(String path, @NotNull List<IEventsListener> listeners){
        this.url = path;
        this.listeners = listeners;

        for (IEventsListener event : listeners) {
            event.READY(new READY());
        }
    }

    public void send(String execute) {
        try (Connection connection = DriverManager.getConnection(this.url);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(execute);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet get(String execute) throws SQLException {
        Connection connection = DriverManager.getConnection(this.url);
         Statement statement = connection.createStatement();
        return statement.executeQuery(execute);
    }
}
