package AppsIntroduction;

import java.sql.*;
import java.util.Scanner;

public class p07_Increase_age_stored_procedure {

    private static int minionId;

    private final static String PRINT_NO_MINION_WITH_THIS_ID = "No minion with ID %d exists in the database.";
    private final static String SELECT_MINIONS_BY_ID = "SELECT `name`, age FROM minions WHERE id = ?";
    private final static String CALL_PROCEDURE = "{CALL usp_get_older(?)}";
    private final static String ENTER_ID = "Enter minion id: ";

    public static void main(String[] args) throws SQLException {
        Connection connection = Utils.getSQLConnection();

        minionId = getMinionId();
        ResultSet minionInfo = getMinionInfo(connection);

        if(minionInfo.next()) {
            updateMinionAge(connection);

            minionInfo = getMinionInfo(connection);

            printMinionInfo(minionInfo);

        } else {
            System.out.printf(PRINT_NO_MINION_WITH_THIS_ID + "%n", minionId);
        }

        connection.close();
    }
    private static void printMinionInfo(ResultSet rs) throws SQLException {
        rs.next();

        String name = rs.getString("name");
        int age = rs.getInt("age");

        System.out.println( name + " " + age);
    }

    private static ResultSet getMinionInfo(Connection connection) throws SQLException {
        PreparedStatement selectMinion = connection.prepareStatement(SELECT_MINIONS_BY_ID);

        selectMinion.setInt(1, minionId);

        return selectMinion.executeQuery();
    }

    private static void updateMinionAge(Connection connection) throws SQLException {
        CallableStatement updateMinionAge = connection.prepareCall(CALL_PROCEDURE);

        updateMinionAge.setInt(1, minionId);

        updateMinionAge.executeUpdate();
    }

    public static int getMinionId() {
        Scanner console = new Scanner(System.in);

        System.out.print(ENTER_ID);

        return Integer.parseInt(console.nextLine());
    }
}