package P451_P500;

import java.util.HashMap;

public class PE469 {

    public static void main(String[] args) {
        new PE469().solve();
    }
    
    void solve(){
        double E=0,lastE=1;
        for (int n=2;Math.abs(lastE-E)>1e-14;n++) {
            lastE=E;
            E=1-e(1,1,n,new HashMap<>())/n;
            System.out.format("E(%d)=%.14f %n",n,E);
        }
        System.out.format("..%nE(10^18)=%.14f %n",E);
    }
    
    double e(long p, int s, int n, HashMap<String,Double> D) {
        double e=D.getOrDefault(p+","+s,0D);
        if(e>0) return e;
        int i,j;
        int[] a=new int[n];
        for (i=0,j=0;i<n;i++)
            if(valid(i,s,n)) a[j++]=i;        
        for (i=0;i<j;i++)
            e+=e(p*j,setbit(s,a[i]),n,D);
        if(e==0) e=val(s,p);
        D.put(p+" "+s,e);
        return e;
    }

    boolean valid(int i, int state, int n) {
        if(bitset(state,i)) return false;
        if(bitset(state,(i+1)%n)) return false;
        if(bitset(state,(i+n-1)%n)) return false;
        return true;
    }
    
    int setbit(int state, int i) {
        return state|(1<<i);
    }
    
    boolean bitset(int state, int i){
        return ((state>>i)&1)==1;
    }

    double val(int state, long p) {
        return Integer.bitCount(state)/(double)p;
    }

}
