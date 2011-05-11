package jtools.math;

public class FactorizationAlgorithm {

    public static int gcd(int a, int b) {
        // Note the if a == b == 0 we return 0. This is not strictly
        // correct since in this case the GCD is any integer. But we
        // can live with it.
        if (a == 0)
            return b;

        if (b == 0)
            return a;

        if (a == b)
            return a;

        if (a > b)
            return FactorizationAlgorithm.gcd_(a, b);
        else return FactorizationAlgorithm.gcd_(b, a);
    }

    // Here we can assume that m > n > 0
    private static int gcd_(int m, int n) {
        int d;
        while ((d = m%n) != 0) {
            m = n;
            n = d;
        }
        return n;
    }
    
    public static int gcd(int vec[]) {
        if (vec.length == 1)
            return vec[0];
        if (vec.length == 2)
            return FactorizationAlgorithm.gcd(vec[0], vec[1]);
        
        return FactorizationAlgorithm.gcd(vec[0], gcd_(vec, 1));
    }

    private static int gcd_(int vec[], int pos) {
        if (pos == vec.length - 2)
            return FactorizationAlgorithm.gcd(vec[pos], vec[++pos]);
        else
            return FactorizationAlgorithm.gcd(vec[pos], gcd_(vec, ++pos));
    }
    
    public static int lcm(int a, int b) {
        return (a * b) / FactorizationAlgorithm.gcd(a, b);
    }

    public static int lcm(int vec[]) {
        return FactorizationAlgorithm.mul(vec) / FactorizationAlgorithm.gcd(vec);
    }

    public static int mul(int vec[]) {
        if (vec.length == 0)
            return 0;
        long result = vec[0];

        for (int i = 1; i < vec.length; i++)
            result = result * vec[i];

        return (int)result;
    }
}
