package P801_P850;

import util.Primes;

public class PE808{

  public static void main(String[] args){
    new PE808().solve();
  }

  int N=50;
  Primes P=new Primes(40000000);
  
  void solve(){
    System.out.println("#\tRPS\t\tSUM");
    for(long s=0,c=0,r,t,n=1;c<N;n+=2)
      if((r=rev(t=n*n))!=t && prm(n)>0 && r==(r=sqr(r))*r && prm(r)>0)
        System.out.println(++c+"\t"+t+"\t"+(c<11?"\t":"")+(s+=t));
  }

  long sqr (long n) { return (long)Math.sqrt(n);                       }
  long prm (long n) { return P.isPrime((n))?1:0;                       } 
  long rev (long n) { long r=0;while(n>0){r=10*r+n%10;n/=10;}return r; }

}