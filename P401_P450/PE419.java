package P401_P450;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.Util;

public class PE419{

    // https://www.youtube.com/watch?v=ea7lJkEhytA&t=319s
    // http://mathworld.wolfram.com/CosmologicalTheorem.html
    public static void main(String[] args){
        new PE419().brute(40);
        new PE419().solve(40,1<<30);
        new PE419().solve((long)1e12,1<<30);
    }

    Map<String,Integer> ind=new HashMap<>();
    int index(String s){
        if(!ind.containsKey(s))
            ind.put(s,ind.size());
        return ind.get(s);
    }

    void solve(long N,long M){
        List<String> str=Util.readText("files/P419.txt");
        int n=str.size(), prev=-1;
        long[][] num=new long[n][3];
        long[][] a=new long[n][n];
        for(String s:str){
            String[] t=s.split("\t");
            int u=index(t[0]);
            if(prev>=0) a[prev][u]++;
            for(String r:t[1].split("\\.")){
                if(r.charAt(0)<='3')
                    for(char ch:r.toCharArray())
                        num[u][ch-'1']++;
                else a[prev][index(r)]++;
            }
            prev=u;
        }
        a[prev][prev]++;
        
        long d=N-8;
        long[] x=new long[n];
        x[index("Hf")]=1;
        x[index("Sn")]=1;
        
        while(d>0){
            if((d%2)>0){
                long[] y=new long[n];
                for(int i=0;i<n;i++)
                    for(int j=0;j<n;j++)
                        y[i]=(y[i]+a[j][i]*x[j])%M;
                x=y;
            }
            if((d/=2)>0){
                long[][] b=new long[n][n];
                for(int i=0;i<n;i++)
                    for(int j=0;j<n;j++)
                        for(int k=0;k<n;k++)
                            b[i][j]=(b[i][j]+a[i][k]*a[k][j])%M;
                a=b;
            }
        }
        
        long[] res=new long[3];
        for(int i=0;i<n;i++)
            for(int j=0;j<3;j++)
                res[j]=(res[j]+num[i][j]*x[i])%M;
        System.out.format("P(%d)=%d,%d,%d %n",N,res[0],res[1],res[2]);
    }
    
    void brute(int n){
        List<Integer> S2,S=new ArrayList<>();
        S.add(1);
        int[] t=null;
        for(int i=2;i<=n;i++){
            S2=new ArrayList<>();
            t=new int[4];
            int occ=1;
            int seen=S.get(0);
            for(int j=1;j<S.size();j++){
                int next=S.get(j);
                if(next==seen)
                    occ++;
                else{
                    S2.add(occ);
                    S2.add(seen);
                    t[occ]++;
                    t[seen]++;
                    occ=1;
                    seen=next;
                }
            }
            S2.add(occ);
            S2.add(seen);
            t[occ]++;
            t[seen]++;
            S=S2;
        }
        String s=S.toString().replaceAll(" ","").replaceAll(",","");
        System.out.format("P(%d)=%d,%d,%d %s %n",n,t[1],t[2],t[3],s);
    }

}
