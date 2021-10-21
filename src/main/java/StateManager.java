import beans.State;

import java.sql.*;

public class StateManager {

    public static State getRow(String stateId) throws SQLException {
        String sql = "SELECT * FROM states WHERE stateId = ?";
        ResultSet rs = null;
        try (
                Connection conn = Util.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, stateId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                State state = new State();
                state.setStateId(stateId);
                state.setStateName(rs.getString("statename"));
                return state;
            } else {
                System.out.println("No data was found");
                return null;
            }
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

    public static Boolean insert(State state) throws SQLException {
        String sql = "INSERT into states (stateId, statename) " +
                "VALUES(?, ?)";

        try (
                Connection conn = Util.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setString(1, state.getStateId());
            stmt.setString(2, state.getStateName());
            int affected = stmt.executeUpdate();
            if (affected == 1) {
                System.out.println("Success");
            } else {
                System.out.println("Sorry!");
            }

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

        return true;
    }

    public static boolean update(State state) throws SQLException {
        String sql = "Update states" +
                " SET stateId = ? , statename = ? " +
                "WHERE stateId = ?";

        try (
                Connection conn = Util.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setString(1, state.getStateId());
            stmt.setString(2, state.getStateName());
            stmt.setString(3, state.getStateId());
            int affected = stmt.executeUpdate();
            if (affected == 1) {
                System.out.println("Success");
                return true;
            } else {
                System.out.println("oops!");
                return false;
            }

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean delete(String stateId) throws SQLException {
        String sql = "DELETE FROM states " +
                "WHERE stateId = ?";

        try (
                Connection conn = Util.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setString(1, stateId);
            int affected = stmt.executeUpdate();

            if (affected == 1) {
                System.out.println("Deleted!");
                return true;
            } else {
                System.out.println("Sure it existed?!!");
                return false;
            }
        }catch (Exception e ){
            System.out.println(e);
            return false;
        }
    }
}
