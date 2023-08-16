package properties;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerCharacterTest {

    private PlayerCharacter sut;
    @Before
    public void setup(){
        sut = new PlayerCharacter(16, 14, 12, 10, 8, 12);
    }
    @Test
    public void testToString() {

        String expected = "John Doe\nSTR: 16  DEX: 14  CON: 12  INT: 10  WIS: 8  CHA: 12  HP: 5";
        assertEquals(expected, sut.toString());
    }

    @Test
    public void testGetModifier() {
        assertEquals(4, sut.getModifier(18));
        assertEquals(4, sut.getModifier(19));
        assertEquals(0, sut.getModifier(10));
        assertEquals(0, sut.getModifier(11));
        assertEquals(-1, sut.getModifier(9));
        assertEquals(-1, sut.getModifier(8));
        assertEquals(-4, sut.getModifier(2));
        assertEquals(-5, sut.getModifier(1));
    }
}