package P751_P800;

import java.util.Arrays;

public class PE755 {

    public static void main(String[] args) {
        new PE755().solve((long)1e2);
        new PE755().solve((long)1e4);
        new PE755().solve((long)1e13);
    }
    
    void solve(long n){
        long[] f=new long[100];
        for(int i=0;;i++){
            f[i]=i<2?i+1:f[i-1]+f[i-2];
            if(f[i]>n){f=Arrays.copyOf(f,i);break;}
        }
        long s=0;for(long e:f) s+=e;
        System.out.println(c(f,n,s,f.length-1));
    }
    
    long c(long[] f,long n,long s,int i){
        if(n<0) return 0;
        if(i<0) return 1;
        if(s<=n) return 1L<<(i+1);
        return c(f,n,s-f[i],i-1)+c(f,n-f[i],s-f[i],i-1);
    }

}
