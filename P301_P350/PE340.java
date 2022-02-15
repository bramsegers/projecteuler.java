package P301_P350;

public class PE340 {

    // For a=50,b=2000,c=40:
    // F[x]=x+40  (x:1951..2000)
    // F[x]=x+120 (x:1901..1950)
    // F[x]=x+200 (x:1851..1900)
    
    // For other a,b,c:
    // 40  => 4a-4c
    // 120 => 4a-4c+1*(4a-3c)
    // 200 => 4a-4c+2*(4a-3c)
    // Each part has (2000-1950=a) numbers
    
    public static void main(String[] args){
        //new PE340().brute(50,2000,40);
        new PE340().solve(50,2000,40,1000000000);
        new PE340().solve(1801088541L,558545864083284007L,35831808L,1000000000L);
    }
    
    void brute(long a,long b,long c){
        A=a;B=b;C=c;
        long f,sum=0;
        for(long n=b;n>=0;n--){
            sum+=(f=F(n));
            System.out.println(n+" "+f);
        }
        System.out.println(sum);
    }
    
    long A,B,C;
    long F(long n){
        return n>B?n-C:F(A+F(A+F(A+F(A+n))));
    }
    
    void solve(long a,long b,long c,long m){
        long d,e,f,g,h,i,j,s;
        d=b/a;
        e=((a-c)*4)%m;
        f=(4*a-3*c)%m;
        g=(d*(d-1)/2)%m;
        h=(g*f)%m;
        i=e+(d*f)%m;
        j=((b%a)+1)%m;
        s=(b&1)==0
            ?((b/2)%m)*((b+1)%m)
            :(((b+1)/2)%m)*(b%m);
        s=(s%m)+((((d*e)%m)*a)%m);
        s=(s%m)+((a%m)*h)%m+(i*j)%m;
        System.out.println(s%m);
    }

}