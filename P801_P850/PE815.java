package P801_P850;

import java.util.HashMap;

public class PE815{

  public static void main(String[] args){
    new PE815().solve(2);
    new PE815().solve(60);
  }

  void solve(int n){

    record State(int m,int a,int b,int c,int d){}
    var P = new HashMap<State,Double>();
    var s = new State(0,0,0,0,0);
    var p = 1d;
    P.put(s,p);

    for (int c=4*n; c>0; c--){
      var Q = new HashMap<State,Double>();
      for (var t : P.keySet()){
        var i = c - 3*t.a - 2*t.b - t.c;
        var m = Math.max(t.m, t.a + t.b + t.c + 1);
        if (i  >0) { s=new State(m  ,t.a+1,t.b  ,t.c  ,t.d  ); p=P.get(t)*    i/c; Q.put(s, p + Q.getOrDefault(s,0d)); }
        if (t.a>0) { s=new State(t.m,t.a-1,t.b+1,t.c  ,t.d  ); p=P.get(t)*3*t.a/c; Q.put(s, p + Q.getOrDefault(s,0d)); }
        if (t.b>0) { s=new State(t.m,t.a,  t.b-1,t.c+1,t.d  ); p=P.get(t)*2*t.b/c; Q.put(s, p + Q.getOrDefault(s,0d)); }
        if (t.c>0) { s=new State(t.m,t.a,  t.b  ,t.c-1,t.d+1); p=P.get(t)*  t.c/c; Q.put(s, p + Q.getOrDefault(s,0d)); }
      }
      P = Q;
    }

    p = 0;
    for (var t:P.keySet()) p += t.m * P.get(t);
    System.out.format("P(%d)=%.8f%n", n, p);
  }

}