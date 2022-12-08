package P751_P800;

import java.util.Arrays;
import java.util.stream.IntStream;

public class PE790 {

  public static void main(String[] args) {
    //new PE790().C(0);       // 30621295449583788
    //new PE790().C(1);       // 30613048345941659
    //new PE790().C(10);      // 21808930308198471
    //new PE790().C(100);     // 16190667393984172
    new PE790().C(100000);  // 16585056588495119  (elapsed: 26 seconds)
  }

  int S = 290797;
  int H = 50515093;
  int G(){int r=S;S=(int)((1L*S*S)%H);return r;}

  void C(int t){

    int[] I = new int[H];
    int[][] Q = new int[2*t][2];
    int[][] R = new int[2*t][4];

    for (int a,x,X,y,Y,i=0;i<t;i++){
      x=G();X=G();y=G();Y=G();
      if (x>X) {a=x;x=X;X=a;}
      if (y>Y) {a=y;y=Y;Y=a;}
      Q[2*i]= new int[]{x,12};
      R[2*i]= new int[]{y,x,X,1};
      Q[2*i+1]= new int[]{X+1,12};
      R[2*i+1]= new int[]{Y+1,x,X,-1};
    }

    Arrays.sort(R,(int[]a,int[]b)->a[0]-b[0]);
    Arrays.sort(Q,(int[]a,int[]b)->a[0]-b[0]);
    for(int i=0;i<2*t;i++) I[Q[i][0]]=i;

    long TOT = 0;
    long CUR = 12*H;

    for(int i=0,y=0;y<H;y++){
      while (i<2*t && R[i][0]==y){
        int[]e=R[i++];
        int p=I[e[1]];
        int q=I[e[2]+1];
        CUR +=IntStream.range(p,q).parallel().map(k->{
          int b=Q[k][1],a=b;
          int m=Q[k+1][0]-Q[k][0];
          b=(b+=e[3])<1?12:b>12?1:b;
          return m*((Q[k][1]=b)-a);
        }).sum();
      }
      TOT+=CUR;
    }
    System.out.format("C(%d)=%d%n",t,TOT);
  }

}