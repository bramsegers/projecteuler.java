package P551_P600;

import java.util.*;

public class PE563 {

    public static void main(String[] args) {
        //new PE563().solve1();
        new PE563().solve2();
    }

    long[] min=new long[101];
    long[] prm={2,3,5,7,11,13,17,19,23};
    List<Long> len=new ArrayList<>();

    // no memory, slow
    void solve1(){
        search1(1,(long)3e15,0,new int[9]);
        output();
    }
    
    void search1(long p,long lim,int pi,int[] e) {
        if(pi==9){
            int w=ways(p,1,0,e);
            if(w>100) return;
            if(min[w]==0||p<min[w]) min[w]=p;            
            return;
        }
        for(int i=0;p<=lim;i++){
            e[pi]+=i;
            search1(p,lim,pi+1,e);
            p*=prm[pi];
            e[pi]-=i;
        }
    }
    
    int ways(long p, long p1, int pi, int[] e){
        long p2=p/p1;
        if(p1>p2) return 0;
        if(pi==9) return (p2<=1.1D*p1)?1:0;
        int rv=0;
        for(int i=0;i<=e[pi];i++){
            rv+=ways(p,p1,pi+1,e);
            p1*=prm[pi];
        }
        return rv;
    }
    
    // 2gb memory, faster
    void solve2(){       
        search2(1,(long)1e8,0,new int[9]);
        Collections.sort(len);
        Map<Long,Integer> map=new TreeMap<>();
        for(int i=0;i<len.size();i++){
            long p1=len.get(i);
            for(int j=i;j<len.size();j++){
                long p2=len.get(j);
                if(10*p2>11*p1) break;
                Integer n=map.get(p1*p2);
                map.put(p1*p2,n==null?1:n+1);
            }
        }
        for(long n:map.keySet()){
            int m=map.get(n);
            if(m<1 || m>100) continue;
            if(min[m]==0 || min[m]>n) min[m]=n;            
        }
        output();
    }
    
    void search2(long p,long lim,int pi,int[] e) {
        if(pi==9){
            len.add(p);
            return;
        }
        for(int i=0;p<=lim;i++){
            e[pi]+=i;
            search2(p,lim,pi+1,e);
            p*=prm[pi];
            e[pi]-=i;
        }
    } 
    
    void output(){
        long sum=0;
        for(int i=2;i<=100;i++){
            System.out.println(i+" "+min[i]);
            sum+=min[i];
        }
        System.out.println(sum);   
    }

}