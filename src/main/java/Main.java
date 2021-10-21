import beans.State;

import java.sql.*;
import java.util.Locale;
import java.util.Scanner;

public class Main {


    public static void main(String[] a) throws SQLException {

        // Try with resources after Java 6. Not for Android!!
        /*try (Connection conn = Util.getConnection();
             Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = stm.executeQuery("SELECT * FROM packages");) {

            System.out.println("It's connected!");
            rs.last();
            System.out.println("Number of rows: " + rs.getRow());
        }*/

        Scanner scanner = new Scanner(System.in);
        String stateId = scanner.nextLine().toUpperCase(Locale.ROOT);
        State state = StateManager.getRow(stateId);
        System.out.println(state.getStateName());
/*
        // Null handling
        System.out.println(state.getStateName());*/


        /*State state2 = new State();
        state2.setStateId(scanner.nextLine());
        state2.setStateName(scanner.nextLine());
        StateManager.insert(state2);*/

        /*State state3 = new State();
        state3.setStateId(scanner.nextLine());
        state3.setStateName(scanner.nextLine());
        StateManager.update(state3);*/

/*
        String stateId = scanner.nextLine();
        StateManager.delete(stateId);*/
    }
}
