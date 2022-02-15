package P201_P250;

public class PE232 {

    public static void main(String[] args) {
        new PE232().solve(100);
    }

    int N;
    double[][] prob;

    void solve(int n) {
        N = n;
        prob = new double[n][n];
        double p=0.5*(prob(0,0)+prob(1,0));
        System.out.format("P(%d)=%.8f%n",n,p);
    }

    double prob(int a, int b) {                                                 //probability of b winning at score(a,b) and b's turn
        if (b>=N) return 1;
        if (a>=N) return 0;
        if (prob[a][b] == 0) {                                                  //build table dynamically
            double p,d,e;
            for (int S=1, T=1; S<2*N; S*=2) {                                   //b can win S points throwing T coins
                p=1.0/(1<<T++);                                                 //probability of b winning S points
                d=0.5*p     * prob(a+1,b+S)                                     //a wins,   b wins
                 +0.5*p     * prob(a  ,b+S)                                     //a looses, b wins
                 +0.5*(1-p) * prob(a+1,b  );                                    //a wins,   b looses
                e=d/(1-(0.5*(1-p)));                                            //probability of b winning when throwing T coins at score(a,b)
                if(e>prob[a][b]) prob[a][b]=e;                                  //update optimal strategy
            }
        }
        return prob[a][b];
    }
}
