package P751_P800;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static util.Util.bi;

public class PE751{

    public static void main(String[] args){
        new PE751().solve();
    }

    void solve(){
        String s;
        BigInteger a,b,c,d,e,f;
        List<BigInteger> q=new ArrayList<>(Arrays.asList(new BigInteger[]{bi(2),bi(2),bi(2)}));
        while(true){
            s=q.stream().map(String::valueOf).collect(Collectors.joining(""));
            int n=s.length();
            if(n>24) break;
            a=bi(s);
            b=a.add(bi(1));
            c=bi(10).pow(n-1);
            for(int i=q.size();i-->0;){
                d=a.divide(c);
                e=(a.mod(c)).add(c);
                f=(b.mod(c)).add(c);
                a=d.multiply(e);
                b=d.multiply(f);
            }
            q.add(a.divide(c));
        }
        System.out.println(s.charAt(0)+"."+s.substring(1,25));
    }
}