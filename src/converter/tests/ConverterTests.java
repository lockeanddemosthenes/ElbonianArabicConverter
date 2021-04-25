package converter.tests;

import converter.ElbonianArabicConverter;
import converter.exceptions.MalformedNumberException;
import converter.exceptions.ValueOutOfBoundsException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the ElbonianArabicConverter class.
 */
public class ConverterTests {

    @Rule
    public ExpectedException e = ExpectedException.none();

    @Test
    public void ElbonianToArabicSampleTest() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("1000");
        assertEquals(converter.toElbonian(), "M");
    }

    @Test
    public void ElbonianToArabicSampleTest2() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("1001");
        assertEquals(converter.toElbonian(), "MI");
    }

    @Test
    public void ElbonianToArabicSampleTest3() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("3005");
        assertEquals(converter.toElbonian(), "MMMV");
    }

    @Test
    public void ElbonianToArabicSampleTest4() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("450");
        assertEquals(converter.toElbonian(), "FL");
    }

    @Test
    public void ArabictoElbonian() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("1");
        assertEquals(converter.toElbonian(), "I");
    }

    @Test
    public void ArabicToElbonianSampleTest0() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("VI");
        assertEquals(converter.toArabic(), 6);
    }

    @Test
    public void MalformedNumException() throws MalformedNumberException, ValueOutOfBoundsException {
        e.expect(MalformedNumberException.class);
        ElbonianArabicConverter converter = new ElbonianArabicConverter("MMMM");
    }

    @Test
    public void ArabicToElbonianSampleTest2() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("MDF");
        assertEquals(converter.toArabic(), 1900);
    }

    @Test
    public void ArabicToElbonianSampleTest5() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("M");
        assertEquals(converter.toArabic(), 1000);
    }
    @Test
    public void ArabicToElbonianSampleTest3() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("I");
        assertEquals(converter.toArabic(), 1);
    }

    @Test
    public void ArabicToElbonianSampleTest4() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("M");
        assertEquals(converter.toArabic(), 1000);
    }

    @Test(expected = MalformedNumberException.class)
    public void malformedNumberTest() throws MalformedNumberException, ValueOutOfBoundsException {
        throw new MalformedNumberException("M");
    }

    @Test(expected = ValueOutOfBoundsException.class)
    public void valueOutOfBoundsTest() throws MalformedNumberException, ValueOutOfBoundsException {
        System.out.println("Value is too Large");
        throw new ValueOutOfBoundsException("0");
    }
}
