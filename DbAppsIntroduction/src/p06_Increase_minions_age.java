package AppsIntroduction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class p06_Increase_minions_age {

    private final static String UPDATE_MINIONS_BY_ID = "update minions set age = age + 1, name = lower(name) where id IN(%s)";
    private final static String SELECT_ALL_MINIONS = "select name, age from minions order by id";

    public static void main(String[] args) throws SQLException {
        Connection connection = Utils.getSQLConnection();
        Scanner scanner = new Scanner(System.in);

        List<String> inputIds = Arrays.stream(scanner.nextLine().split("\\s+")).toList();

        updateMinions(inputIds, connection);
        printMinions(connection);

        connection.close();
    }

    private static void updateMinions(List<String> inputIds, Connection connection) throws SQLException {
        String updateTheMinions = String.format(UPDATE_MINIONS_BY_ID,
                inputIds.stream()
                        .map(v -> "?")
                        .collect(Collectors.joining(", ")));

        PreparedStatement updateMinions = connection.prepareStatement(updateTheMinions);

        for(int i = 1; i <= inputIds.size(); i++) {
            updateMinions.setInt(i, Integer.parseInt(inputIds.get(i-1)));
        }
        updateMinions.executeUpdate();
    }
    private static void printMinions(Connection connection) throws SQLException {
        PreparedStatement selectMinions = connection.prepareStatement(SELECT_ALL_MINIONS);
        ResultSet printMinions = selectMinions.executeQuery();

        while (printMinions.next()) {
            String name = printMinions.getString("name");
            int age = printMinions.getInt("age");

            System.out.println(name + " " + age);
        }
    }
}

