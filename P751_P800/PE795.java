package P751_P800;

public class PE795{

  public static void main(String[] args){
    new PE795().solve(1234);
    new PE795().solve(12345678);
  }

  void solve(int N){
    long G=0;
    int i,j,p,q,r,s,t;
    int PI[]=new int[N+1];
    int PR[]=new int[N+1];
    int PH[]=new int[N+1];
    int SV[]=new int[N+1];
    for (i=2;i<=N;i++) PI[i]=1;
    for (i=2;i*i<=N;i++) if(PI[i]>0)
    for (j=i;i*j<=N;j++) PI[i*j]=0;
    for (j=0,i=2;i<=N;i++) if(PI[i]>0)PR[j++]=i;
    for (i=1;i<=N;i++) PH[i]=i;
    for (i=2;i<=N;i++) if(SV[i]<1)
    for (j=i;j<=N;j+=i) {SV[j]=1;PH[j]/=i;PH[j]*=i-1;}
    for (r=1;r<=N;r++){
    for (t=1,i=0,q=r;q>1;i++){
    for (p=PR[i],s=0;q%p<1;q/=p)if((s=1-s)>0)t*=p;if(PI[q]>0){t*=q;q=1;}}
    for (j=t;j<=N;j+=t) G+=(1-2*(j&1))*PH[r]*(N/r-(j-1)/r);}
    System.out.format("G(%d)=%d%n",N,G);
  }

}