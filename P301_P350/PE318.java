package P301_P350;

public class PE318 {

    public static void main(String[] args){
        new PE318().solve(2011);
    }

    // Decimal tends to 1 if √q-√p < 1
    // There will be 2011 or more 9's if (√q-√p)^2n <= 10^(-2011)
    // Or, for any p,q pair then n = ceiling(-2011/(2*log10(√q-√p))

    void solve(int N){
        long sum=0;
        for(int p=1;p<=N;p++){
            for(int q=p+1;p+q<=N;q++){
                double sp=Math.sqrt(p);
                double sq=Math.sqrt(q);
                if(sq-sp>=1) continue;
                sum+=Math.ceil(-N/(2*Math.log10(sq-sp)));
            }
        }
        System.out.println(sum);
    }

}
