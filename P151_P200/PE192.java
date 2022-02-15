package P151_P200;

import java.math.BigDecimal;

public class PE192 {

    public static void main(String[] args) {
        new PE192().solve(100000,(long)1e12);
    }
    
    void solve(long maxN,long maxQ){
        long sum=0,d=0,D=1,S=0,P=0,Q=0,n,p,q,a;
        for (n=1;n<=maxN;n++) {
            if(++d==D){d=0;D+=2;S++;continue;}
            long p1=0,q1=1,a1=S,N=0;
            long P1=1,P2=0,Q1=0,Q2=1,bestQ=0;
            BigDecimal PQ,DIF,MDIF=bd(1),BS=sqrt(n);
            while(++N>0) {     
                for(long e=1;e<=a1;e++){
                    P=e*P1+P2;
                    Q=e*Q1+Q2;    
                    if(Q>maxQ){N=-1;break;}
                    PQ=bd(P).divide(bd(Q),SCA,RHU);
                    DIF=(BS.subtract(PQ)).abs();
                    if(DIF.compareTo(MDIF)<0){
                        MDIF=DIF;bestQ=Q;
                    }
                }                
                p=(N==1)?S:a1*q1-p1;
                q=(N==1)?n-(S*S):(n-p*p)/q1;
                a=(S+p)/q;
                p1=p;q1=q;a1=a;
                P2=P1;Q2=Q1;P1=P;Q1=Q;
            }
            sum+=bestQ;           
        }
        System.out.println(sum);
    }
        
    BigDecimal sqrt(long n){
        BigDecimal N=bd(n),T=bd(2),x0=bd(0),x1=bd(1);
        while(!x0.equals(x1)){
            x0=x1;x1=N.divide(x0,SCA,RHU).add(x0).divide(T,SCA,RHU);
        }
        return x1;
    }
    
    int SCA=64,RHU=BigDecimal.ROUND_HALF_UP;
    BigDecimal bd(long n){return BigDecimal.valueOf(n);}
}
