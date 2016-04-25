package ox.calc;

public class PrimeGenDirect implements IPrimeGen {


    public boolean isPrime(long l) {
        if (l < 2) {
            return false;
        } else if (l < 4) {
            return true;
        } else if (l % 2 == 0 || l % 3 == 0) {
            return false;
        } else {
            long r = (long) Math.sqrt(l);
            long f = 5;
            while (f <= r) {
                if (l % f == 0) return false;
                if (l % (f + 2) == 0) return false;
                f += 6;
            }
            return true;
        }
    }


}
