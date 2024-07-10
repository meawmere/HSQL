# HSQL
HSQL - Helper SQL. It is needed to simplify working with the SQL database.
An example of using HSQL is below.
```java
public class Test extends EventAdapter {
    public static HSQL hsql;
    public static void main(String[] args) throws SQLException {
        hsql = HSQLBuilder.setURL("jdbc:sqlite:db.txt").addEventListener(new Test()).build();
        ResultSet rs = hsql.get("SELECT id FROM users");
    }

    @Override
    public void READY(READY event) {
        System.out.println("CREATE CONNECTION");
    }

    @Override
    public void UPDATE(@NotNull UPDATE event) {
        System.out.println(event.execute);
    }

    @Override
    public void GET(@NotNull GET event) {
        try {
            while (event.output.next()) {
                System.out.println(event.output.getString("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
```
