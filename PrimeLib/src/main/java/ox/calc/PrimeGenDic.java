package ox.calc;

import it.unimi.dsi.fastutil.longs.Long2BooleanAVLTreeMap;
import ox.calc.IPrimeGen;

import java.util.Map;

/**
 * Created by huseyn on 22/04/2016.
 */
public class PrimeGenDic implements IPrimeGen {

    private final Map<Long, Boolean> cacheDic = new Long2BooleanAVLTreeMap();


    static {

    }

    public boolean isPrime(long x) {
        if (cacheDic.containsKey(x)) {
            return cacheDic.get(x);
        }
        long c = x / 2;
        for (long i = c; i > 1; i--) {
            if (x % i == 0) {
                cacheDic.put(x, false);
                return false;
            }
        }
        cacheDic.put(x, true);
        return true;
    }
}
