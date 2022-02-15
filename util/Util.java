package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Util {
  
    
    public static BigDecimal sqrt(long n,int sc){
        BigDecimal T=BigDecimal.valueOf(2);
        BigDecimal A=BigDecimal.valueOf(n);
        BigDecimal P=BigDecimal.ZERO;
        BigDecimal S=new BigDecimal(Math.sqrt(n));
        int rm= BigDecimal.ROUND_HALF_UP;
        while(!P.equals(S)){
            P=S;
            S=A.divide(P,sc,rm);
            S=S.add(P);
            S=S.divide(T,sc,rm);
        }
        return S;
    }
    
    public static BigDecimal bd(long n){
        return BigDecimal.valueOf(n);
    }
      
    public static long sumLucy(long n){
        int r=(int)Math.sqrt(n);
        List<Long> V=new ArrayList<>();
        for(int i=1;i<=r;i++) V.add(n/i);
        for(long i=V.get(V.size()-1)-1;i>0;i--) V.add(i);  
        Map<Long,Long> S=new HashMap<>();
        for(long i:V) S.put(i,i*(i+1)/2-1);
        for(long p=2;p<=r;p++){
            if(S.get(p)>S.get(p-1)){
                long q=p*p;
                long s=S.get(p-1);
                for(long v:V){
                    if(v<q) break;
                    S.put(v,S.get(v)-p*(S.get(v/p)-s));
                }
            }
        }
        return S.get(n);
    }
    
    public static BigInteger bi(long p) {
        return BigInteger.valueOf(p);
    }  
    
    public static BigInteger bi(String s) {
        return new BigInteger(s);
    }  
     
    public static BigInteger mul(long... m) {
        BigInteger rv=bi(1);
        for(long n:m){
            rv=rv.multiply(bi(n));
        }
        return rv;
    }
    
    public static void exit(){
        System.exit(0);
    }
    
    public static int[] primes(long n) {
        return primes((int)n);
    } 
    
    public static int[] primes(int n){ 
        BitSet p=new BitSet();
        p.set(2,n+1);
        for(int i=2;i*i<=n;i++)
            if(p.get(i)) for(int j=i;i*j<=n;j++)
                p.clear(i*j);
        int[] pr=new int[p.cardinality()];
        for(int j=0,i=p.nextSetBit(0);i>=0;i=p.nextSetBit(i+1))
            pr[j++]=i;
        return pr;
    }    

    public static boolean eq(int[]... a) {
        for (int i=1;i<a.length;i++) {
            if(!Arrays.equals(a[0],a[i])) return false;
        }
        return true;
    }
    
    public static Frac frac(long p, long q){
        return new Frac(p,q);
    } 
    
    public static void print(Object... o){
        String s=Arrays.deepToString(o);
        s=s.substring(1,s.length()-1);
        s=s.replaceAll(",","");
        System.out.println(s);
    }
    
    private static long timer;
    public static String timer(){
        timer=System.currentTimeMillis()-timer;
        return timer+"ms";
    }
    
    public static int fac(int n) {
        return n<2?1:n*fac(n-1);
    }
    
    public static long digsum(long n) {
        long sum=0;
        while(n>0){
            sum+=n%10;
            n/=10;
        }
        return sum;
    }

    public static int modPowI(int b, int e, int m) {
        b %= m;
        int rv = 1;
        while (e > 0) {
            if ((e & 1) == 1) {
                rv *= b;
                rv %= m;
            }
            b *= b;
            b %= m;
            e >>= 1;
        }
        return rv;
    }

    public static long modPow(long base, long exp, long mod) {
        if (mod == 1) {
            return 0;
        }
        base = base % mod;
        long result = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }
    
    
    //https://en.wikipedia.org/wiki/Lucas%27s_theorem
    //edit fac[] creation if multiple calls
    public static long modChoose(long n,long k,long m){
        int len=(int)(n%m);
        long[] fac=new long[len+1];
        for(int i=0;i<=len;i++) fac[i]=i<2?1:(fac[i-1]*i)%m;
        return modChoose(n,k,m,fac);
    }
    
    public static long modChoose(long n,long k,long m,long[] fac){
        int ni=(int)n,ki=(int)k,nm=(int)(n%m),km=(int)(k%m);
        return (n<m&&k<m)
            ?ni<ki?0:modMul(
                modPow(fac[ki]*fac[ni-ki],m-2,m),
                fac[ni]
                ,m)
            :nm<km?0:modMul(
                modPow(fac[km]*fac[nm-km],m-2,m),
                fac[nm],
                modChoose(n/m,k/m,m,fac),
                m);
    }
    

    public static boolean[] isPrime;
    public static List<Integer> primes;

    public static void initPrimes(int limit) {
        isPrime = new boolean[limit + 1];
        for (int n = 2; n <= limit; n++) {
            isPrime[n] = true;
        }
        for (int n = 2; n * n <= limit; n++) {
            if (isPrime[n]) {
                for (int j = n; n * j <= limit; j++) {
                    isPrime[n * j] = false;
                }
            }
        }
        primes = new ArrayList<>();
        for (int n = 2; n <= limit; n++) {
            if (isPrime[n]) {
                primes.add(n);
            }
        }
    }
    
    public static boolean[] atkinSieve(int N) {
        int[] seq={2,4};
        boolean[] p=new boolean[N];
        int sqrt=(int)Math.sqrt(N)+1;
        int i=0,k1=0,k=0,x=0,y=0,x3=0;
        double xm=Math.sqrt(N/4)+1;
        while(++x<xm){
            i=0;
            k1=4*x*x;
            y=1;            
            if(++x3==3){
                x3=0;
                while((k=k1+y*y)<N){
                    p[k]=!p[k];
                    y+=seq[(++i&1)];
                }                
            }else{ 
                while((k=k1+y*y)<N) {
                    p[k]=!p[k];
                    y+=2;                
                }
            }
        }
        xm=Math.sqrt(N/3)+1;
        x=-1;y=0;
        while((x+=2)<xm){
            i=1;
            k1=3*x*x;
            y=2;
            while((k=k1+y*y)<N){
                p[k]=!p[k];
                y+=seq[(++i&1)];
            }
        }
        xm=(int)Math.sqrt(N);
        x=0;y=0;
        while(++x<xm) {
            k1=3*x*x;
            if((x&1)==0){y=1;i=0;} 
            else {y=2;i=1;}
            while(y<x){
                k=k1-y*y;
                if(k<N) p[k]=!p[k];
                y+=seq[(++i&1)];
            }
        }
        p[2]=true;
        p[3]=true;
        for(int n=5;n<=sqrt;n++) {
            if(!p[n]) continue;
            int n2=n*n;
            for(k=n2;k<N;k+=n2)
                p[k]=false;
        }
        return p;
    }    

    public static List<Integer> getPrimes(int nMax) {
        initPrimes(nMax);
        return primes;
    }

    public static BitSet getPrimesBS(int n) {
        BitSet bs = new BitSet();
        bs.set(2, n + 1);
        for (int i = 2; i * i <= n; i++) {
            if (bs.get(i)) {
                for (int j = i; i * j <= n; j++) {
                    bs.clear(i * j);
                }
            }
        }
        return bs;
    }

    public static BitSet getPrimesBS_len(int len) {
        int n = 1, j = 0;
        BitSet bs;
        while ((bs = getPrimesBS(n)).cardinality() < len) {
            n *= 2;
        }
        for (int i = 0; i < len; i++) {
            j = bs.nextSetBit(j + 1);
        }
        bs.clear(j + 1, n + 1);
        return bs;
    }
    
    public static int[] getPrimes_len(int len) {
        BitSet bs=getPrimesBS_len(len);
        len=bs.cardinality();
        int[] pr=new int[len];
        for(int i=0,p=2;p>0;p=bs.nextSetBit(p+1))
            pr[i++]=p;
        return pr;
    }

    public static void addNextPrime() {
        int p = primes.get(primes.size() - 1);
        while (!isPrime(++p)) {
        }
        primes.add(p);
    }

    public static boolean isPrime(long n) {
        if (n < 2) {
            return false;
        }
        if (n == 2) {
            return true;
        }
        if (n % 2 == 0) {
            return false;
        }
        for (long i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static long nextPrime(long n) {
        while (!isPrime(++n)) {
        }
        return n;
    }

    public static void lucky(int n) {
        BitSet lucky = new BitSet();
        lucky.flip(1, n);
        int step = 1;
        while ((step = lucky.nextSetBit(step + 1)) > 0) {
            int j = 0;
            for (int i = 0; i >= 0; i = lucky.nextSetBit(i + 1)) {
                lucky.set(i, j++ % step != 0);
            }
        }
        System.out.println(lucky.cardinality());
        System.out.println(lucky);
    }

    public static Map<Integer, Integer> factorize(int n) {
        Map<Integer, Integer> map = new TreeMap<>();
        int i = 0;
        while (n > 1) {
            int d = primes.get(i++);
            int e = 0;
            while (n % d == 0) {
                n /= d;
                e++;
            }
            if (e > 0) {
                map.put(d, e);
            }
            if (isPrime[n]) {
                map.put(n, 1);
                n = 1;
            }
        }
        return map;
    }
    
    public static List<Integer> exponents(int n) {
        List<Integer> rv=new ArrayList<>();
        for(int i=0;n>1;i++){
            int e=0,p=primes.get(i);
            while(n%p==0){n/=p;e++;}
            if(e>0) rv.add(e);
            if(isPrime[n]){rv.add(1);n=1;}
        }
        return rv;
    }

    public static TreeMap<Integer, Integer> factorize(long n) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int d = 2;
        while (n > 1) {
            int e = 0;
            while (n % d == 0) {
                n /= d;
                e++;
            }
            if (e > 0) {
                map.put(d, e);
            }
            d++;
        }
        return map;
    }

    public static int totient(int n) {
        double phi = n;
        int i = 0, p = 0;
        while (i < primes.size() && (p = primes.get(i++)) * p <= n) {
            if (n % p == 0) {
                phi -= phi / p;
                while (n % p == 0) {
                    n /= p;
                }
            }
        }
        if (n > 1) {
            phi -= phi / n;
        }
        return (int) phi;
    }

    public static long totient(long n) {
        if(primes==null){
            initPrimes((int)Math.sqrt(n));
        }
        double phi = n;
        int i = 0;
        long p = 0;
        while (i < primes.size() && (p = primes.get(i++)) * p <= n) {
            if (n % p == 0) {
                phi -= phi / p;
                while (n % p == 0) {
                    n /= p;
                }
            }
        }
        if (n > 1) {
            phi -= phi / n;
        }
        return (long) phi;
    }

    public static void chooseD(int n, int k, List<List<Double>> c) {
        chooseD(k, n - 1, 0, c);
        chooseD(k - 1, n - 1, 1, c);
    }

    private static void chooseD(int t, int d, int state, List<List<Double>> c) {
        if (t < 0 || t > d || (d == 0 && t > 0)) {
        } else if (d == 0 && t == 0) {
            c.add(numsFromBitsD(state));
        } else {
            chooseD(t, d - 1, state << 1, c);
            chooseD(t - 1, d - 1, (state << 1) + 1, c);
        }
    }

    private static List<Double> numsFromBitsD(int c) {
        List<Double> rv = new ArrayList<>();
        int i = 1;
        while (c > 0) {
            if ((c & 1) == 1) {
                rv.add((double) i);
            }
            c >>= 1;
            i++;
        }
        return rv;
    }

    public static void choose(int n, int k, List<List<Integer>> c) {
        choose(k, n - 1, 0, c);
        choose(k - 1, n - 1, 1, c);
    }

    private static void choose(int t, int d, int state, List<List<Integer>> c) {
        if (t < 0 || t > d || (d == 0 && t > 0)) {
        } else if (d == 0 && t == 0) {
            c.add(numsFromBits(state));
        } else {
            choose(t, d - 1, state << 1, c);
            choose(t - 1, d - 1, (state << 1) + 1, c);
        }
    }

    private static List<Integer> numsFromBits(int c) {
        List<Integer> rv = new ArrayList<>();
        int i = 1;
        while (c > 0) {
            if ((c & 1) == 1) {
                rv.add(i);
            }
            c >>= 1;
            i++;
        }
        return rv;
    }

    public static void permutations(List<Integer> todo, List<List<Integer>> perm) {
        permutations(new ArrayList<>(), todo, perm);
    }

    private static void permutations(List<Integer> done, List<Integer> todo, List<List<Integer>> perm) {
        if (todo.isEmpty()) {
            perm.add(done);
        } else {
            for (Integer i : todo) {
                List<Integer> done2 = new ArrayList<>(done);
                List<Integer> todo2 = new ArrayList<>(todo);
                done2.add(i);
                todo2.remove(i);
                if (noDupe(done2, perm)) {
                    permutations(done2, todo2, perm);
                }
            }
        }
    }

    private static boolean noDupe(List<Integer> done, List<List<Integer>> perm) {
        for (List<Integer> p : perm) {
            List<Integer> part = p.subList(0, done.size());
            if (part.equals(done)) {
                return false;
            }
        }
        return true;
    }

    public static void partitions(int p, List<List<Integer>> part) {
        for (int c = p; c > 0; c--) {
            List<Integer> list = new ArrayList<>();
            partitions(list, c, p, p, part);
        }
    }

    private static void partitions(List<Integer> pt, int done, int todo, int p, List<List<Integer>> part) {
        pt.add(done);
        todo -= done;
        if (todo == 0) {
            part.add(pt);
        } else {
            for (int c = p; c > 0; c--) {
                if (c <= done && c <= todo) {
                    partitions(new ArrayList<>(pt), c, todo, p, part);
                }
            }
        }
    }

    public static int radical(int num) {
        if (isPrime[num]) {
            return num;
        }
        int rad = 1, i = 0;
        while (num > 1) {
            int p = primes.get(i++);
            if (num % p == 0) {
                rad *= p;
            }
            while (num % p == 0) {
                num /= p;
            }
        }
        return rad;
    }

    public static List<String> readText(String file) {
        List<String> list = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String line;
            while ((line = in.readLine()) != null) {
                list.add(line);
            }
            in.close();
            return list;
        } catch (FileNotFoundException ex) {
            return null;
        } catch (IOException ex) {
            return null;
        }
    }

    public static BigInteger gcd(BigInteger a, BigInteger b) {
        return b.signum() == 0 ? a : gcd(b, a.mod(b));
    }

    public static long lcm(long a, long b) {
        return a * (b / gcd(a, b));
    }
    
    public static long lcm(long... n){
        long lcm=n[0];
        for(int i=1;i<n.length;i++) 
            lcm=lcm(lcm,n[i]);
        return lcm;
    }
    
    public static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static BigInteger sqrt(BigInteger x) {
        return sqrt(x, BigInteger.ZERO.setBit(x.bitLength() / 2));
    }

    public static BigInteger sqrt(BigInteger x, BigInteger start) {
        BigInteger div = start;
        BigInteger div2 = div;
        while (true) {
            BigInteger y = div.add(x.divide(div)).shiftRight(1);
            if (y.equals(div) || y.equals(div2)) {
                return y;
            }
            div2 = div;
            div = y;
        }
    }

    public static long choose(long n, long k) {
        k = Math.min(k, n - k);
        long result = 1L;
        for (long i = 0; i < k; i++) {
            result *= n - i;
            result /= i + 1;
        }
        return result;
    }

    public static long chooseModB(int n, int k, long m) {
        k = Math.min(k, n - k);
        BigInteger c = BigInteger.ONE;
        for (long i = 0; i < k; i++) {
            c = c.multiply(BigInteger.valueOf(n - i));
            c = c.divide(BigInteger.valueOf(i + 1));
        }
        return c.mod(BigInteger.valueOf(m)).longValue();
    }

    public static double nChooseR(int n, int r) {
        r = Math.min(r, n - r);
        double f = 1;
        for (int i = 1; i <= r; i++) {
            f *= n - r + i;
            f /= i;
        }
        return f;
        // Math.round(f) for values [1,2,..,Long.MAX_VALUE]
    }

    public static double zeta(int n) {
        if (n == 2) {
            return Math.PI * Math.PI / 6;
        }
        double z = 1;
        for (int p : primes) {
            z *= 1D / (1 - Math.pow(p, -n));
        }
        return z;
    }
    
    public static long modCh(long n, long k, long p) {
        return choose_mod_one(n, k, p);
    }

    private static long choose_mod_one(long n, long k, long p) {
        if (k < p) {
            return choose_mod_two(n, k, p);
        }
        long q_n, r_n, q_k, r_k, choose;
        q_n = n / p;
        r_n = n % p;
        q_k = k / p;
        r_k = k % p;
        choose = choose_mod_two(r_n, r_k, p);
        choose *= choose_mod_one(q_n, q_k, p);
        return choose % p;
    }

    // Preconditions: 0 <= k <= min(n,p-1); p > 1 prime
    private static long choose_mod_two(long n, long k, long p) {
        n %= p;
        if (n < k) {
            return 0;
        }
        if (k == 0 || k == n) {
            return 1;
        }
        if (k > n / 2) {
            k = n - k;
        }
        long num = n, den = 1;
        for (n = n - 1; k > 1; --n, --k) {
            num = (num * n) % p;
            den = (den * k) % p;
        }
        den = modInv(den, p);
        return (num * den) % p;
    }
    
    /**
     * Computes (a0*a1*a2*...*an) % mod
     * @param a Array [a0, a1, a2, ..., an, mod]
     * @return 
     */
    public static long modMul(long... a){
        long p=1;
        int i=0,m=a.length-1;
        while(i<m) p=(p*a[i++])%a[m];
        return p;
    }    
    
    /**
     * Solves for x:  x^2 ≡ n (mod p)
     * <br><br>
     * https://rosettacode.org/wiki/Tonelli-Shanks_algorithm#Java
     */
    public static long[] tonelliShanks(long n,long p){
        if(modPow(n,(p-1)/2,p)!=1) return null;
        long q=p-1,m=0;
        while((q&1)==0) {m++;q>>=1;} 
        if(m==1){
            long r=modPow(n,(p+1)/4,p);
            return new long[]{r,p-r};
        } 
        long b,e,i,z=2;
        while(modPow(z,(p-1)/2,p)!=p-1) z++;
        long c=modPow(z,q,p);
        long r=modPow(n,(q+1)/2,p);
        long t=modPow(n,q,p);
        for(z=t,i=0;t!=1;m=i,z=t){
            while(z!=1&&i<m-1) {z=(z*z)%p;i++;}
            for(b=c,e=m-i-1;e>0;e--) b=(b*b)%p;
            r=(r*b)%p;
            c=(b*b)%p;
            t=(t*c)%p;
        }
        return new long[]{r,p-r};
    }
    
    /**
     * Computes (1^p + 2^p+ ... + n^p) mod m<br>
     * https://en.wikipedia.org/wiki/Faulhaber%27s_formula<br>
     */
    public static long faulhaber(long n,int p,long m){
        long t,sum=0,ncr=1;
        long[] B=new long[p+1];
        for(int j=0;j<=p;j++){
            B[j]=modInv(j+1,m);
            for(int i=j;i>=1;i--) 
                B[i-1]=modMul(i,B[i-1]+m-B[i],m);
            t=modMul(ncr,j==1?m-B[0]:B[0],m);
            t=modMul(t,modPow(n,p+1-j,m),m);
            sum=(j%2==0)?(sum+t)%m:(sum+m-t)%m;
            t=modMul(p+1-j,modInv(j+1,m),m);
            ncr=modMul(ncr,t,m);
	}
        return modMul(sum,modInv(p+1,m),m);
    }

    public static long modInv(long k, long m) {
        if (m == 0) {
            return (k == 1 || k == -1) ? k : 0;
        }
        if (m < 0) {
            m = -m;
        }
        k %= m;
        if (k < 0) {
            k += m;
        }
        boolean neg = true;
        long p1 = 1, p2 = 0, k1 = k, m1 = m, q, r, temp;
        while (k1 > 0) {
            q = m1 / k1;
            r = m1 % k1;
            temp = q * p1 + p2;
            p2 = p1;
            p1 = temp;
            m1 = k1;
            k1 = r;
            neg = !neg;
        }
        return neg ? m - p2 : p2;
    }

    private static Map<Integer, List<List<Integer>>> permutations;

    public static List<List<Integer>> getPermutations(int n) {
        if (permutations == null) {
            permutations = new HashMap<>();
        }
        List<List<Integer>> rv = permutations.get(n);
        if (rv == null) {
            rv = new ArrayList<>();
            List<Integer> p = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                p.add(i);
            }
            permutations(p, rv);
            permutations.put(n, rv);
        }
        return rv;
    }

    /**
     * Generates all primitive Pythagorean triplets with hypotenuse &le limit<br>
     * = all pairs (a,b,c) satisfying<br>
     * 1. a^2 + b^2 = c^2<br>
     * 2. gcd(a,b,c) = 1<br>
     * 3. c &le limit<br>
     *
     * @param limit max value of hypotenuse c
     * @return set of int[] {a,b,c} with a &lt b &lt c
     */
    public static Set<int[]> pythTriplets(int limit) {
        Set<int[]> set = new HashSet<>();
        int c = 0;
        for (int m = 1; m * m <= limit; m++) {
            for (int n = (m & 1) + 1; n < m && (c = m * m + n * n) <= limit; n += 2) {
                if (gcd(m, n) == 1) {
                    int a = m * m - n * n;
                    int b = 2 * m * n;
                    set.add(new int[]{a < b ? a : b, a < b ? b : a, c});
                }
            }
        }
        return set;
    }

    public static List<Integer> newList(List<Integer> list, int n) {
        List<Integer> rv = new ArrayList<>(list);
        rv.add(n);
        return rv;
    }

    public static BigInteger binomial(final int N, final int K) {
        BigInteger ret = BigInteger.ONE;
        for (int k = 0; k < K; k++) {
            ret = ret.multiply(BigInteger.valueOf(N - k))
                    .divide(BigInteger.valueOf(k + 1));
        }
        return ret;
    }

    public static BigInteger nthRoot(BigInteger x, int n) {
        BigInteger N = BigInteger.valueOf(n);
        BigInteger Ndec = BigInteger.valueOf(n - 1);
        BigInteger a, b = BigInteger.ZERO.setBit(1 + x.bitLength() / n);
        do {
            a = b;
            b = a.multiply(Ndec).add(x.divide(a.pow(n - 1))).divide(N);
        } while (b.compareTo(a) == -1);
        return a;
    }

    public static BigInteger factorial(int n) {
        BigInteger rv = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            rv = rv.multiply(BigInteger.valueOf(i));
        }
        return rv;
    }
    
    public static long[] fibmod(long n,long m){
        if(n==0) return new long[]{1,0};
        long[] ab=fibmod(n/2,m);
        long a=ab[0],b=ab[1],c,d;
        long a2=(a*a)%m;
        long b2=(b*b)%m;
        long ab2=(2L*a*b)%m;
        long apb=(a+b)%m;
        if((n&1)==0) {c=a2+b2; d=ab2+b2;}
        else     {c=ab2+b2;d=b2+apb*apb;}
        return new long[]{c%m,d%m};
    }
    
    public static int[] sieveMoebius(int N){
        boolean[] c=new boolean[N+1];
        boolean[] s=new boolean[N+1];
        int[] m=new int[N+1];
        for(int n=2;n*n<=N;n++) if(!c[n]) for(int j=n;n*j<=N;j++) c[n*j]=true;
        for(int n=2;n*n<=N;n++) if(!c[n]) for(int p=n*n;p<=N;p+=n*n) s[p]=true;
        for(int n=2;n<=N;n++)   if(!c[n]) for(int p=n;p<=N;p+=n) m[p]++;
        for(int n=0;n<=N;n++) m[n]=s[n]?0:(m[n]&1)==0?1:-1;
        return m;
    }

    public static boolean coprime(long x,long y){
        return gcd(x,y)==1;
    }
    
    // fast matrix exponentiation for square matrix m
    public static long[][] matrixPow(long[][] m,long pow,long mod){
        int N=m.length;
        long[][] p=new long[N][N];
        for(int i=0;i<N;i++) p[i][i]=1;
        while(pow>0){
            if((pow&1)==1) p=matrixMult(N,p,m,mod);
            if(pow>1) m=matrixMult(N,m,m,mod);
            pow>>=1;
        }
        return p;
    }    
    
    private static long[][] matrixMult(int N,long[][] m1,long[][] m2,long mod){
        long[][] m=new long[N][N];
        for(int i=0;i<N;i++)
            for(int j=0;j<N;j++)
                for(int k=0;k<N;k++){
                    m[i][j]+=(m1[i][k]*m2[k][j])%mod;
                    //m[i][j]%=mod;
                    if((m[i][j]%=mod)<0) m[i][j]+=mod;
                }
        return m;
    }
    
    public static long[] divisors(long n){
        long[] r=new long[256];
        int i=0;
        for(long d=1;d*d<=n;d++){
            if(n%d==0) r[i++]=d;
            if(n%d==0&&d*d!=n) r[i++]=n/d;
        }
        r=Arrays.copyOfRange(r,0,i);
        Arrays.sort(r);
        return r;        
    }

    public static long modFac(int n,long mod){
        long f=1,i=1;
        while(++i<=n) f=(f*i)%mod;
        return f;
    }

    public static int[] sievePhi(int N){
        boolean[] sieve=new boolean[N+1];
        int[] phi=new int[N+1];
        int i,j;
        for(i=1;i<=N;i++)
            phi[i]=i;
	for(i=2;i<=N;i++)
            if(!sieve[i])
                for(j=i;j<=N;j+=i){
                    sieve[j]=true;
                    phi[j]/=i;
                    phi[j]*=i-1;
                }   
        return phi;
    }

}