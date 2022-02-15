package P751_P800;

import static java.lang.Math.*;
import static util.Util.gcd;

public class PE764 {
  
  public static void main(String[] args) {
    new PE764().solve((long)1e4);
    new PE764().solve((long)1e7);
    new PE764().solve((long)1e16);
  }

  void solve(long N){
    
    long S=0;
    long M=(long)1e9;
    double q=0.25;
    
    for(long a=1;a<pow(2*N,q);a+=2)
      for(long b=1;b<min(a,pow(2*N-a*a*a*a,q));b+=2)
        if(gcd(a,b)==1) S=(S+(a*a*a*a-b*b*b*b)/8+a*b+(a*a*a*a+b*b*b*b)/2)%M;

    for(long a=1;a<pow(q*N,q);a+=2)
      for(long b=1;b<=pow((N-4*a*a*a*a)*q*q,q);b+=1)
        if(gcd(a,b)==1) S=(S+abs(a*a*a*a-4*b*b*b*b)+4*a*b+4*a*a*a*a+16*b*b*b*b)%M;

    System.out.format("S(%d)=%d%n",N,S);
  }
}
