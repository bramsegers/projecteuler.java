package P351_P400;

import java.util.HashMap;
import java.util.HashSet; 
import util.Matrix;

public class PE380 {
    
    // http://capone.mtsu.edu/dwalsh/MAZETLK2.pdf
    // http://mathworld.wolfram.com/SpanningTree.html
    public static void main(String[] args){
        new PE380().solve_dense(9,12);
        new PE380().solve_sparse(9,12);
        new PE380().solve_sparse(100,500);
    }
   
    void solve_sparse(int n, int m){
        
        // setup sparse matrix
        HashMap<Integer,Double>[] map=new HashMap[n*m-1];
        for(int i=0;i<m*n-1;i++) map[i]=new HashMap<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(n*i+j==n*m-1) break;
                int c=4;
                if(i==0||i==m-1) c--;
                if(j==0||j==n-1) c--;
                map[n*i+j].put(n*i+j,(double)c);
                if(i>0) map[n*i+j].put(n*(i-1)+j,-1D);
                if(j>0) map[n*i+j].put(n*i+(j-1),-1D);
                if(i<m-1 && n*(i+1)+j<n*m-1) map[n*i+j].put(n*(i+1)+j,-1D);
                if(j<n-1 && n*i+(j+1)<n*m-1) map[n*i+j].put(n*i+(j+1),-1D);
            }
        }
        
        // gaussian elimination
        for(int i=0;i<m*n-2;i++){
            double q=map[i].get(i);
            for(int j=i+1;j<m*n-1;j++){
                Double p=map[j].get(i);
                if(p==null) continue;
                double r=-p/q;                
                HashMap<Integer,Double> map2=new HashMap<>();
                HashSet<Integer> set=new HashSet<>(map[i].keySet());
                set.addAll(map[j].keySet());
                for(int k:set){
                    Double d1=map[i].get(k);
                    Double d2=map[j].get(k);
                    if(d1==null) d1=0D;
                    if(d2==null) d2=0D;
                    double d3=r*d1+d2;
                    if(Math.abs(d3)>1e-16) map2.put(k,d3);
                }
                map[j]=map2;
            }
        }
        
        // determinant
        long e=0;
        double det=1;
        for(int qq=0;qq<m*n-1;qq++){       
            det*=map[qq].get(qq);
            while(det>10){e++;det/=10;}
        }
        
        // output
        System.out.format("C(%d,%d)=%.4fe%d%n",n,m,det,e);
    }
    
    void solve_dense(int n, int m){
        double[][] mat=new double[n*m-1][n*m-1];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(n*i+j==n*m-1) break;
                int c=4;
                if(i==0||i==m-1) c--;
                if(j==0||j==n-1) c--;
                mat[n*i+j][n*i+j]=c;
                if(i>0) mat[n*i+j][n*(i-1)+j]--;
                if(j>0) mat[n*i+j][n*i+(j-1)]--;
                if(i<m-1 && n*(i+1)+j<n*m-1) mat[n*i+j][n*(i+1)+j]--;
                if(j<n-1 && n*i+(j+1)<n*m-1) mat[n*i+j][n*i+(j+1)]--;
            }
        }       
        Matrix M = new Matrix(mat);
        System.out.println(M.det());
    }

}