import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UtilTest {

    @Mock
    Connection connection;

    @Test
    @DisplayName("Calling the method does not return null _ no argument _ not null")
    void getConnection() throws SQLException {
        assertNotNull(Util.getConnection());
    }
}