package ox.calc;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Set;

import static junit.framework.TestCase.assertTrue;

/**
 * primeUtils Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Apr 22, 2016</pre>
 */
@Slf4j
public class PrimesServiceTest {



    @BeforeClass
    public static void before() throws Exception {


    }

    @After
    public void after() throws Exception {
    }


    @Test
    public void testGetGenerator() throws Exception {

    }

    @Test
    public void testGetFirstNPrimes() throws Exception {
        final Set<Long> set1 = PrimesService.getFirstNPrimes("set", 100);
        final Set<Long> set2 = PrimesService.getFirstNPrimes("dict", 100);
        final Set<Long> set3 = PrimesService.getFirstNPrimes("direct", 100);
        log.info("primes {}",set1);
        log.info("primes {}",set2);
        log.info("primes {}",set3);
        assertTrue(set1.containsAll(set2)&&set1.size()==set2.size() );
        assertTrue(set1.containsAll(set3)&&set1.size()==set3.size() );

    }
}
