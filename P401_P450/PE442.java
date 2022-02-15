package P401_P450; 

import java.util.*;
    
public class PE442 {
    

    public static void main(String[] args) {
        new PE442().solve((long)1e18);
    }
    
    char[] dig;
    long[][][] mem;
    List<String> pre, eleven;
    
    void solve(long n) {
        eleven=new ArrayList<>();
        for (long j=1,i=0;i<18;i++) {
            eleven.add(""+(j*=11));
        }       
        System.out.format("P(%d)=%d%n",n,count1(n));
    }
    
    // n-th eleven-free number, bisection
    long count1(long n){                 
        long lo=n,hi=2*n,m,c;
        while(lo+1<hi){
            m=(lo+hi)/2;
            c=count2(m);
            if(c<n) lo=m;
            else hi=m; 
        }        
        return hi;     
    }    
    
    // #eleven-free numbers <= n
    long count2(long n){
        mem = new long[200][(""+n).length()][2];
        pre = new ArrayList<>();        
        dig = (""+n).toCharArray();
        return count2("",0,1)-1;
    }    
    
    // #eleven-free numbers <=n with prefix p at index i
    // j=1 if such number can reach n, 0 otherwise
    long count2(String p, int i, int j){        
        if(i==dig.length) return 1;        
        while(!lead(p)) p=p.substring(1);
        int k=pre.size();
        if(!pre.contains(p)) pre.add(p);
        else k=pre.indexOf(p);        
        if(mem[k][i][j]==0) {
            for (char d='0';d<='9';d++) {
                if(j==1&&d>dig[i]) break;
                if(!free(p+d)) continue; 
                mem[k][i][j]+=count2(p+d,i+1,(j==1&&d==dig[i])?1:0);
            }            
        }
        return mem[k][i][j];
    }
    
    // checks if prefix p can lead to an eleven number
    boolean lead(String p){
        for (String e:eleven)
            if(e.startsWith(p)) return true;
        return false;
    } 

    // checks if prefix p is eleven-free
    boolean free(String p) {
        for (String e:eleven)
            if(p.endsWith(e)) return false;
         return true;
    }   

}