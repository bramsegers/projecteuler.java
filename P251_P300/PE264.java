package P251_P300;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PE264{

    //    1. max radius = P/sqrt(27)
    //    2. radius = odd multiple of 5
    //    3. Ax+Bx+Cx=5,  Ay+By+Cy=0

    //    P(100000)=2816417.1055 (7 minutes 26 seconds)

    public static void main(String[] args){
        new PE264().solve(100000);
    }

    void solve(long P){

        Map<Integer,List<Point>> map=new HashMap<>();
        long R=P*P/27;
        for(int i=0;2*i*i<=R;i++){
            for(int j=i+1;;j+=2){
                int s=i*i+j*j;
                if(s>R) break;
                if(s%10!=5) continue;
                List<Point> list=map.get(s);
                if(list==null){
                    list=new ArrayList<>();
                    map.put(s,list);
                }
                list.add(new Point(i,j));
            }
        }

        double sum=0;
        for(int key:map.keySet()){
            List<Point> list=new ArrayList<>();
            for(Point p:map.get(key)){
                int x=p.x, y=p.y;
                list.add(new Point( x, y));
                list.add(new Point( x,-y));
                list.add(new Point( y, x));
                list.add(new Point(-y, x));
                if(x>0){
                    list.add(new Point(-x, y));
                    list.add(new Point( y,-x));
                    list.add(new Point(-y,-x));
                    list.add(new Point(-x,-y));
                }
            }
            int len=list.size();
            for(int i=0;i<len;i++){
                Point p1=list.get(i);
                for(int j=i+1;j<len;j++){
                    Point p2=list.get(j);
                    for(int k=j+1;k<len;k++){
                        Point p3=list.get(k);
                        int tx=p1.x+p2.x+p3.x;
                        int ty=p1.y+p2.y+p3.y;
                        if(tx==5 && ty==0){
                            sum+=dist(p1,p2);
                            sum+=dist(p1,p3);
                            sum+=dist(p2,p3);
                            System.out.format(
                                "(%d,%d) (%d,%d) (%d,%d)%n",
                                p1.x,p1.y,p2.x,p2.y,p3.x,p3.y
                            );
                        }
                    }
                }
            }
        }

        System.out.format("P(%d)=%.4f%n",P,sum);
    }

    class Point{
        int x,y;
        Point(int i,int j){x=i;y=j;}
    }

    double dist(Point p1,Point p2){
        int x=p1.x-p2.x;
        int y=p1.y-p2.y;
        return Math.sqrt(x*x+y*y);
    }

}