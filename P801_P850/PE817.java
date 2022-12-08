package P801_P850;

class PE817 {

  public static void main(String[] args) {
    long ans = new PE817().solve(1000000007L, 100000);
    System.out.println(ans); // 2min 2sec
  }

  long solve(long p,int d){
    long a[]=new long[d];
    long b[]=new long[8];
    long m=1,k=1,c=0,s=0,t;
    for (int i,j;;k+=2,m++){
      for (t=k,j=0;t>0;j++){
        t= (b[j]+=t)/p;
        if ((b[j]%=p)<p-d) continue;
        if (a[i=(int)(p-b[j]-1)]>0) continue;
        if ((s+=a[i]=m)>0 && ++c==d) return s;
      }
    }
  }

}