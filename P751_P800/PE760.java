package P751_P800;

public class PE760 {
 
  public static void main(String[] args) {
    new PE760().solve((long)1e01,1000000007);
    new PE760().solve((long)1e02,1000000007);
    new PE760().solve((long)1e18,1000000007);
  }
 
  void solve(long n,long m){
    long a=((((((n+2)/3)%m)*((n+1)%m))%m)*(n%m))%m;
    for(long b=1,d,g,c,e,f;b<=n;b*=2,a%=m){
      c=b%m;d=(n%b)%m;e=(n/b)&1;f=(c-d-1)%m;g=((n/b+1)/2)%m;
      a+=(((c*g)%m)*((((((2*c)%m)*c)%m)-f*(f-1))%m))*(1-e);
      a+=(((c*g)%m)*((((d+1)%m)*((d+2)%m))%m))*e;
      a+=(((((((g*(g-1))%m)*c)%m)*c)%m)*c)%m;
    }    
    System.out.println(a);
  }

}