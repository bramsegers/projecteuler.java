package P351_P400;

public class PE352 {

    public static void main(String[] args) {
        new PE352().solve(10000,50);
    }

    void solve(int s,int p){
        double sum=0;
        for(int i=1;i<=p;i++) 
            sum+=T(s,0.01*i);        
        System.out.format("%.6f%n",sum);
    }
    
    double T(int s,double p){
        double zn,z,t,zk,q=1-p;
        double[] t1=new double[s+1];
        double[] t2=new double[s+1];
        double[] pw=new double[s+1];
        pw[0]=1;
        for(int i=1;i<=s;i++)
            pw[i]=q*pw[i-1];
        for(int n=1;n<=s;n++){
            zn=1-pw[n];
            t2[n]=(n==1)?0:n;
            for(int k=1;k<n;k++){
                z=(1-pw[k])/zn;
                t=1+z*(t2[k]+t1[n-k])+(1-z)*t2[n-k];
                t2[n]=Math.min(t2[n],t);
            }
            t1[n]=n;
            for(int k=1;k<=n;k++){
                zk=1+(1-pw[k])*t2[k]+t1[n-k];
                t1[n]=Math.min(t1[n],zk);
            }
        }
        return t1[s];
    }
}
