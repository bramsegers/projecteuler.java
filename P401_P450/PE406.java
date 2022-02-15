package P401_P450;

public class PE406{

    public static void main(String[] args){
        new PE406().solve();
    }

    void solve(){
        double ans=0;
        long f0=0,fk=1,f2=1;
        for(long k=1;k<=30;k++){
            long n=(long)1e12;
            double a=Math.sqrt(k);
            double b=Math.sqrt(fk);
            ans+=C(n,a,b);
            f0=fk;
            fk=f2;
            f2+=f0;
        }
        System.out.format("%.8f%n",ans);
    }

    double C(long n,double a,double b){
        double[] cs=new double[5000];
        long[] ns=new long[5000];
        ns[0]=1;
        int i,j=0,k=0;
        for(i=1;;i++){
            long m=ns[j]+ns[k];
            double ca=cs[j]+a;
            double cb=cs[k]+b;
            double c=Math.min(ca,cb);
            double d=Math.abs(ca-cb);
            ns[i]=m;
            cs[i]=c;
            if(d<1e-8){j++;k++;}
            else if(ca<cb) j++;
            else k++;
            if(m>n) break;
        }
        return cs[i-1];
    }

}
