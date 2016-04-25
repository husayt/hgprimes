package ox.calc;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

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
        int count=1000000;
        Stopwatch sw = Stopwatch.createStarted();

        final Set<Long> set4 = PrimesService.getFirstNPrimes("directCached", count);
        log.info("directCached method done in {}ms",sw.elapsed(TimeUnit.MILLISECONDS));
        sw.reset(); sw.start();
        final Set<Long> set3 = PrimesService.getFirstNPrimes("direct", count);
        log.info("direct method done in {}ms",sw.elapsed(TimeUnit.MILLISECONDS));
        sw.reset();  sw.start();
        final Set<Long> set1 = PrimesService.getFirstNPrimes("set", count);
        log.info("set method done in {}ms",sw.elapsed(TimeUnit.MILLISECONDS));
        sw.reset();sw.start();
        final Set<Long> set5 = PrimesService.getFirstNPrimes("set", count);
        log.info("set2 method done in {}ms",sw.elapsed(TimeUnit.MILLISECONDS));
        sw.reset();sw.start();
        final Set<Long> set2 = PrimesService.getFirstNPrimes("dict", count);
        log.info("dict method done in {}ms",sw.elapsed(TimeUnit.MILLISECONDS));

        sw.stop();
        //log.info("primes {}",set1);
        //log.info("primes {}",set4);
        assertTrue(set1.containsAll(set2)&&set1.size()==set2.size() );
        assertTrue(set1.containsAll(set3)&&set1.size()==set3.size() );
       assertTrue(set1.containsAll(set4)&&set1.size()==set4.size() );

    }
}
