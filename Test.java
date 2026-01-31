import database.DatabaseConnection;

import java.sql.Connection;

public class Test {
    static void main() {
        Connection connection = DatabaseConnection.getConnection();
    }
}
