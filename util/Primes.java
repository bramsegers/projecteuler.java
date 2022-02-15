package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Primes {

    private final long[] words;
    private final long limit, size, max;

    public Primes(long n) {
        validate(n);
        long wlen = (n + 127) >> 7;
        words = new long[(int) wlen];
        int t = (int) (((wlen << 7) - n) >> 1);
        for (int i = 0; i < wlen; i++) {
            words[i] = (i == 0) ? -2 : -1;
            if (i == wlen - 1) {
                words[i] = ((words[i]) << t) >>> t;
            }
        }
        for (long i = 3; i * i <= n; i += 2) {
            int idx1 = (int) (i >> 7);
            int pos1 = (int) (((i & 0b1111111) - 1) >> 1);
            if (((words[idx1] >> pos1) & 1) == 1) {
                for (long j = i; i * j <= n; j += 2) {
                    int idx2 = (int) ((i * j) >> 7);
                    int pos2 = (int) ((((i * j) & 0b1111111) - 1) >> 1);
                    words[idx2] &= ~(1L << pos2);
                }
            }
        }
        limit = n;
        size = setSize();
        max = setMax();
    }

    private void validate(long n) {
        long max = ((long) (Integer.MAX_VALUE - 1)) << 7;
        if (n < 0) {
            throw new IllegalArgumentException("Garanteed to contain " + (-n) + " primes not implemented yet");
        }
        if (n < 2 || n > max) {
            throw new IllegalArgumentException("Input must be in range [2," + max + "]");
        }
    }

    private long setSize() {
        long sum = 1;
        for (long n : words) {
            sum += Long.bitCount(n);
        }
        return sum;
    }

    private long setMax() {
        long p = limit;
        while (!contains(p)) {
            p--;
        }
        return p;
    }

    public long limit() {
        return limit;
    }

    public long size() {
        return size;
    }

    public long max() {
        return max;
    }

    public long next(long p) {
        if (p < 2) {
            return 2;
        } else if (p >= max) {
            return -1;
        } else if (((p & 1) == 0) && contains(++p)) {
            return p;
        }
        int idx = 0;
        int pos = 0;
        while (((words[idx] >> pos) & 1) == 0) {
            p += 2;
            idx = (int) (p >> 7);
            pos = (int) (((p & 0b1111111) - 1) >> 1);
        }
        return p;
    }

    public long prev(long p) {
        if (p > max) {
            return max;
        } else if (p <= 3) {
            return p == 3 ? 2 : -1;
        } else if (((p & 1) == 0) && contains(--p)) {
            return p;
        }
        int idx = 0;
        int pos = 0;
        while (((words[idx] >> pos) & 1) == 0) {
            p -= 2;
            idx = (int) (p >> 7);
            pos = (int) (((p & 0b1111111) - 1) >> 1);
        }
        return p;
    }

    public boolean contains(long p) {
        if (p == 2) {
            return true;
        } else if ((p & 1) == 0 || p < 2 || p > limit) {
            return false;
        }
        int idx = (int) (p >> 7);
        int pos = (int) (((p & 0b1111111) - 1) >> 1);
        return ((words[idx] >> pos) & 1) == 1;
    }

    public int moebius(long n) {
        long npf = 0, p = 2;
        while (n > 1) {
            int e = 0;
            while (n % p == 0) {
                npf++;
                n /= p;
                if (++e == 2) {
                    return 0;
                }
            }
            if (contains(n)) {
                npf++;
                n = 1;
            }
            p = next(p);
            if (p < 0) {
                npf++;
                n = 1;
            }
        }
        return npf % 2 == 0 ? 1 : -1;
    }

    public TreeMap<Long, Integer> factorize(long n) {
        TreeMap<Long, Integer> map = new TreeMap<>();
        long p = 2;
        while (n > 1) {
            int e = 0;
            while (n % p == 0) {
                n /= p;
                e++;
            }
            if (e > 0) {
                map.put(p, e);
            }
            if (contains(n)) {
                map.put(n, 1);
                n = 1;
            }
            p = next(p);
            if (p < 0) {
                if(n==1) return map;
                map.put(n, 1);
                n = 1;
            }
        }
        return map;
    }
    
    public long divscount(long n){
        long d=1,p=2;
        while(n>1){
            int e=0;
            while(n%p==0){
                n/=p;
                e++;
            }
            d*=e+1;
            if(contains(n)) return 2*d;
            if((p=next(p))<0) return (n==1)?d:2*d;
        }
        return d;
    }
    
    public int[][] factorize_(long n){
        TreeMap<Long,Integer> f=factorize(n);
        int[][] rv=new int[f.size()][2];
        int i=0;
        for (long k:f.keySet()) {
            rv[i][0]=(int)k;
            rv[i][1]=f.get(k);
            i++;
        }
        return rv;
    }
    
    // factorizes n*m
    public TreeMap<Long, Integer> factorize2(long n, long m) {
        TreeMap<Long,Integer> f1 = factorize(n);
        TreeMap<Long,Integer> f2 = factorize(m);
        for(long f:f2.keySet()){
            Integer i = f1.get(f);
            i=(i==null)?0:i;
            f1.put(f,i+f2.get(f));
        }     
        return f1;
    }
    
    // factorizes n*m
    public int[][] factorize_2(long n, long m) {
        TreeMap<Long,Integer> f=factorize2(n,m);
        int[][] rv=new int[f.size()][2];
        int i=0;
        for (long k:f.keySet()) {
            rv[i][0]=(int)k;
            rv[i][1]=f.get(k);
            i++;
        }
        return rv;
    }

    public Set<Long> factors(long n) {
        Set<Long> set = new TreeSet<>();
        long p = 0;
        while (n > 1) {
            p = next(p);
            while (n % p == 0) {
                n /= p;
                set.add(p);
            }
            if (contains(n)) {
                set.add(n);
                n = 1;
            }
        }
        return set;
    }
    
    public long[] factorsA(long n){
        Set<Long> f=factors(n);
        long[] a=new long[f.size()];
        int i=0;for(long e:f) a[i++]=e;
        return a;
    }
    
    public List<Integer> exp(long n) {
        return exponents(n);
    }
    
    public List<Integer> exponents(long n) {
        List<Integer> list= new ArrayList<>(8);
        int e;
        long p=2;
        while(true){
            for(e=0;n%p==0;e++) n/=p;
            if(e>0) list.add(e);
            if(contains(n)) {list.add(1);return list;}
            if(n==1) return list;
            if((p=next(p))<0) return list;
        }
    }

    public long highestFactor(long n) {
        if(n<2) return 0;
        long p = 2;
        while (p > 0) {
            if (contains(n)) {
                return n;
            }
            while (n % p == 0) {
                n /= p;
                if (contains(n)) {
                    return n;
                }
            }
            p = next(p);
        }
        return n;
    }
    
    
    public boolean isPrime(long n) {
        if(contains(n)) return true;
        long p=0;
        while ((p=next(p))>0){
            if(n%p==0) return false;
        }
        return true;
    }

    public TreeMap<Long, Integer> factorizeFac(int n) {
        TreeMap<Long, Integer> rv = new TreeMap<>();
        long p = 0;
        while ((p = next(p)) <= n && p > 0) {
            long f = factors(p, n);
            rv.put(p, (int) f);
        }
        return rv;
    }

    public long factors(long p, long fac) {
        return fac == 0 ? 0 : (fac / p + factors(p, fac / p));
    }

    public long totient(long n) {
        double phi = n;
        long p = 0;
        while ((p = next(p)) * p <= n && p>0) {
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
        return (long) (phi+0.5);
    }
    
    public Set<Long> divs(long n){
        TreeMap<Long,Integer> s=factorize(n);
        TreeSet<Long> set=new TreeSet<>();
        searchDivs(1,s.firstKey(),s,set);
        return set;
    }
    
    public Set<Long> divs(long n,int first,int last){
        TreeMap<Long,Integer> s=factorize(n);
        TreeSet<Long> set=new TreeSet<>();
        searchDivs(1,s.firstKey(),s,set);
        if(first==0) set.pollFirst();
        if(last==0) set.pollLast();
        return set;
    }

    private void searchDivs(long p,Long k,TreeMap<Long,Integer> s,Set<Long> set){
        set.add(p);
        if(k==null) return;
        for(int e=0;e<=s.get(k);e++){
            searchDivs(p,s.higherKey(k),s,set);
            p*=k;
        }
    }   

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("2");
        for (long i = 0; i < words.length; i++) {
            for (int p = 0; p < (1 << 6); p++) {
                if (((words[(int) i] >> p) & 1) == 1) {
                    long prime = (i << 7) + (p << 1) + 1;
                    sb.append(",").append(prime);
                }
            }
        }
        sb.append("\nπ(");
        sb.append(limit);
        sb.append(")=");
        sb.append(size);
        return sb.toString();
    }

    private long p;

    public long next(){
        p = next(p);
        return p;
    }

    public long cur(){
        return p;
    }
    
    public void reset(){
        p=0;
    }

    public long pi(long x){
        if(x<2) return 0;
        long sum=1;
        int len=(int)(x/128);
        for(int i=0;i<len;i++)
            sum+=Long.bitCount(words[i]);
        long q=0;
        
        if(len<words.length){
            q=words[len];
            //    catch(Exception e){
            //        System.out.println(e);
            //        System.out.println("len="+len);
            //        System.out.println("wlen="+words.length);
            //        System.out.println("x="+x);
            //        System.exit(0);
            //    }
            x%=128;
            for(long i=1;i<=x;i+=2){
                sum+=q&1;
                q>>=1;
            }
        }
        return sum;
    }

}
