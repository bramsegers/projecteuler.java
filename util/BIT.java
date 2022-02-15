package util;

public class BIT{
    
    int N;
    int M;
    int[] a;
    
    public BIT(int n,int m){
        N=n;
        M=m;
        a=new int[n];
    }

    public int sum(int x){
        int s=0;
        for(;x>0;x-=x&-x)
            s=(s+a[x])%M;
        return s;
    }

    public void add(int x,int d){
        for(;x<N;x+=x&-x) 
            a[x]=(a[x]+d)%M;
    }
        
}