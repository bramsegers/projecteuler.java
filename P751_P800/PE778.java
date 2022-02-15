package P751_P800;

public class PE778{

  public static void main(String[] args){
    new PE778().F(234567,765432,1000000009);
  }
  
  void F(int a,int b,int m){
    long A[],B[],C[]=new long[10],s=0;
    for(int c=1,d,t,u,v,w;b>=c;c*=10){
    A=new long[10];B=new long[10];d=b;
    while(d>0)A[t=(d--/c)%10]=B[t]=(A[t]+1)%m;
    for(w=1;w++<a;A=C,C=new long[10])
    for(u=0;u<10;u++)for(v=0;v<10;v++)
    C[t=(u*v)%10]=(C[t]+B[u]*A[v])%m;
    for(u=0;u<10;u++)s+=c*u*A[u];}
    System.out.println(s%m);
  }

}