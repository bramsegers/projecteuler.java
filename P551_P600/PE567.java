package P551_P600;

import static util.Util.choose;

public class PE567{

    /*

                   1    n    ch(n,k)
        Ja(n) =   ──   ∑   ─────
                  2^n   k=1    k



                  n        1
        Jb(n) =   ∑   ───────
                 k=1   k * ch(n,k)



                  Ja(n-1)     1        1
        Ja(n) =   ────  +  ─ ( 1 - ── )
                     2        n       2^n



                  Jb(n-1)     1
        Jb(n) =   ────  +  ─
                     2        n

    */
    public static void main(String[] args){
        new PE567().S(123456789);
    }

    void S(long m){
        double a=0.5;
        double b=1;
        double h=0.5;
        double S=a+b;
        for(long n=2;n<=m;n++){
            h*=0.5;
            a=a/2+(1-h)/n;
            b=b/2+1D/n;
            S+=a+b;
        }
        System.out.format("S(%d)=%.8f%n",m,S);
    }

    double Ja(int n){
        double sum=0;
        for(int k=1;k<=n;k++)
            sum+=choose(n,k)/(double)k;
        sum/=Math.pow(2,n);
        return sum;
    }

    double Jb(int n){
        double sum=0;
        for(int k=1;k<=n;k++)
            sum+=1D/(k*choose(n,k));
        return sum;
    }

}
