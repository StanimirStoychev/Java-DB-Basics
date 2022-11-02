package AppsIntroduction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class p03_Add_minion {

    final static String GET_TOWN_BY_NAME = "select t.id from towns t where t.name = ?";
    final static String INSERT_INTO_TOWNS = "insert into towns(name) values(?)";
    final static String TOWN_FORMAT = "Town %s was added to the database.";

    final static String GET_VILLAIN_BY_NAME = "select v.id from villains v where v.name = ?";
    final static String INSERT_INTO_VILLAINS = "insert into villains(name, evilness_factor) values(?, ?)";
    final static String VILLAIN_FORMAT = "Villain %s was added to the database.";
    final static String EVILNESS_FACTOR = "evil";

    final static String INSERT_INTO_MINIONS = "insert into minions(name, age, town_id) values(?, ?, ?)";
    final static String GET_LAST_ADDED_MINION = "select m.id from minions m order by m.id desc limit 1";
    final static String INSERT_INTO_MINIONS_VILLAINS = "insert into minions_villains(minion_id, villain_id) values(?, ?)";
    final static String MINION_FORMAT = "Successfully added %s to be minion of %s.";
    final static String COLUMN_LABEL_ID = "id";

    public static void main(String[] args) throws SQLException {
        Connection connection = Utils.getSQLConnection();
        Scanner scanner = new Scanner(System.in);

        //input
        final String[] minionInfo = scanner.nextLine().split(" ");
        final String minionName = minionInfo[1];
        final int minionAge = Integer.parseInt(minionInfo[2]);
        final String minionTown = minionInfo[3];

        final String villainName = scanner.nextLine().split(" ")[1];

        final int townId = getId(connection,
                List.of(minionTown),
                        GET_TOWN_BY_NAME,
                        INSERT_INTO_TOWNS,
                        TOWN_FORMAT);

        final int villainId = getId(connection,
                List.of(villainName, EVILNESS_FACTOR),
                GET_VILLAIN_BY_NAME,
                INSERT_INTO_VILLAINS,
                VILLAIN_FORMAT);

        final PreparedStatement minionStatement = connection.prepareStatement(INSERT_INTO_MINIONS);
        minionStatement.setString(1, minionName);
        minionStatement.setInt(2, minionAge);
        minionStatement.setInt(3, townId);
        minionStatement.executeUpdate();

        final PreparedStatement lastMinion = connection.prepareStatement(GET_LAST_ADDED_MINION);
        final ResultSet resultSet = lastMinion.executeQuery();
        resultSet.next();
        final int lastMinionId = resultSet.getInt(COLUMN_LABEL_ID);

        final PreparedStatement insertIntoMV = connection.prepareStatement(INSERT_INTO_MINIONS_VILLAINS);
        insertIntoMV.setInt(1, lastMinionId);
        insertIntoMV.setInt(2, villainId);

        System.out.printf(MINION_FORMAT + "%n", minionName, villainName);

        connection.close();
    }

    private static int getId(Connection connection,
                                 List<String> arguments,
                                 String selectQuery,
                                 String insertQuery,
                                 String printFormat) throws SQLException {
        final String name = arguments.get(0);
        final PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
        selectStatement.setString(1, name);

        final ResultSet resultSet = selectStatement.executeQuery();

        if (!resultSet.next()) {
            final PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            for (int index = 1; index <= arguments.size(); index++) {
                insertStatement.setString(index, arguments.get(index - 1));
            }
            insertStatement.executeUpdate();

            final ResultSet resultSet1 = selectStatement.executeQuery();
            resultSet1.next();

            System.out.printf(printFormat + "%n", name);

            return resultSet1.getInt(COLUMN_LABEL_ID);
        }
        ResultSet newSet = selectStatement.executeQuery();
        newSet.next();

        return newSet.getInt(COLUMN_LABEL_ID);
    }
}
