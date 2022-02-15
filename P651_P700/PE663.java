package P651_P700;

class PE663{

    public static void main(String[] args){
        new PE663().S(14,0,100);
        new PE663().S(107,0,1000);
        new PE663().S(10000003,10000000,10200000);
    }

    void S(int N,int L1,int L2){
        long sum=0;
        long[] a=new long[N];
        int[] t=new int[2*L2];
        t[2]=1;
        for(int i=3;i<2*L2;i++)
            t[i]=(t[i-1]+t[i-2]+t[i-3])%N;
        for(int i=1;i<=L2;i++){
            a[t[2*i-2]]+=2*t[2*i-1]-N+1;
            if(i>L1) sum+=kadane(a,N);
        }
        System.out.println(sum);
    }

    long kadane(long a[],int n){
        long max=-(1L<<60), cmax=0;
        for(int i=0;i<n;i++){
            cmax+=a[i];
            if(max<cmax) max=cmax;
            if(cmax<0) cmax=0;
        }
        return max;
    }
}