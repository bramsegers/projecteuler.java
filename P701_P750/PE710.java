package P701_P750;

public class PE710 {

    // t(n) = 2^floor(n/2) - A000931(n+5)
    long solve(int M){
        int a=0,b=1,c=1,p=1;
        for(long n=2;;n++){
            int d=a+b;
            a=b;
            b=c;
            c=d;
            if(n%2==0) p*=2;
            if(p>M) p-=M;
            if(c>M) c-=M;
            int t=p-c;
            if(t<0) t+=M;
            if(n>42 && t==0) return n;
        }
    }

    int t(int n,int done,int two){
        int todo=n-2*done;
        int t=(two==1||todo==2)?1:0;
        for(int j=1;2*j<=todo;j++)
            t+=t(n,done+j,j==2?1:two);
        return t;
    }

    public static void main(String[] args){
        System.out.println("t(42)="+new PE710().t(42,0,0));
        System.out.println("n="+new PE710().solve(1000000));
    }

}