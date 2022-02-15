package P751_P800;

import static util.Util.bi;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class PE776 {

  public static void main(String[] args) {
    new PE776().solve(1234567890123456789L);
  }
  
  Map<String,BigInteger[]> M=new HashMap<>();
  
  void solve(long n){
    double r=0;
    int s=(""+n).length()*9;
    while(s>0) r+=f(n,s)[1].doubleValue()/s--;
    System.out.println(r);
  }
  
  BigInteger[] f(long a,long b) {
    if(b==0) return new BigInteger[]{bi(1),bi(0)};
    if(a==0) return new BigInteger[]{bi(0),bi(0)};
    if(M.containsKey(a+","+b)) return M.get(a+","+b);
    BigInteger[] c,d=new BigInteger[]{bi(0),bi(0)};
    for (long k=0;k<=(9<b?9:b);k++){
      c=k<=a%10?f(a/10,b-k):a>9?f(a/10-1,b-k):new BigInteger[]{bi(0),bi(0)};
      d[1]=d[1].add((c[0].multiply(bi(k))).add(c[1].multiply(bi(10))));
      d[0]=d[0].add(c[0]); }
    M.put(a+","+b,d);
    return d;
  }
  
}

/*
<script>

let M = {}

let f = (a, b) => {
  if (!b) return [1n, 0n]
  if (!a) return [0n, 0n]
  if (M[[a, b]]) return M[[a, b]]
  for (var c = 0n, d = 0n, i, j, k = 0n; k <= (9 < b ? 9 : b); k++)
    [i, j] = k <= a % 10n ? f(a / 10n, b - k) : a >= 10 ? f(a / 10n
    - 1n, b - k) : [0n, 0n], [c, d] = [c + i, d + i * k + j * 10n]
  return M[[a, b]] = [c, d]
}

let F = (n) => {
  let r=0, s = ('' + n).length * 9
  while (s) r += Number(f(n, BigInt(s))[1]) / s--
  return r
}

console.log(F(1234567890123456789n))

</script>
*/