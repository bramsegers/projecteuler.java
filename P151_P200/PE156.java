package P151_P200;

public class PE156 {
    /*
    
    1. find formula for f(n,d) in closed form
    
    2. sum(d,a,b) = sum of solutions in range [a,b]
    
        Divide and conquer the solution range in two halves:    
            If for one half
            f of the lower bound is greater than the upper bound 
            or 
            f of the upper bound is less than the lower bound 
            then
            the two functions (n and f) do not intersect in this range
            (because f is always constant or increasing)

    */
    
    public void solve() {        
        long sum=0;
        for (int d=1; d<10; d++) {
            long s = sum(d, 0, (long)1e18);
            System.out.println(d+" "+s);
            sum+=s;
        }
        System.out.println(sum);
    }
        
    private long sum(long d, long a, long b) {
        long fa = f(a,d);
        long fb = f(b,d);
        if(a==b) return a==fa? a :0;        
        if(fa>b) return 0;
        if(fb<a) return 0;
        long m = (a+b)/2;
        return sum(d,a,m) + sum(d,m+1,b);        
    }
    
    private long f(long n, long d) {            
        long p=0,q,r,t=0,pt=(long)1e18;
        for(int i=18; i>0; i--){             
            q= n/pt;
            r= i*(pt/10);
            t+= q*r;
            t+= q*p*pt;            
            n-= q*pt;  
            if(d <q) t+= pt;
            if(d==q) p++;
            pt/= 10;
        } 
        t+= p*(n+1);
        if(d<=n) t++;
        return t;
    }
    
    public static void main(String[] args) {
        new PE156().solve();
    }

}
