package net.meawmere.HSQL.events.source;

import java.sql.ResultSet;

public class GET {
    public String execute;
    public ResultSet output;

    public GET(String execute, ResultSet output) {
        this.execute = execute;
        this.output = output;
    }
}
