package P501_P550;

import java.util.HashMap;
import java.util.Map;

public class PE527{

    public static void main(String[] args){
        long n=(long)1e10;
        Map<Long,Double> m=new HashMap<>();    
        System.out.format("%.8f%n",R(n)-B(n,m));
    }
    
    static double R(long n){
        double h=0.5772156649+Math.log(n);
	return 2*h*(n+1)/n-3;
    }
    
    static double B(long n, Map<Long,Double> m){
        if(n<2) return n;
	Double b=m.get(n);
        if(b!=null) return b;
	b=1+(B(n/2,m)*(n/2)+B((n-1)/2,m)*((n-1)/2))/n;
	m.put(n,b);
	return b;
    }

}