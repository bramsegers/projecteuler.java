package P301_P350;

public class PE301 {

    /*
     * Info: http://www.suhendry.net/blog/?p=1586
     * Any position where the xor value of all piles is not zero
     * is a winning position, otherwise it is a losing position.
     */
    public static void main(String[] args) {
        new PE301().solve(1<<30);
    }

    void solve(long N){
        long sum=0;
        for(long n=1;n<=N;n++)
            sum+=(n^(2*n)^(3*n))==0?1:0;        
        System.out.format("P(%d)=%d%n",N,sum);
    } 

}
