package net.meawmere.HSQL;

import net.meawmere.HSQL.events.IEventsListener;
import net.meawmere.HSQL.events.source.GET;
import net.meawmere.HSQL.events.source.READY;
import net.meawmere.HSQL.events.source.UPDATE;
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

        for (IEventsListener event : listeners) {
            event.UPDATE(new UPDATE(execute));
        }
    }

    public ResultSet get(String execute) throws SQLException {
        Connection connection = DriverManager.getConnection(this.url);
        Statement statement = connection.createStatement();
        ResultSet output = statement.executeQuery(execute);

        for (IEventsListener event : listeners) {
            event.GET(new GET(execute, output));
        }

        return output;
    }
}
