package P651_P700; 

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static util.Util.bi;

public class PE698 {

    public static void main(String[] args){
        new PE698().solve();
    }
    
    long N=111111111111222333L;
    List<Long> ott=new ArrayList<>();
        
    void solve(){
        search(0,0,0,0);
        Collections.sort(ott);        
        search("",0,1,0);
    }
    
    void search(String pre,int plen,int len,long sum){
        long c=count(pre,len);
        System.out.println("*"+pre+"* "+len+" "+c+" "+(sum+c));        
        if(sum+c==N){
            if(plen<len){
                System.out.println("almost found");
                search(pre+'1',plen+1,len,sum);
            } else{
                System.out.println("found");
                System.out.println(bi(pre).mod(bi(123123123)));
            }
        } else if(sum+c>N){
            System.out.println("too high");
            search(pre+'1',plen+1,len,sum);
        } else{
            System.out.println("too low");
            if(plen==0)
                search(pre,plen,len+1,sum+c);
            else{
                char ch=pre.charAt(plen-1);
                pre=pre.substring(0,plen-1);
                search(pre+(++ch),plen,len,sum+c);
            }
        }
    }    
    
    long count(String pre,long len){
        int[] abc={0,0,0};
        for(char ch:pre.toCharArray()){
            abc[ch-'1']++;
            len--;
        }
        long count=0;
        for(int i=0;i<ott.size();i++){
            long a=ott.get(i)-abc[0];
            if(a<0) continue;
            if(a>len) break;
            for(int j=0;j<ott.size();j++){
                long b=ott.get(j)-abc[1];
                if(b<0) continue;
                long c=len-a-b;
                if(c<0) break;
                if(ott.contains(c+abc[2]))
                    count+=fac(bi(len))
                        .divide(fac(bi(a)))
                        .divide(fac(bi(b)))
                        .divide(fac(bi(c)))
                        .longValue();
            }
        }
        return count;
    }
    
    BigInteger fac(BigInteger n){
        return n.signum()==0
                ?bi(1)
                :n.multiply(fac(n.subtract(bi(1))));
    }
    
    void search(long m,long a,long b,long c){
        if(m>1e14) return;
        if(valid(a,b,c)) ott.add(m);
        search(10*m+1,a+1,b,c);
        search(10*m+2,a,b+1,c);
        search(10*m+3,a,b,c+1);
    }

    boolean valid(long... abc){
        for(long a:abc){
            while(a>0){
                long m=a%10;
                if(m==0) return false;
                if(m>3) return false;
                a/=10;
            }
        }
        return true;
    }

}
