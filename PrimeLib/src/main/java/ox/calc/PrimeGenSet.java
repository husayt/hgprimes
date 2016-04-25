package ox.calc;

import ox.calc.IPrimeGen;

import java.util.BitSet;

public class PrimeGenSet implements IPrimeGen {
    private int primeCount = 0;
    private static final int MaxSize = 111111112;//Integer.MAX_VALUE/2;

    // cache[x]==false means x is prime
    private BitSet cache = new BitSet(MaxSize);


    {
        buildCache(MaxSize);
    }

    private void buildCache(int len) {
        for (int i = 3; i < len; i += 2) {
            for (int j = i + i; j < len; j += i) {
                if (j % 2 != 0) {
                    cache.set((j - 1) / 2, true);
                }
            }
        }
    }

    public boolean isPrime(long l) {
        if (l < 0 || l >= MaxSize) {
            throw new IndexOutOfBoundsException("Index is out ");
        } else if (l < 2) {
            return false;
        } else if (l < 4) {
            return true;
        } else if (l % 2 == 0 || l % 3 == 0) {
            return false;
        } else {
            int x = (int) l;
            return !cache.get((x - 1) / 2);
        }
    }


}


