package P351_P400;

public class PE355{

  public static void main(String[] args){
    new PE355().solve(30);
    new PE355().solve(200000);
  }

  int N,P[],Q[];

  void solve(int n){
    int i,j,k;
    P=new int[N=n]; Q=new int[N];
    for (i=0,j=(n=1); ++j<N;) if(Q[j]<1){
    for (k=j; k+j<N;) Q[k+=j]=1; n+=P[i++]=j;}
    System.out.format("C(%d)=%d%n", N, f(n));
  }

  int f(int s){
    int i,j,p,q,t,u,v,x,y;
    int[] a=new int[N], b=new int[N];
    for (t=s,i=0;(p=P[i])*p<=N; i++) if(p>0){
    for (x=p,y=0;x*p<=N;) x*=p; for (q=p;;q*=p){
    for (j=N/q;Q[j]>0;)j--; if (j<=N/j) break;
    if (x<q*j-j) {x=q*j-j; y=j;}} if(a[y]>0){
    P[i]^=-(Q[y]=1); u=f(s+x-p); P[i]^=-1;
    P[a[y]]^=-1; v=f(s+b[y]); P[a[y]]^=-1;
    return (Q[y]=0)+(u>v?u:v);} t+=x-p;
    a[y]=i; b[y]=x-p;} return t;
  }
}