package P351_P400;

import static java.lang.Math.*;
import java.util.ArrayList;
import java.util.PriorityQueue; 

public class PE353 {

    public static void main(String[] args) {
        new PE353().solve(15);
    }

    int P;
    ArrayList<Coor> points;
    ArrayList<Vertex> vert;
    
    void solve(int N){
        double minDist,sum=0;
        for (int n=1;n<=N;n++){
            minDist=minDist((1<<n)-1);
            System.out.println(n+" "+minDist);
            sum+=minDist;
        }
        System.out.format("∑M = %.10f%n",sum);
    }
    
    double minDist(long r){
        points=new ArrayList<>();
        vert=new ArrayList<>();
        for (long x=0;x<=r;x++){
            for (long y=x;x*x+y*y<=r*r;y++){
                long z2=r*r-x*x-y*y;
                long z=(long) sqrt(z2);
                if (z*z==z2) points.add(new Coor(x,y,z,r));
            }
        }
        P=points.size();
        for (int i=0;i<=P;i++) vert.add(new Vertex(i));
        computePaths(vert.get(0),P);
        return vert.get(P).minDist;        
    }

    void computePaths(Vertex source,int s){ // Dijkstra
        source.minDist=0.;
        PriorityQueue<Vertex> q=new PriorityQueue<>();
        q.add(source);
        while(!q.isEmpty()){
            Vertex u=q.poll();
            if (u.n==s) return;
            for (Edge e:u.getAdjacencies()){
                Vertex v=e.target;
                double w=e.weight;
                double dist=u.minDist+w;
                if(dist>=v.minDist) continue;
                q.remove(v);
                v.minDist=dist;
                v.prev=u;
                q.add(v);              
            }
        }
    }
    
    class Coor {

        long x,y,z,r;

        Coor(long a,long b,long c,long d){
            x=a;
            y=b;
            z=c;
            r=d;
        }
        
        double dist(Coor c){
            long dx=x-c.x;
            long dy=y-c.y;
            long dz=z-c.z;
            double d=Math.sqrt(dx*dx+dy*dy+dz*dz);
            d=2*Math.asin(d/(2*r))/Math.PI;
            return d*d;
        }
        
    }

    class Vertex implements Comparable<Vertex>{
        
        int n;
        Vertex prev;
        double minDist=Double.POSITIVE_INFINITY;
        
        Vertex(int i){
            n=i;
        }

        @Override
        public int compareTo(Vertex o){
            return Double.compare(minDist,o.minDist);
        }

        ArrayList<Edge> getAdjacencies(){
            ArrayList<Edge> adj=new ArrayList<>();
            Coor c1=points.get(n);
            Coor c2=new Coor(c1.x,c1.y,-c1.z,c1.r);
            Edge e=new Edge(vert.get(P),c1.dist(c2));
            adj.add(e);
            for(int j=0;j<P;j++){
                if (n==j) continue;
                c2=points.get(j);
                e=new Edge(vert.get(j),2*c1.dist(c2));
                adj.add(e);
            }
            return adj;            
        }
    }

    class Edge {

        Vertex target;
        double weight;

        public Edge(Vertex t,double w){
            target=t;
            weight=w;
        }
    }

}