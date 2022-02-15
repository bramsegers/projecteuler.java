package P151_P200;

public class PE198 {

    //https://en.wikipedia.org/wiki/Farey_sequence#Farey_neighbours
    public static void main(String[] args) {
        new PE198().solve(100,100000000);
    }    
    
    void solve(long P, long Q){
        long count=1;
        for(long b=1;2*b<Q;b++){
            long q,i=(b&1)==1?1:2;
            for(long d=b+1;(q=2*b*d)<Q;d+=i){
                long[] euc=euclid(b,d);
                long a=-euc[1];
                long c=euc[0];
                if(a<0){a=-a;c=-c;}
                long f=a*d-b*c;
                if(f<-1||f>1) continue;
                long p=a*d+b*c;
                if(p*P<q) count++;                
            }
        }
        System.out.println(count);
    }
    
    long[] euclid(long a,long b) {
        if(b>a){
            long[] coeffs=euclid(b,a);
            long[] output={coeffs[1],coeffs[0]};
            return output;
        }
        long q=a/b;
        long r=a-q*b;
        if(r==0){
            long[] output={0,1};
            return output;
        }
        long[] next=euclid(b,r);
        long[] output={next[1],next[0]-q*next[1]};
        return output;
    }
}
