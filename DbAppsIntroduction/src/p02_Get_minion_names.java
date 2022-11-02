package AppsIntroduction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class p02_Get_minion_names {

    final static String GET_MINIONS_NAME_AND_AGE = "select m.name, m.age from minions m" +
            " join minions_villains mv on m.id = mv.minion_id" +
            " where villain_id = ?";
    final static String GET_VILLAIN_NAME_BY_ID = "select v.name from villains v where v.id = ?";
    final static String PRINT_FORMAT_VILLAIN_NAME = "Villain: %s";
    final static String PRINT_FORMAT_MINION_INFO = "%d. %s %d";
    final static String NO_VILLAIN_FORMAT = "No villain with ID %d exists in the database.";

    final static String COLUMN_LABEL_NAME = "name";
    final static String COLUMN_LABEL_AGE = "age";

    public static void main(String[] args) throws SQLException {
        final Connection connection = Utils.getSQLConnection();

        int villainId = new Scanner(System.in).nextInt();

        final PreparedStatement villainStatement = connection.prepareStatement(GET_VILLAIN_NAME_BY_ID);
        villainStatement.setInt(1, villainId);
        final ResultSet villainSet = villainStatement.executeQuery();

        if (!villainSet.next()) {
            System.out.printf(NO_VILLAIN_FORMAT, villainId);
            return;
        }

        final String villainName = villainSet.getString(COLUMN_LABEL_NAME);

        System.out.printf(PRINT_FORMAT_VILLAIN_NAME + "%n", villainName);


        final PreparedStatement minionStatement = connection.prepareStatement(GET_MINIONS_NAME_AND_AGE);
        minionStatement.setInt(1, villainId);
        final ResultSet minionSet = minionStatement.executeQuery();

        for (int i = 1; minionSet.next(); i++) {
            final String minionName = minionSet.getString(COLUMN_LABEL_NAME);
            final int minionAge = minionSet.getInt(COLUMN_LABEL_AGE);
            System.out.printf(PRINT_FORMAT_MINION_INFO + "%n", i, minionName, minionAge);
        }

        connection.close();

    }
}
