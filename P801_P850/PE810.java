package P801_P850;

public class PE810{

  public static void main(String[] args){
    System.out.println(new PE810().solve());
  }

  int N=1<<27;
  int K=5000000;
  boolean[] B=new boolean[N];

  int mul(int x,int y,int m){
    while(y>0){m^=(y&1)>0?x:0;x<<=1;y>>=1;}
    return m;
  }

  int solve(){
    for(int i=2,j,x;;i++)
      if(!B[i]) if(--K<1) return i; else
      for(j=2;(x=mul(i,j,0))<N;j++) B[x]=true;
  }

}