package P701_P750;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PE701 {

    public static void main(String[] args){
        new PE701().solve();
    }
   
    int N=7;
    Map<Row,Long> M1,M2;
   
    
    void solve(){
        
        Row r0=new Row(new int[N],new int[(N+1)/2],0);              // init
        M1=new HashMap<>();
        M1.put(r0,1L);
       
        for(int i=1;i<=N;i++){                                      // dp by rows
            M2=new HashMap<>();
            for(Row r:M1.keySet())
                rows(r,new int[N],1,0,0);
            System.out.println(i+" "+M1.size());
            M1=M2;
        }
       
        long sum=0, pos=1L<<(N*N);                                  // result
        for(Row r:M1.keySet())
            sum+=r.max*M1.get(r);
        double ans=(double)sum/pos;
        System.out.format("%d/%d = %.8f%n",sum,pos,ans);
    }
    
   
    void rows(Row r,int[] p,int next,int ups,int i){
        
        if(i==N){                                                   // row finished
            int[] a=new int[(N+1)/2];
            for(int j=0;j<N;j++){
                int up=r.pos[j];
                int dn=p[j];
                if(dn!=0){
                    a[dn-1]++;
                    if(((ups>>up)&1)==1){
                        a[dn-1]+=r.area[up-1];
                        ups&=~(1<<up);
                    }
                }
            }
            int max=r.max;
            for(int v:a) max=Math.max(max,v);
            Row r2=new Row(p,a,max);
            long v1=M1.get(r);
            long v2=M2.getOrDefault(r2,0L);
            M2.put(r2,v1+v2);
            return;
        }
        
        rows(r,p,next,ups,i+1);                                 // empty cell
        
        int[] p2=Arrays.copyOf(p,N);                            // occupied cell
        int prev=0,
            last=(i==0)?0:p[i-1],
            up=r.pos[i],
            upseen=(ups>>up)&1;
        for(int j=0;j<i && prev==0;j++)
            if(r.pos[j]==up) prev=p[j];
        
        if(last==0 && up==0)              { p2[i]=next++;                      }
        if(last!=0 && up==0)              { p2[i]=last;                        }
        if(last==0 && up!=0 && upseen==0) { p2[i]=next++; ups|=1<<up;          }   
        if(last==0 && up!=0 && upseen!=0) { p2[i]=prev;   ups|=1<<up;          }
        if(last!=0 && up!=0 && upseen==0) { p2[i]=last;   ups|=1<<up;          }
        if(last!=0 && up!=0 && upseen!=0) { p2[i]=prev;   next=0;
                                            for(int j=0;j<i;j++){
                                                if(p2[j]==last) p2[j]=prev;
                                                if(p2[j]>=next) next=p2[j]+1;} }
        rows(r,p2,next,ups,i+1);
    }
    
      
    class Row{
       
        int[] pos;
        int[] area;
        int max;
       
        Row(int[] p,int[] a,int m){
            pos=p;
            area=a;
            max=m;
        }

        @Override
        public int hashCode() {
            int hash=7;
            hash=71*hash+Arrays.hashCode(pos);
            hash=71*hash+Arrays.hashCode(area);
            hash=71*hash+this.max;
            return hash;
        }

        @Override
        public boolean equals(Object o) {
            if(this==o) return true;
            if(o==null) return false;
            if(getClass()!=o.getClass()) return false;
            Row r=(Row)o;
            return max==r.max
                && Arrays.equals(pos,r.pos)
                && Arrays.equals(area,r.area);
        }        
    }

}