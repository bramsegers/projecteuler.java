package P301_P350;

import static java.lang.Math.PI;
import static java.lang.Math.acos;
import static java.lang.Math.sqrt;

public class PE332 {

    public static void main(String[] args) {
        new PE332().solve(50);
    } 
    
    void solve(int R){
        int[][] xyz=new int[1000][3];
        double sum=0;
        for(int r=1;r<=R;r++){
            int cnt=0;
            double minarea=Double.MAX_VALUE;
            for(int x=-r;x<=r;x++){
                for(int y=-r;y<=r;y++){
                    for(int z=0;z<=r;z++){
                        int n=x*x+y*y+z*z;
                        if(n==r*r){
                            xyz[cnt][0]=x;
                            xyz[cnt][1]=y;
                            xyz[cnt][2]=z;
                            cnt++;
                        }
                    }
                }
            }
            for(int i=0;i<cnt;i++){
                for(int j=i+1;j<cnt;j++){
                    for(int k=j+1;k<cnt;k++){
                        int x1=xyz[i][0];
                        int x2=xyz[j][0];
                        int x3=xyz[k][0];
                        int y1=xyz[i][1];
                        int y2=xyz[j][1];
                        int y3=xyz[k][1];
                        int z1=xyz[i][2];
                        int z2=xyz[j][2];
                        int z3=xyz[k][2];
                        double a=sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2)+(z1-z2)*(z1-z2));
                        double b=sqrt((x2-x3)*(x2-x3)+(y2-y3)*(y2-y3)+(z2-z3)*(z2-z3));
                        double c=sqrt((x1-x3)*(x1-x3)+(y1-y3)*(y1-y3)+(z1-z3)*(z1-z3));
                        int delta=x1*(y2*z3-y3*z2)-y1*(x2*z3-x3*z2)+z1*(x2*y3-x3*y2);
                        if(delta>0){
                            double cosa=1-a*a/(2*r*r);
                            double cosb=1-b*b/(2*r*r);
                            double cosc=1-c*c/(2*r*r);
                            double sina=sqrt(1-cosa*cosa);
                            double sinb=sqrt(1-cosb*cosb);
                            double sinc=sqrt(1-cosc*cosc);
                            double A=acos((cosa-cosb*cosc)/(sinb*sinc));
                            double B=acos((cosb-cosa*cosc)/(sina*sinc));
                            double C=acos((cosc-cosa*cosb)/(sina*sinb));
                            double area=r*r*(A+B+C-PI);
                            if(area<minarea) minarea=area;
                        }
                    }
                }
            }
            System.out.println(r+" "+minarea);
            sum+=minarea;
        }
        System.out.format("%.6f%n",sum);
    }

}