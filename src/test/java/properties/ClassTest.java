package properties;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClassTest {

    private Class sut;
    @Before
    public void setup(){

    }
    @Test
    public void testToString() {
        sut = new Class(16, 14, 12, 10, 8, 12);
        String expected = "STR: 16  DEX: 14  CON: 12  INT: 10  WIS: 8  CHA: 12  HP: 0";
        assertEquals(expected, sut.toString());
    }
}