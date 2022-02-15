package P401_P450;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class PE426{

    public static void main(String[] args){
        new PE426().solve(10000000);
    }

    // triceps' post in thread
    void solve(int N){
        
        // 1. set initial state
        BitSet b=new BitSet();
        long s=290797;
        for(int t,k=0,i=0;i<=N;i++){
            t=(int)(s%64)+1;
            s=(s*s)%50515093;
            b.set(k,k+=t,(i&1)==0);
        }
        
        // 2. remove occurances of '10'
        List<Integer> young=new ArrayList<>();
        while(b.length()>0){
            BitSet c=new BitSet();
            int i=b.nextSetBit(0),j=0,k=i,m;
            while(i>=0){
                m=b.nextClearBit(i);
                c.set(i-2*j-k,m-2*j-k-1);
                i=b.nextSetBit(m+1);
                j++;
            }
            young.add(j);
            b=c;
        }
        
        // 3. get conjugate of young tableau
        long n=young.size(),sum=0;
        while(n>0){
            sum+=n*n;
            for(int c,i=(int)n-1;i>=0;i--){
                c=young.get(i);
                if(c>1) young.set(i,c-1);
                else {young.remove(i);n--;}
            }
        }
        
        // 4. output
        System.out.println("sum="+sum);
    }
    
}