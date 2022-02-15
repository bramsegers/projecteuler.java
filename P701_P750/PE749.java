package P701_P750;

public class PE749{

    public static void main(String[] args){
        new PE749().solve(2);
        new PE749().solve(6);
        new PE749().solve(16);
    }

    void solve(int D){
        long ans = 0;
        for(int k=2;k<=D;k++) ans+=r(D,new int[10],k,D,9,0);
        System.out.format("S(%d)=%d%n",D,ans);
    }

    long r(int D,int[] A,int k,int n,int d,long s){
        long a=0;
        if(d==0){A[0]=n;a+=(v(D,A,s-1)?s-1:0)+(v(D,A,s+1)?s+1:0);}
        else for(int i=0;i<=n;i++){A[d]=i;a+=r(D,A,k,n-i,d-1,s+i*(long)Math.pow(d,k));}
        return a;
    }

    boolean v(int D,int[] A,long x){
        int[] B=A.clone();
        for(int n=0;n<D;n++,x/=10) if(--B[(int)((x+10)%10)]<0) return false;
        return x==0;
    }
}