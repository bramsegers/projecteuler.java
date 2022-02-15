package P701_P750;

public class PE713 {

    public static void main(String[] args){
        new PE713().solve(10000000);
    }
       
    void solve(int N){
        long[] divs=new long[N+1];
        for(int n=1;n<=N;n++)
            for(int d=n;d<=N;d+=n)
                divs[d]++;
        long sum=0;
        for(int n=1;n<=N;n++)
            sum+=divs[n]*(N-n);
        System.out.println(sum);
    }
}
