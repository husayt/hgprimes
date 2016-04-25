package ox.calc;

import it.unimi.dsi.fastutil.longs.Long2BooleanAVLTreeMap;

import java.util.Map;

/**
 * Cached prime generator using Efficient AVLTreeMap from FastUtil Library
 */
public class PrimeGenDic implements IPrimeGen {

    private final Map<Long, Boolean> cacheDic = new Long2BooleanAVLTreeMap();


    static {

    }

    @Override
    public boolean isPrime(long x) {
        if (this.cacheDic.containsKey(x)) {
            return this.cacheDic.get(x);
        }
        long c = x / 2;
        for (long i = c; i > 1; i--) {
            if (x % i == 0) {
                this.cacheDic.put(x, false);
                return false;
            }
        }
        this.cacheDic.put(x, true);
        return true;
    }
}
