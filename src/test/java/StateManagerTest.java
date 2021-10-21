import static org.junit.jupiter.api.Assertions.*;

import beans.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

@ExtendWith(MockitoExtension.class)
class StateManagerTest {


    @Test
    @DisplayName("get a state by name _ AZ _ Arizona")
    void getRow1() throws SQLException {
        assertEquals("Arizona", StateManager.getRow("AZ").getStateName());
    }

    @Test
    @DisplayName("Empty input return null _ empty string _ null ")
    void getRow2() throws SQLException {
        assertNull(StateManager.getRow(""));
    }

    @Test
    @DisplayName("state ID does not exist _ ON _ null")
    void getRow3() throws SQLException {
        assertNull(StateManager.getRow("ON"));
    }

    // Mocking a database response using Mockito
    @Test
    @DisplayName("(Mocked) get a state by name _ ON _ Ontario")
    void getRow4() throws SQLException {
        State state = new State();
        state.setStateId("ON");
        state.setStateName("Ontario");

        try (MockedStatic<StateManager> stateManager = Mockito.mockStatic(StateManager.class)) {
            stateManager.when(() -> StateManager.getRow("ON")).thenReturn(state);

            assertEquals("Ontario", StateManager.getRow("ON").getStateName());
        }
    }


    @Test
    @DisplayName("insert a state _ state object _ true")
    void insert1() throws SQLException {
        State ontario = new State();
        ontario.setStateId("ON");
        ontario.setStateName("Ontario");

        assertTrue(StateManager.insert(ontario));
    }

    @Test
    @DisplayName("insert null returns false _ null _ false")
    void insert2() throws SQLException {

        assertFalse(StateManager.insert(null));
    }

    @Test
    @DisplayName("insert empty object returns false _ empty state _ false")
    void insert3() throws SQLException {
        State ontario = new State();

        assertFalse(StateManager.insert(ontario));
    }

    @Test
    @DisplayName("insert a state without state ID _ state object _ false")
    void insert4() throws SQLException {
        State ontario = new State();
        ontario.setStateName("Ontario");

        assertFalse(StateManager.insert(ontario));
    }

    @Test
    @DisplayName("insert a state without state name _ state object _ false")
    void insert5() throws SQLException {
        State ontario = new State();
        ontario.setStateId("ON");

        assertFalse(StateManager.insert(ontario));
    }

    @Test
    @DisplayName("Update Arizona to Arizon _ state object _ true")
    void update1() throws SQLException {
        State arizona = new State();
        arizona.setStateId("AZ");
        arizona.setStateName("Arizon");

        assertTrue(StateManager.update(arizona));
    }

    @Test
    @DisplayName("Update Arizon to Arizona returns the correct state name _ state object _ Arizona")
    void update2() throws SQLException {
        State arizona = new State();
        arizona.setStateId("AZ");
        arizona.setStateName("Arizona");

        StateManager.update(arizona);

        assertEquals("Arizona", Objects.requireNonNull(StateManager.getRow("AZ")).getStateName());
    }


    @Test
    @DisplayName("Deleting Arizona returns true _ AZ _ true")
    void delete1() throws SQLException {

        assertTrue(StateManager.delete("AZ"));

        State arizona = new State();
        arizona.setStateId("AZ");
        arizona.setStateName("Arizona");
        StateManager.insert(arizona);
    }

    @Test
    @DisplayName("Deleting without input _ null _ false")
    void delete2() throws SQLException {

        assertFalse(StateManager.delete(""));
    }

    @Test
    @DisplayName("Deleting non-existing state returns false _ BC _ false")
    void delete3() throws SQLException {

        assertFalse(StateManager.delete("BC"));
    }
}