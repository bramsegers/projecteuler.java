package P251_P300;

import util.Primes;
import static util.Util.print;
import static util.Util.gcd;

public class PE283 {

    // FG200718.pdf
    public static void main(String[] args) {
        new PE283().solve(1000); 
    } 
    
    Primes pr;
    long c,m,u,v,q;

    void solve(long n) {
        pr=new Primes(16*n*n);        
        for (m=1; m<=n; m++) {
            for (u=1; u<=2*m; u++) {
                if((2*m)%u!=0) continue;
                for (v=1; v*v<=3*u*u; v++) {
                    if(gcd(u,v)!=1) continue;
                    long a=4*m*m;
                    long b=u*u+v*v;
                    q=a*b;
                    c+=solve(1,0,pr.factorize_2(a,b));       
                }
            }           
            print(m,c);
        }
    }  
    
    long solve(long d1, int i, int[][] f) {
        long rv=0,pe=1,a,b,c,d2=q/d1;
        if(d1*d1>q) return 0;
        if(i==f.length) {            
            if((d1+2*m*u)%v!=0) return 0;
            if((d2+2*m*u)%v!=0) return 0;
            a=((d1+2*m*u)/v)+((2*m*v)/u);
            b=((d2+2*m*u)/v)+((2*m*v)/u);
            c=(d1+d2+4*m*u)/v;
            return (a<=b&&b<=c)?a+b+c:0;            
        }
        for (int j=0; j<=f[i][1]; j++) {
            rv+=solve(d1*pe,i+1,f);
            pe*=f[i][0];
        }
        return rv;
    }

}
