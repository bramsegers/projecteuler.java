package P201_P250; 

import static util.Util.choose;

public class PE242 {

    public static void main(String[] args) {         
        //new PE().brute();
        new PE242().solve((long)1e12);
    } 
        
    //https://oeis.org/A006046    
    void brute() {
        for (int N=1;N<90;N++) {
            long count=0;
            for (int n=1;n<=N;n+=2) {
                for (int k=1;k<=n;k+=2) {
                    long sum=0,p,q;
                    for (int e=0;e<=k;e+=2) {
                        int o=k-e;
                        if(e>(n/2)) continue;
                        if(o>(n/2)+1) continue;
                        p = choose(n/2,e);
                        q = choose((n/2+1),o);
                        sum+=p*q;
                    }
                    if(sum%2==1) count++;
                }
            }
            System.out.println(N+" "+count);
        }
    }
    
    void solve(long n){        
        long q = (n-1)/4+1;
        System.out.println(a(q));
    }

    long a(long n) {
        if(n<2) return n;
        if(n%2==0) return 3*a(n/2);
        return 2*a(n/2)+a(n/2+1);
    }

}