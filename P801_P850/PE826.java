package P801_P850;

public class PE826 {

  public static void main(String... args) {
    new PE826().solve(1000000);
  }

  void solve(int n) {
    double s=0,c=0;
    for (double p:util.Util.getPrimes(n))
      if (p>2) {c++;s+=2/++p+(p-4)*7/p/18;}
    System.out.format("%.10f%n",s/c);
  }

}