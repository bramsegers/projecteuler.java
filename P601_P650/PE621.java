package P601_P650;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import util.Util;

public class PE621 {

    public static void main(String[] args) {
        new PE621().solve2(17526000000000L);    
    }
    
    // G(17526000000000)=11429712
    // BUILD SUCCESSFUL (total time: 13,160 minutes 3 seconds)
    void solve1(long m) {
        List<Long> list=new ArrayList<>();
        long count=0,i=0,n=0,a,b,c;
        while(n<=m){
            list.add(n);
            n+=++i;
        }
        Set<Long> set=new HashSet<>(list);       
        for(int j=0;j<i;j++){
            a=list.get(j);
            for(int k=j;k<i;k++){
                b=list.get(k);
                c=m-a-b;
                if(c<b) break;
                if(set.contains(c))
                    count+=(a==b||b==c)?3:6;
            }
        }
        System.out.format("G(%d)=%d%n",m,count);
    }

    //G(17526000000000)=11429712
    //BUILD SUCCESSFUL (total time: 368 minutes 15 seconds)
    void solve2(long m){
        long M=m*8+3,count=0;
        int sq=(int)Math.sqrt(M);
        List<Integer> pr=Util.getPrimes(sq);
        pr.remove(0);
        for(long n=1,n2;(n2=n*n)<=M;n+=2)
            count+=r2(pr,M-n2);
        System.out.format("G(%d)=%d%n",m,count);
    }
    
    long r2(List<Integer> pr, long n){
        if(n<2) return 1;
        while((n&1)==0) n>>=1;
        long B=1;
        for(int p:pr){
            int e=0;
            while(n%p==0){
                n/=p;
                e++;
            }
            if(e>0){
                if((p&3)==1) B*=e+1;
                else if((e&1)==1) return 0;
            }
            if(n==1) return B;
        }
        return (n&3)==1?2*B:0;
    }      
       
}

 