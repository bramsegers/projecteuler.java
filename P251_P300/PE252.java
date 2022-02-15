package P251_P300;

import java.util.Arrays;
 
public class PE252 {
 
    public static void main(String[] args) {
        new PE252().solve(500);
    }
   
    int N, max_a;
    int [][][] ET;
    double[][] S; 
    Point[] P,Q;
    int[] max_pol;
   
    void solve(int N) {
       
        // 1. generate PRN points
        this.N=N;
        Q=new Point[N];
        P=new Point[N];
        ET=new int[N][N][N];
        S=new double[N][N];
        long s = 290797;
        for (int x,y,n=0; n<N; n++){
            s = (s*s)%50515093;
            x = (int)(s%2000);
            s = (s*s)%50515093;
            y = (int)(s%2000);
            P[n] = new Point(n,x,y);
            Q[n] = P[n];
        } 
        
        // 2. generate empty triangles
        for (int i=0; i<N; i++) {
            for (int j=i+1; j<N; j++) {
                for (int k=j+1; k<N; k++) {
                    int a = area(P[i],P[j],P[k]);
                    ET[i][j][k]=a;
                    ET[i][k][j]=a;
                    ET[j][i][k]=a;
                    ET[j][k][i]=a;
                    ET[k][i][j]=a;
                    ET[k][j][i]=a;
                }
            }
        }
        
        // 3. pre-calculate slope (atan2)
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                Point p = P[i], q=P[j];
                double dx = q.x-p.x;
                double dy = q.y-p.y;
                S[i][j] = Math.atan2(dy,dx);
            }
        }
       
        // 4. search max area
        for (int i=0; i< N; i++) {
            
            // 4.1 sort
            final Point p=P[i];         
            Arrays.sort(Q, (p1, p2) -> {
                double slope1 = S[p.id][p1.id];
                double slope2 = S[p.id][p2.id];
                return slope1<slope2?1:slope1==slope2?0:-1;
            });     
            
            // 4.2 search
            int[] pol = new int[N];
            pol[0]=p.id;
            search(pol,0,1,0);           
            
            // 4.3 print
            double a = 0.5*max_a;
            String m = Arrays.toString(max_pol);
            System.out.format("%d/%d %.1f %s %n",i,N,a,m);                            
        }    
    }   
    
    void search(int[] pol, int i, int s, int a) {
        
        // 1. slope 1 must be < slope 2
        if(s>3 && S[pol[s-3]][pol[s-2]] < S[pol[s-2]][pol[s-1]]) return;      
        
        // 2. triangle must be empty
        if(s>2 && ET[pol[0]][pol[s-1]][pol[s-2]]==0) return;
        
        // 3. update area
        if(s>2) a+= ET[pol[0]][pol[s-1]][pol[s-2]]; 
        
        // 4. update max
        if(a>max_a){
            max_a=a;
            max_pol=Arrays.copyOfRange(pol,0,s);
        }       
        
        // 5. extend search
        for (int j=i; j<N && S[pol[0]][Q[j].id]>0; j++) {
            pol[s]=Q[j].id;
            search(pol,j+1,s+1,a);
        }
    }
    
    int area(Point i, Point j, Point k) {
       
        // 1. i,j,k on same line?
        int a=-j.y*k.x+i.y*(-j.x+k.x)+i.x*(j.y-k.y)+j.x*k.y;
        if(a==0) return 0;
        
        // 2. points inside triangle?       
        int sign=a<0?-1:1;
        for (int n=0; n<N; n++) {
            int s=(i.y*k.x-i.x*k.y+(k.y-i.y)*P[n].x+(i.x-k.x)*P[n].y)*sign;
            int t=(i.x*j.y-i.y*j.x+(i.y-j.y)*P[n].x+(j.x-i.x)*P[n].y)*sign;  
            if(s>0 && t>0 && (s+t)<a*sign) return 0;
        }       
        
        // 3. return area of valid empty triangle
        return a*sign;
    }
 
    class Point {
       
        int id,x,y;
       
        Point(int n, int i, int j) {
            id=n;
            x=i;
            y=j;
        }     
        
    }
 
}