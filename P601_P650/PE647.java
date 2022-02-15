package P601_P650;

public class PE647{

    public static void main(String[] args){
        System.out.println(new PE647().solve((long)1e3));
        System.out.println(new PE647().solve((long)1e12));
    }

    long solve(long N){
        long sum=0;
        for(long k=3;;k+=2){
            for(long m=1;;m++){
                long A=(2*(k-2)*m+1)*(2*(k-2)*m+1);
                long B=(k-4)*(k-4)*m*((k-2)*m+1)/2;
                if(m==1 && (A>N || B>N)) return sum;
                if(A>N || B>N) break;
                sum+=A+B;
            }
        }
    }

}
