package P301_P350;

import util.BIT;
import util.Util;

public class PE337 {

    public static void main(String[] args){
        new PE337().solve();
    }

    int N=20000000;
    int M=100000000;
    
        
    void solve(){
        int[] phi=Util.sievePhi(N);
        BIT b=new BIT(N,M);
        int sum=0;
	for(int n=N;n>=6;n--){
            int k=phi[n];
            sum=1+b.sum(n-1)-b.sum(k);
            b.add(k,sum);
	}
	int ans=sum%M;
        if(ans<0) ans+=M;
        System.out.println(ans);
    }

}