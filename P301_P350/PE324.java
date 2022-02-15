package P301_P350;

import java.math.BigInteger;
import java.util.BitSet;

public class PE324 {
        
    // f(10^10000) mod 100000007 = 96972774
    // BUILD SUCCESSFUL (total time: 13 minutes 16 seconds)
    public static void main(String[] args){
        new PE324().solve();
    }

    int base=10,
         exp=10000,
         mod=100000007;
    
    void solve(){
        
        //1. setup 2^9 x 2^9 transition matrix for n->n+1
        long[][] m=new long[512][512];
        for(int i=0;i<512;i++) rec(0,i,i,0,m);
        
        //2. start exponentiation of matrix to n (=b^e) th power
        m=pow(m,BigInteger.valueOf(base));
        
        //3. after some iterations (10 is enough), reduce matrix size
        //   by removing rows and cols that have no connection to end state 0
        
        //3.1 see which states are connected to 0
        BitSet c=new BitSet();
        connections(m,c,0); 
        
        //3.2 setup new transition matrix with reduced size
        int s=c.cardinality();
        long[][] m2=new long[s][s];
        for(int a=0,i=0;i<512;i++){
            if(!c.get(i))a++;
            else for(int b=0,j=0;j<512;j++){
                if(!c.get(j))b++;
                else m2[i-a][j-b]=m[i][j];
            }
        }
        
        //4. finish exponentiation to n-th power with reduced matrix
        BigInteger b=BigInteger.valueOf(base).pow(exp-1);
        m2=pow(m2,b);
        
        //5. output result
        String out="f(%d^%d) mod %d = %d%n";
        System.out.format(out,base,exp,mod,m2[0][0]);
    }
    
    void rec(int p,int a,int b,int c,long[][] m){
        if(p==9){m[a][c]++;return;}
        if(get(b,p)){rec(p+1,a,b,c,m);return;}
        rec(p+1,a,set(b,p),set(c,p),m);                          // up
        if(p%3<2&&!get(b,p+1)) rec(p+1,a,set(set(b,p),p+1),c,m); // right?
        if(p/3<2&&!get(b,p+3)) rec(p+1,a,set(set(b,p),p+3),c,m); // down?
    }
  
    boolean get(int i,int p){
        return ((i>>p)&1)==1;
    }

    int set(int i,int p){
        return i|(1<<p);
    }
        
    void connections(long[][] m, BitSet c, int n){
        for(int i=0;i<512;i++){
            if(m[i][n]>0&&!c.get(i)){
                c.set(i);
                connections(m,c,i);        
            }
        }
    }
    
    long[][] pow(long[][] m,BigInteger p){
        int s=m.length;
        long[][] I=new long[s][s];
        for(int i=0;i<s;i++) I[i][i]=1;
        for(int i=p.bitLength();i>0;i--){
            System.out.println(i);
            if(p.testBit(0)) I=mult(I,m);
            m=mult(m,m);
            p=p.shiftRight(1);
        }
        return I;
    }
    
    long[][] mult(long[][] m1,long[][] m2) {
        int s=m1.length;
        long[][] m=new long[s][s];
        for (int i=0;i<s;i++){
            for (int j=i;j<s;j++){
                for (int k=0;k<s;k++){
                    m[i][j]+=m1[k][j]*m2[i][k];
                    m[i][j]%=mod;
                }
                // matrices are symmetric, calculate only 1 triangle
                m[j][i]=m[i][j];
            }
        }
        return m;
    }

}
