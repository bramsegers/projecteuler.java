package P301_P350;

public class PE308 {

    // primes: 10001, iterations: 1539669807660924
    // BUILD SUCCESSFUL (total time: 27 minutes 19 seconds)
    public static void main(String[] args){
        new PE308().solve(10001);
    }
    
    void solve(long N){
        int[][] p={{6},{0,1,5},{7},{8},{9},{3,4},{2,7},{3,4},{},{4},{5},{1,2},{},{2,4}};
        int[][] q={{3,5},{2,6},{1,6},{0,7},{1,4},{9},{8},{7},{6},{5},{4},{0},{3},{}};
        long[] n={1,0,0,0,0,0,0,0,0,0};
        long c,it=0,s=1,npr=0;
        while(npr<N){
            for(int i=0;i<14;i++){
                boolean v=true;
                for(int j=0;v&&j<q[i].length;j++) 
                    if(n[q[i][j]]==0) v=false;
                if(!v) continue;
                if(i==0&&n[2]>0&&n[3]>0){
                    c=Math.min(n[2],n[3]);
                    if(n[2]==c) s--;
                    if(n[3]==c) s--;
                    if(n[0]==0) s++;
                    if(n[1]==0) s++;
                    n[2]-=c;
                    n[3]-=c;
                    n[0]+=c;
                    n[1]+=c;
                    it+=2*c;
                } else if(i==3){
                    c=n[0];
                    s--;
                    if(n[2]==0) s++;
                    n[0]=0;
                    n[2]+=c;
                    it+=2*c;
                } else if(i==4){
                    c=n[1];
                    s--;
                    if(n[3]==0) s++;
                    n[1]=0;
                    n[3]+=c;
                    it+=2*c;
                } else if(i==11){
                    c=n[0];
                    s--;
                    if(n[1]==0) s++;
                    if(n[2]==0) s++;
                    n[0]=0;
                    n[1]+=c;
                    n[2]+=c;
                    it+=c;
                } else if(i==12){
                    c=n[3];
                    s--;
                    n[3]=0;
                    it+=c;
                } else{
                    for(int j=0;j<p[i].length;j++) if(++n[p[i][j]]==1) s++;
                    for(int j=0;j<q[i].length;j++) if(--n[q[i][j]]==0) s--;
                    it++;
                }
                //System.out.println(Arrays.toString(n)+" "+i);
                if(s==1) System.out.format("primes: %d, iterations: %d%n",++npr,it);
                break;
            }
        }
    }
 
}
