package ox.calc;

import it.unimi.dsi.fastutil.longs.LongAVLTreeSet;
import ox.calc.IPrimeGen;
import ox.calc.PrimeGenDic;
import ox.calc.PrimeGenFactory;
import ox.calc.PrimeGenSet;

import java.util.Set;

/**
 * Created by huseyn on 22/04/2016.
 */

public final class PrimesService {
    private static final PrimeGenFactory factory = new PrimeGenFactory();

    static {
        factory.register("set", PrimeGenSet.class);
        factory.register("dict", PrimeGenDic.class);
        factory.register("direct", PrimeGenDirect.class);
        factory.register("directCached", PrimeGenDirectCached.class);
    }

    public static IPrimeGen getGenerator(String generatorName) {
        return factory.getPrimeGenerator(generatorName);
    }

    public static Set<Long> getFirstNPrimes(String generatorName, long count) {
        final IPrimeGen generator = factory.getPrimeGenerator(generatorName);

        Set<Long> primes = new LongAVLTreeSet();
        if (count > 0) {
            primes.add(2l);
        }
        for (long i = 1, n = 1; n < count; i++) {
            long x = (i << 1) + 1;
            if (generator.isPrime(x)) {
                primes.add(x);
                n++;
            }
        }
        return primes;
    }

}

