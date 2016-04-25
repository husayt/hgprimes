package ox.calc;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PrimeGenSetTest {

    static PrimeGenSet generator;

    @BeforeClass
    public static void before() throws Exception {
        generator = new PrimeGenSet();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: isPrime(int x)
     */
    @Test
    public void testIsPrime() throws Exception {

        assertTrue(generator.isPrime(5));
        assertTrue(generator.isPrime(17));
        assertTrue(generator.isPrime(97));
        assertFalse(generator.isPrime(100));
        assertFalse(generator.isPrime(49));
        assertFalse(generator.isPrime(11111));
        assertFalse(generator.isPrime(111111));
       // assertFalse(generator.isPrime(1111111));
      //  assertFalse(generator.isPrime(11111111));
      //  assertFalse(generator.isPrime(111111111));
        //  assertFalse(generator.isPrime(1111111111));
    }


} 
