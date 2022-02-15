package P451_P500;

import java.util.*;
import util.Util;

public class PE452{

    public static void main(String[] args){
        new PE452().brute(40);
        new PE452().solve(1000000000); //F=345558983 (17 min 33 sec)
    }
    
    long N,M=1234567891;
    int pi;
    int[] pr;
    Map<String,Long> sig;
    
    void solve(int n){
        System.out.println("sieving primes..");
        sig=new HashMap<>();
        pr=Util.primes(n);
        pi=pr.length;
        N=n;
        System.out.println("collecting signatures..");
        signatures(0,1,"");
        System.out.println("calculate result..");        
        Map<List<Integer>,Long> map=new HashMap<>();
        for(String s:sig.keySet()){
            if(s.isEmpty()) continue;
            String[] r=s.split("_");
            List<Integer> list=new ArrayList<>();
            for(String t:r)
                list.add(Integer.parseInt(t));
            Collections.sort(list);
            Collections.reverse(list);
            Long v=map.get(list);
            if(v==null) v=0L;
            map.put(list,v+sig.get(s));
        }        
        long sum=1,c=0,size=map.size();
        for(List<Integer> list:map.keySet()){
            int t=0,len=list.size();
            int[] a=new int[len];
            for(int i=0;i<len;i++)
                t+=a[i]=list.get(i);
            long ways=ways(a,t,len);
            
            sum=(sum+(ways*(map.get(list)%M))%M)%M;
            System.out.println(++c+"/"+size);
        }
        System.out.println("F="+sum);
    }

    void signatures(int i,long p,String s){
        Long v=sig.get(s);
        sig.put(s,v==null?1:v+1);
        for(int j=i;j<pi&&p*pr[j]<=N;j++)
            for(long q=p,e=1;(q*=pr[j])<=N;e++)
                signatures(j+1,q,s+e+"_");
    }
    
    long ways(int[] a,int t,int len){
        List<int[]> list=new ArrayList<>();
        list(0,len-1,a,new int[len],list);
        return ways(1,0,1,t,list.size()-1,len,a,list);
    }
    
    void list(int t,int i,int[] a,int[] b,List<int[]> list){
        if(i<0) {if(t>0)list.add(b.clone());return;}
        for(int j=0;j<=a[i];j++){
            b[i]=j;
            list(t+j,i-1,a,b,list);
        }
    }
    
    long ways(long tp,long tn,long td,int t,int i,int len,int[] a,List<int[]> list){
        if(t==0) return (tp*Util.modInv(td,M))%M;
        long rv=0;
        for(int j=i;j>=0;j--){
            int[] b=list.get(j);
            long td2=td,tp2=tp;
            for(int m=1;;m++){
                tp2=(tp2*(N-tn+1-m))%M;
                td2=(td2*m)%M;
                int t2=t;
                int[] a2=a.clone();
                for(int k=0;k<len;k++){
                    t2-=b[k]*m;
                    a2[k]-=b[k]*m;
                    if(a2[k]<0) m=-1;
                }
                if(m<0) break;
                rv+=ways(tp2,tn+m,td2,t2,j-1,len,a2,list);
            }     
        }
        return rv;
    }
    
    void brute(int N){
        System.out.println("brute force..");
        for(int n=1;n<=N;n++){
            long f=brute(0,1,n,n);
            System.out.println(n+" "+f);
        }        
    }
    
    long brute(int i,long p,int m,int n){
        if(i==n) return 1;
        long rv=0;
        for(int j=1;p*j<=m;j++)
            rv+=brute(i+1,p*j,m,n);
        return rv;
    }

}
