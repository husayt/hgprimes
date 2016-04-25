package ox.calc;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/** 
* primeUtils Tester. 
* 
* @author <Authors name> 
* @since <pre>Apr 22, 2016</pre> 
* @version 1.0 
*/ 
public class PrimeGenDirectTest {

   static PrimeGenDirect generator;

@BeforeClass
public static void before() throws Exception {
    generator = new PrimeGenDirect();
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: isPrime(int x) 
* 
*/ 
@Test
public void testIsPrime() throws Exception {
    assertTrue(generator.isPrime(3));
    assertTrue(generator.isPrime(17));
    assertTrue(generator.isPrime(97));
    assertFalse(generator.isPrime(100));
    assertFalse(generator.isPrime(49));
    assertFalse(generator.isPrime(11111));
    assertFalse(generator.isPrime(111111));
    assertFalse(generator.isPrime(1111111));
    assertFalse(generator.isPrime(11111111));
    assertFalse(generator.isPrime(111111111));
    assertFalse(generator.isPrime(1111111111));
    assertTrue(generator.isPrime(Integer.MAX_VALUE));
    assertFalse(generator.isPrime(Long.MAX_VALUE));
   // assertFalse(generator.isPrime(11111111111l));
   // assertFalse(generator.isPrime(Integer.MAX_VALUE));
} 



} 
