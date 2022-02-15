package P301_P350;

/*
    n-digits numbers gave a expected value of 10^n-n+1 
    except when the head and the tail of the number are identical for k digits 
    then the expected value increases with 10^k
*/
public class PE316 {

    public static void main(String[] args){
        new PE316().solve(999,(long)1e6);
        new PE316().solve(999999,(long)1e16);
    }

    void solve(long a,long b){
        long sum=0;
        for(int n=2;n<=a;n++)
            sum+=g(b/n);
        System.out.println(sum);
    }

    long g(long n){
        String s=""+n;
        int d=s.length();
        long rv=(long)Math.pow(10,d)-d+1;
        rv+=overlap(s,d);
        return rv;
    }

    long overlap(String s,int len){
        long rv=0;
        for(int k=1;k<len;k++)
            if(s.endsWith(s.substring(0,k)))
                rv+=(long)Math.pow(10,k);
        return rv;
    }

}
