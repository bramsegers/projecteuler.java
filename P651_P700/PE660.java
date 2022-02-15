package P651_P700;

public class PE660{

    //https://en.wikipedia.org/wiki/Eisenstein_triple
    public static void main(String[] args){
        new PE660().solve();
    }
    
    void solve(){
        int sum=0;
        int N=18*18*18*18*18*18;
        for(int m=2;m*m<=N;m++){
            for(int n=1;n<m;n++){
                int a=m*m+m*n+n*n;
                int b=2*m*n+n*n;
                int c=m*m-n*n;
                if(a>N) break;
                if((m-n)%3==0) continue;
                if(gcd(m,n)>1) continue;
                for(int k=1;k*a<=N;k++){
                    for(int base=9;base<=18;base++)
                        if(pandigital(base,k*a,k*b,k*c)){
                            String out="(%d,%d,%d)_%d%n";
                            System.out.format(out,k*a,k*b,k*c,base);
                            sum+=k*a;
                        }
                }
            }
        }
        System.out.println(sum);
    }

    boolean pandigital(int base,int... abc){
        int p=0;
        for(int n:abc)
            while(n>0){
                int d=n%base;
                if(((p>>d)&1)>0) return false;
                p|=1<<d;
                n/=base;
            }
        return p==(1<<base)-1;
    }
    
    int gcd(int a,int b){
        return b==0?a:gcd(b,a%b);
    }

}