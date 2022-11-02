package AppsIntroduction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class p04_Change_town_names_casting {

        private final static String UPDATE_TOWN_NAMES = "update towns t set t.name = upper(name) where t.country = ?";
        private final static String GET_TOWN_NAMES_BY_COUNTRY = "select t.name from towns t where t.country = ?";

        private static final String COUNT_OF_AFFECTED_TOWNS = "%d town names were affected.";
        private static final String NO_TOWNS_AFFECTED = "No town names were affected.";

    public static void main(String[] args) throws SQLException {
        Connection connection = Utils.getSQLConnection();

        final String countryName = new Scanner(System.in).nextLine();
        final PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TOWN_NAMES);
        preparedStatement.setString(1, countryName);

        final int updatedTownsCount = preparedStatement.executeUpdate();

        if (updatedTownsCount == 0) {
            System.out.println(NO_TOWNS_AFFECTED);
            return;
        }
        System.out.printf(COUNT_OF_AFFECTED_TOWNS + "%n", updatedTownsCount);

        PreparedStatement statement = connection.prepareStatement(GET_TOWN_NAMES_BY_COUNTRY);
        statement.setString(1, countryName);
        final ResultSet resultSet = statement.executeQuery();
        List<String> towns = new ArrayList<>();

        while (resultSet.next()) {
            towns.add(resultSet.getString("name"));
        }
        System.out.println(towns);
    }
}
