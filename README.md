# HSQL
SQLHelper SQL. It is needed to simplify working with the SQL database.
```java
public class Test extends EventAdapter {
    public static HSQL hsql;
    public static void main(String[] args) throws SQLException {
        hsql = HSQLBuilder.setURL("jdbc:sqlite:db.db").addEventListener(new Test()).build();
        hsql.send("CREATE TABLE IF NOT EXISTS users(id TEXT)");
        hsql.send("INSERT INTO users VALUES ()");
        ResultSet rs = hsql.get("SELECT id FROM users");

        while (rs.next()) {
            System.out.println(rs.getString("id"));
        }
    }

    @Override
    public void READY(READY event) {
        System.out.println("CREATE CONNECTION");
    }
}
```
