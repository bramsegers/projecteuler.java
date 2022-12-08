package P801_P850;

public class PE804 {

  public static void main(String[] args){
    new PE804().solve((long)1e03);
    new PE804().solve((long)1e06);
    new PE804().solve((long)1e16);
  }

  void solve(long N){
    long T = 0;
    long Y = (long)(Math.sqrt(4.0*N/163));
    for (long m=0,y=0; y<=Y; y++,m=1){
      double d = Math.sqrt(4.0*N-163*y*y);
      long p = (long)Math.ceil ((-y-d)/2);
      long q = (long)Math.floor((-y+d)/2);
      T += (m+1) * (q-p+m);
    }
    System.out.format("T(%d)=%d%n", N, T);
  }

}