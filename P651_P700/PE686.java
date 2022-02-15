package P651_P700;

public class PE686 {

    //https://math.stackexchange.com/questions/2006159/leading-digits-of-large-power-of-2
    public static void main(String[] args){
        new PE686().p(12,1);
        new PE686().p(12,2);
        new PE686().p(123,45);
        new PE686().p(123,678910);
    }

    void p(long L,long n){
        double e,f;
        long i,j,k,p=0,q=0;
        e=Math.log10(2);
        i=(long)(Math.log10(L)+1);
        while(true){
            j=1+(long)(e*p);
            f=e*p-j+i;
            k=(long)Math.pow(10,f);
            if(k==L) q++;
            if(q==n) break;
            p++;
        }
        System.out.format("p(%d,%d)=%d%n",L,n,p);
    }

}
