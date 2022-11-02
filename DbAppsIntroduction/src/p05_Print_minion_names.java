package AppsIntroduction;

import jdk.jshell.spi.SPIResolutionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayDeque;

public class p05_Print_minion_names {

    private final static String GET_NAMES_ORDERED_BY_ID = "select name from minions order by id";

    public static void main(String[] args) throws SQLException {
        Connection connection = Utils.getSQLConnection();

        PreparedStatement getNames = connection.prepareStatement(GET_NAMES_ORDERED_BY_ID);
        ResultSet resultSet = getNames.executeQuery();

        ArrayDeque<String> minionNames = new ArrayDeque<>();

        while (resultSet.next()){
            minionNames.offer(resultSet.getString("name"));
        }

        while (minionNames.size() > 0) {
            System.out.println(minionNames.pollFirst());
            if (!minionNames.isEmpty()) {
                System.out.println(minionNames.pollLast());
            }
        }

        connection.close();
    }
}
