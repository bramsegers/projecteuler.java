package P701_P750;

import java.math.BigDecimal;
import static util.Util.bd;

public class PE704{

    public static void main(String[] args){
        new PE704().brute1(100);
        new PE704().brute2((long)1e7);
        new PE704().solve((long)1e16);
    }

    void solve(long N){
        long[] arr=new long[100];
        BigDecimal x=bd(N);
        for(int i=0;x.signum()>0;i++){
            arr[i]=1+x.longValue();
            x=x.subtract(bd(1)).divide(bd(2));
        }
        long sum=0;
        for(long n=2,e=1;n<=N;e++){
            long n2=n*2-2;
            int v1=val(n,arr);
            int v2=val(n2,arr);
            long v3=arr[v2];
            v3+=v3&1;
            sum+=((n2+2-v3)/2)*e*v2;
            if(v3-2>=n) sum+=((v3-n)/2)*e*v1;
            n<<=1;
        }
        System.out.println(sum);
    }

    int val(long n,long[] arr){
        for(int i=0;;i++)
            if(arr[i]<=n) return i;
    }


    // https://oeis.org/A119387
    void brute1(long N){
        long sum=0;
        for(long n=1;n<=N;n++){
            long a=n,b=n+1,c=0;
            while(a>0){
                if((a&1)==(b&1)) c++;
                a>>=1;
                b>>=1;
            }
            sum+=c;
            System.out.println(n+" "+c);
        }
        System.out.println(sum);
    }

    void brute2(long N){
        long sum=0;
        for(long n=2,e=1;n<=N;e++){
            long n2=n*2-2;
            System.out.println(e+" "+n+" "+n2);
            for(long m=n;m<=n2;m+=2){
                long q=m;
                if(q>N) break;
                while(true){
                    sum+=e;
                    q=2*q+1;
                    if(q>N) break;
                }
            }
            n<<=1;
        }
        System.out.println(sum);
    }


}
