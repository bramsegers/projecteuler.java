package P401_P450;

import static util.Util.modPow;

public class PE405{

    public static void main(String[] args){
        //new PE405().brute();
        new PE405().solve();
    }
    
    void solve(){
        int n=200000000;
        int m=410338673;
        int p=0;
        long[] a=new long[n];
        a[2]=2;a[3]=16;
        for(int i=4;p==0;i++){
            a[i]=(((6*a[i-1] -7*a[i-2] -6*a[i-3] +8*a[i-4])%m)+m)%m;
            if(a[i]==a[3] && a[i-1]==a[2] && a[i-2]==0 && a[i-3]==0) p=i-3;
        }
        System.out.println("period="+p);        
        int i=(int)modPow(10,(long)1e18,p);
        System.out.println(a[i]);
    }

    // 0 0
    // 1 0
    // 2 2
    // 3 16
    // 4 82
    // 5 368
    // 6 1554
    // 7 6384
    // 8 25874
    // 9 104176
    // 10 418066
    // 11 1674992
    // 12 6705426
    //  
    // Wolfram: 
    // FindLinearRecurrence[0,0,2,16,82,368,1554,6384,25874,104176,418066]
    // Result; {6,-7,-6,8}
    //
    // I.e.:   f(n)=6f(n−1)−7f(n−2)−6f(n−3)+8f(n−4)    
    void brute(){
        Block[] B2,B=null;
        for(int n=1,i=0;i<=12;i++){
            B2=new Block[n];
            for(int j=0;j<n;j++)
                if(i>0) B2[j]=B[j>>2].spawn(j&3);
                else B2[j]=new Block(0,0,1<<13,1<<13);
            int c=0;
            int[][] a=new int[1<<14][1<<14];
            for(Block b:B2){
                if(++a[b.xa][b.ya]==4) c++;
                if(++a[b.xa][b.yb]==4) c++;
                if(++a[b.xb][b.ya]==4) c++;
                if(++a[b.xb][b.yb]==4) c++;
            }
            System.out.println(i+" "+c);
            B=B2;
            n<<=2;
        }
    }

    class Block {
        
        int xa,ya,xb,yb;
        
        Block(int a,int b,int c,int d){
            xa=a;ya=b;xb=c;yb=d;
        }

        Block spawn(int i){
            int x=xb-xa,y=yb-ya;
            if(x>y){
                x>>=2;
                y>>=1;
                if(i==0) return new Block(xa+x,ya,xa+3*x,ya+y);
                if(i==1) return new Block(xa+x,ya+y,xa+3*x,yb);
                if(i==2) return new Block(xa,ya,xa+x,yb);
                else     return new Block(xa+3*x,ya,xb,yb);
            }else{
                x>>=1;
                y>>=2;
                if(i==0) return new Block(xa,ya,xb,ya+y);
                if(i==1) return new Block(xa,ya+3*y,xb,yb);
                if(i==2) return new Block(xa,ya+y,xa+x,ya+3*y);
                else     return new Block(xa+x,ya+y,xb,ya+3*y);
            }
        }
        
    }
    
}
