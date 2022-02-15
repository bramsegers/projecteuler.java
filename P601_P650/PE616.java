package P601_P650;

public class PE616{

    public static void main(String[] args){
        new PE616().solve((long)1e12);
    }

    //
    // any number of the form a^b with a>1 and b>1
    // and not both a and b prime, with the exception of 16=2^4=4^2
    // is creative
    //    
   void solve(long N){
        long sum=0;
        int S=(int)Math.sqrt(N);
        boolean[] p=new boolean[S+1];   // perfect powers
        boolean[] c=new boolean[S+1];   // composites
        for(int n=2;n*n<=S;n++){
            for(int i=n*n;i<=S;i*=n) p[i]=true;
            if(!c[n]) for(int i=n;n*i<=S;i++) c[n*i]=true;
        }
        for(int a=2;a<=S;a++){
            long ab=a;
            if(!p[a]) for(int b=2;ab<=N/a;b++)
                if((ab*=a)!=16 && (c[a]||c[b])) sum+=ab;
        }
        System.out.println(sum);
    }


}
