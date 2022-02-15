package P251_P300;

public class PE275{

    public static void main(String[] args){
        new PE275().solve(10); //P(10)=964      (00:00:00.016)
        new PE275().solve(15); //P(15)=360505   (00:00:01.392)
        new PE275().solve(18); //P(18)=15030564 (00:00:49:816)
    }

    int N;

    void solve(int n){
        N=n;
        int[] occp=new int[N];
        int[] skip=new int[2*N];
        int[] cand={1,P(N,0)};
        boolean[][] occ2=new boolean[2*N][N+1];
        int p=countPoly(0,0,occp,skip,cand,occ2);
        System.out.format("P(%d)=%d%n",N,p/2);
    }

    int countPoly(int n,int b,int[] o,int[] s,int[] c,boolean[][] o2){
        if(!hasReachableBalance(n,b,c)) return 0;
        if(n==N) return isSymmetric(o,o2)?2:1;
        int rv=0, s0=s[0];
        for(int i=1;i<=c[0];i++){
            o[n]=c[i];
            int x=x(c[i]);
            int y=y(c[i]);
            o2[x][y]=true;
            int[] c2=new int[2*N];
            for(int j=1;j<=c[0];j++)
                if(j<i) add(s,c[j]);
                else if(j>i) add(c2,c[j]);
            int ri=P(x+1,y);
            int le=P(x-1,y);
            int up=P(x,y+1);
            int dw=P(x,y-1);
            if(       !o2[x+1][y] && !has(s,ri) && !has(c2,ri)) add(c2,ri);
            if(       !o2[x-1][y] && !has(s,le) && !has(c2,le)) add(c2,le);
            if(       !o2[x][y+1] && !has(s,up) && !has(c2,up)) add(c2,up);
            if(y>0 && !o2[x][y-1] && !has(s,dw) && !has(c2,dw)) add(c2,dw);
            rv+=countPoly(n+1,b+x-N,o,s,c2,o2);
            o2[x][y]=false;
            s[0]=s0;
        }
        return rv;
    }

    boolean hasReachableBalance(int n,int b,int[] c){
        if(b==0) return true;
        int t=N-n, min=2*N, max=0;
        for(int i=1;i<=c[0];i++){
            int x=x(c[i]);
            if(x<min) min=x;
            if(x>max) max=x;
        }
        if(b<0) return b+t*(max-N)+(t-1)*t/2 >= 0;
        else    return b+t*(min-N)-(t-1)*t/2 <= 0;
    }

    boolean isSymmetric(int[] o,boolean[][] o2){
        for(int p:o) if(!o2[2*N-x(p)][y(p)]) return false;
        return true;
    }

    int P(int x,int y)             {return (y<<16)+x;    }
    int x(int p)                   {return p&((1<<16)-1);}
    int y(int p)                   {return p>>16;        }
    void    add(int[] a,int p)     {a[++a[0]]=p;}
    boolean has(int[] a,int p)     {int i=a[0];while(i>0&&a[i]!=p) i--; return i>0;}

}