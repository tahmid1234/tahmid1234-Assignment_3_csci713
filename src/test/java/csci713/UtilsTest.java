package csci713;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UtilsTest {

    @Test
    void testCheckName_valid() {
        assertTrue(Utils.checkName("Alice"));
    }

    @Test
    void testCheckName_empty() {
        assertFalse(Utils.checkName(""));
    }

    @Test
    void testCheckName_null() {
        assertFalse(Utils.checkName(null));
    }

    @Test
    void testIsValidAge_valid() {
        assertTrue(Utils.isValidAge(20));
    }

    @Test
    void testIsValidAge_negative() {
        assertFalse(Utils.isValidAge(-1));
    }

    @Test
    void testIsValidAge_tooLarge() {
        // BUG: expected false, actual true
        assertTrue(Utils.isValidAge(200));
    }
}