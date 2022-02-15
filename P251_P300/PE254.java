package P251_P300;

import static util.Util.*;

public class PE254 {

    public static void main(String[] args) {
        new PE254().solve();
    }
    
    int[][] arr1=new int[100][11];
    int[] arr2=new int[fac(9)]; 
    
    void solve() {      
        
        // 1.bruteforce sg(n) up to n=63
        solve1(1,0,new int[11],0);
        long sg,sum=0;
        for (int n=1;n<=63;n++) {
            sg=arr1[n][10];
            sum+=sg;
            System.out.println(n+" "+sg);
        } 
        
        // 2.for n>63, f(n) is of form x99999.. (x=[1..9])
        solve2(1,0,0, new int[9]);   
        long m=2,f=10000000; 
        for (int n=64;n<=150;n++) {
            sg=solve3(m*f-1);
            sum+=sg;
            System.out.println(n+" "+sg);
            if(++m>10){m=2;f*=10;}
        } 
        
        // 3.print sum
        System.out.println(sum);
                
    }
        
    void solve1(int sd, int nd, int[] n, int fd){
        if(n[0]>50||sd<9&&nd>sd) return;        
        int ds=(int)digsum(fd), a=arr1[ds][0];
        if(a==0||a>n[0]) arr1[ds]=n;
        for (int d=sd;d<10;d++) {
            int[] n2=n.clone();
            n2[0]++;n2[d]++;n2[10]+=d;
            solve1(d,(d==sd)?nd+1:1,n2,fd+fac(d));
        }       
    }
    
    void solve2(int d, int nfs, int ns, int[] n){
        if(d==9) arr2[nfs]=ns;
        for (int nd=0;nd<=d&&d<9;nd++) {
            int[] n2=n.clone();
            n2[0]+=nd;n2[d]=nd;
            solve2(d+1,nfs+nd*fac(d),ns+nd*d,n2);
        }       
    }

    long solve3(long f) {
        long d=0, f9=fac(9);
        for(int i=0;d==0;i++){
            if((f-i)%f9==0) d=((f-i)/f9)*9+arr2[i];            
        }
        return d;
    }
}