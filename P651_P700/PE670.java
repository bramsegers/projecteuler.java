package P651_P700;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import static util.Util.matrixPow;

public class PE670 {

    public static void main(String[] args){
        new PE670().solve1(2);
        new PE670().solve2(5);
        new PE670().solve2(100);
        new PE670().solve3((long)1e16);
    }
    
    long mod=1000004321;
    long[][][][][] mem;
    
    // DP solution
    void solve1(int n){
        mem=new long[n+1][4][4][4][4];
        long s=count(n,0,0,0,0);
        System.out.format("S(%d)=%d%n",n,s); 
    }
    
    long count(int n,int c1,int c2,int s1,int s2){
        if(n==0) return 1;
        if(mem[n][c1][c2][s1][s2]>0)
            return mem[n][c1][c2][s1][s2];
        long rv=0;
        for(int c=0;c<16;c++){
            int a=c>>2;
            int b=c&3;
            int s3=(a==c1)?s1+1:1;
            int s4=(b==c2)?s2+1:1;
            if(s3>3||s4>3) continue;
            if(a==c2&&s1>0) continue;
            if(b==c1&&s2>0) continue;
            if(a!=b&&a!=c1&&a!=c2 && b!=c1&&b!=c2 && c1!=c2) continue;
            rv+=count(n-1,a,b,s3,s4);
            rv%=mod;
        }
        mem[n][c1][c2][s1][s2]=rv;
        return rv;
    }
    
    // Matrix 256x256 solution 
    void solve2(long n){
        long[][] m=new long[256][256];
        for(int i=0;i<4096;i++){
            int c1=i&3;
            int c2=(i>>2)&3;
            int s1=(i>>4)&3;
            int s2=(i>>6)&3;
            int c3=(i>>8)&3; 
            int c4=(i>>10)&3; 
            int s3=(c3==c1)?s1+1:1;
            int s4=(c4==c2)?s2+1:1;
            if(s3>3||s4>3) continue;
            if(c3==c2&&s1>0) continue;
            if(c4==c1&&s2>0) continue;
            if(c3!=c4&&c3!=c1&&c3!=c2 && c4!=c1&&c4!=c2 && c1!=c2) continue;
            m[i%256][(s4<<6)+(s3<<4)+(c4<<2)+c3]++;
        }
        m=matrixPow(m,n,mod);
        long s=0;
        for(int i=0;i<256;i++) s=(s+m[0][i])%mod;
        System.out.format("S(%d)=%d%n",n,s); 
    }  
    
    // Matrix 144x144 solution    
    void solve3(long n){
        Set<Integer> set=new HashSet<>();
        long[][] m=new long[144][144];//[900][900];
        int col=4;
        for(int c1=0;c1<col;c1++){
            for(int c2=0;c2<col;c2++){
                for(int c3=0;c3<col;c3++){
                    for(int c4=0;c4<col;c4++){
                        for(int s1=1;s1<4;s1++){
                            for(int s2=1;s2<4;s2++){
                                int s3=(c3==c1)?s1+1:1;
                                int s4=(c4==c2)?s2+1:1;
                                if(s3>3||s4>3) continue;
                                if(c3==c2) continue;
                                if(c4==c1) continue;
                                if(c3!=c4&&c3!=c1&&c3!=c2 && c4!=c1&&c4!=c2 && c1!=c2) continue;
                                int key1=key(c1,c2,s1,s2);
                                if(s1==1&&s2==1) set.add(key1);
                                int key2=key(c3,c4,s3,s4);
                                m[key1][key2]++;
                            }
                        }
                    }
                }
            }
        }
        m=matrixPow(m,n-1,mod);
        long s=0;
        for(int i:set)
            for(int j=0;j<144;j++) s=(s+m[i][j])%mod;        
        System.out.format("S(%d)=%d%n",n,s); 
    }
    
    Map<String,Integer> map=new HashMap<>();
    int key(int c1,int c2,int s1,int s2){
        String k=c1+"-"+c2+"-"+s1+"-"+s2;
        if(!map.containsKey(k)) map.put(k,map.size());
        return map.get(k);
    }
    
    
    
}
