package P651_P700;

public class PE656{

    public static void main(String[] args){
        new PE656().solve(100,1000);
    }
    
    long mod=(long)1e15;
    
    void solve(int g,int n){
        long sum=0;
        for(int a=1,t=1,i=1;a<=n;a++)
            if(a==t) t+=(i+=2);
            else sum=(sum+H(g,a))%mod;
        System.out.println(sum);
    }
    
    long H(int g,int a){
        long ha=0,la=0,hn=1,ln=1,hb=1,lb=1,c=1,s=1;
        while((ha-1)*(ha-1)<a) la=--ha;
        while(true){
            long na=ha+la-1;
            long nb=hb+lb;
            if(na*na>a*nb*nb){
                ha=na+1;
                hb=nb;
                hn=(hn+ln)%mod;
                s=(hn+s)%mod;
                if(++c==g) return s;
            }else{
                la=na;
                lb=nb;
                ln=hn+ln;
            }
        }
    }

}