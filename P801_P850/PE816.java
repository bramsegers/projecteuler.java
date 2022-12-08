package P801_P850;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class PE816{

  public static void main(String[] args) {
    new PE816().solve(2000000);
  }

  void solve(int n){
    long s = 290797;
    long m = 50515093;
    var P = new ArrayList<Point>();
    for(int x,y,i=0;i<n;i++){
      x=(int)s; s=(s*s)%m;
      y=(int)s; s=(s*s)%m;
      P.add(new Point(x,y));
    }
    Collections.sort(P,(Point p,Point q)->p.x-q.x);
    System.out.format("%.9f%n",search(P,m+m));
  }

  double search(List<Point> P,double m){
    int n=P.size();
    if (n<2) return m;
    m = search(P.subList(0,n/2),m);
    m = search(P.subList(n/2,n),m);
    return strip(P,P.get(n/2).x,m);
  }

  double strip(List<Point> P,int x,double m){
    var Q = new ArrayList<Point>();
    for(Point p:P) if (p.x> x+m)
    break; else if (x-m <= p.x) Q.add(p);
    Collections.sort(Q,(Point p,Point q)->p.y-q.y);
    for(int i=Q.size();--i>0;) for(int j=i-1;j>=0;j--)
    m = Math.min(m,Q.get(i).distance(Q.get(j)));
    return m;
  }
  

}