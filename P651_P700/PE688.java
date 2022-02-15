package P651_P700;

public class PE688 {

    public static void main(String[] args){
        new PE688().solve((long)1e16,1000000007);
    }

    void solve(long N,long M){
        long sum=0;
        for(long n,k=1;(n=k*(k+1)/2)<=N;k++){
            long m1=(N+1-n)/k;
            long m2=(N+1-n)%k;
            long t=(m1&1)==0
                ?(((m1/2)%M)*((m1+1)%M))%M
                :((m1%M)*(((m1+1)/2)%M))%M;
            long s1=((k%M)*t)%M;
            long s2=((m2%M)*((m1+1)%M))%M;
            sum=(sum+s1+s2)%M;
        }
        System.out.println(sum);
    }
    
}
