package P351_P400;

import util.Matrix;
import util.Util;

public class PE367 {

    public static void main(String[] args){
        new PE367().solve(11); // 48271207 (32 min 28 sec)
    }
    
    int id,N;
    int[] pr=Util.primes(100);
    int[] sign=new int[100];
    long[] occ=new long[100];
    long[][] m=new long[100][100];
    
    void solve(int n){
        N=n;
        perm(new int[N],0,(1<<N)-1);
        double[][] d=new double[id][id];
        double[][] Q=new double[id][1];
        d[0][0]=1;
        for(int j=1;j<id;j++){
            Q[j][0]=-1;
            long sum=0;
            for(int i=0;i<id;i++) sum+=m[j][i];
            for(int i=0;i<id;i++){
                d[j][i]=1d*m[j][i]/sum;
                if(i==j) d[j][i]--;
            }
        }
      
        Matrix A=new Matrix(d);
        Matrix b=new Matrix(Q);
        Matrix x=A.solve(b);
    
        double sum=0;
        long sum2=0;
        for(int i=0;i<id;i++){
            sum+=occ[i]*x.get(i,0);
            sum2+=occ[i];
        }    
        System.out.println((long)((sum/sum2)+0.5));   
    }

    void perm(int[] a,int i,long t){
        if(i==N){
            int s=sign(a);
            occ[s]++;
            if(s==0) return;
            for(int p=0;p<N;p++){
                for(int q=p+1;q<N;q++){
                    for(int r=q+1;r<N;r++){
                        m[s][s]++;
                        m[s][sign(a,p,q,r,p,r,q)]++;
                        m[s][sign(a,p,q,r,q,p,r)]++;
                        m[s][sign(a,p,q,r,q,r,p)]++;
                        m[s][sign(a,p,q,r,r,p,q)]++;
                        m[s][sign(a,p,q,r,r,q,p)]++;
                    }
                }
            }
            return;
        }
        for(int j=0;j<N;j++){
            if(((t>>j)&1)==0) continue;
            a[i]=j;
            perm(a,i+1,t&~(1<<j));
        }
    }

    int sign(int[] a){
        int rv=1;
        int[] b=a.clone();
        for(int i=0;i<N;i++){
            if(b[i]==i) continue;
            int n,c=0,x=b[i];
            while(b[i]!=i){
                n=b[x];
                b[x]=x;
                x=n;
                c++;
            }
            rv*=pr[c-2];
        }
        if(sign[rv]==0) sign[rv]=1+id++; 
        return sign[rv]-1;
    }

    int sign(int[] a,int p,int q,int r,int i,int j,int k){
        int[] b=a.clone();
        b[i]=a[p];
        b[j]=a[q];
        b[k]=a[r];
        return sign(b);
    }
    
}