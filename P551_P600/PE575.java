package P551_P600;

public class PE575{

    public static void main(String[] args){
        new PE575().solve(5);
        new PE575().solve(1000);
    }

    void solve(int N){
        double sum=0;
        double a=N*(5*N-4);
        double b=N*(4*N-4);
        for(int n=1;n<=N;n++){
            int s=n*n-1;
            int r=s/N;
            int c=s%N;
            int i=2;
            if(r==0||r==N-1) i--;
            if(c==0||c==N-1) i--;
            sum+=(i+3)/a;
            sum+=(i+2)/b;
        }
        System.out.format("P(%d)=%.12f%n",N,sum/2);
    }

}