package AppsIntroduction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class p01_Get_villains_names {

    private final static String GET_VILLAINS_BY_MINIONS_COUNT = "select v.name, COUNT(distinct mv.minion_id) minions_count from villains v" +
            " join minions_villains mv on v.id = mv.villain_id" +
            " group by villain_id" +
            " having minions_count > ?" +
            " order by minions_count DESC;";

    private final static String COLUMN_LABEL_NAME = "name";
    private final static String COLUMN_LABEL_MINIONS_COUNT = "minions_count";
    private final static String PRINT_FORMAT = "%s %d";

    public static void main(String[] args) throws SQLException {
        final Connection connection = Utils.getSQLConnection();

        final PreparedStatement statement = connection.prepareStatement(GET_VILLAINS_BY_MINIONS_COUNT);
        statement.setInt(1, 15);
        final ResultSet villainsSet = statement.executeQuery();

        while (villainsSet.next()) {
            final String villainName = villainsSet.getString(COLUMN_LABEL_NAME);
            final int minionsCount = villainsSet.getInt(COLUMN_LABEL_MINIONS_COUNT);

            System.out.printf(PRINT_FORMAT + "%n", villainName, minionsCount);
        }

        connection.close();
    }
}
