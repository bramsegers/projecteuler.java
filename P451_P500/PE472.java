package P451_P500;

class PE472{

    // 1. brute force for small N
    // 2. plot in pe472.xls
    // 3. describe fractal pattern
    // 4. solve for big N

    public static void main(String[] args){
        //new PE472().brute(500);
        new PE472().solve(20);
        new PE472().solve(500);
        new PE472().solve((long)1e12);
    }

    long M=100000000;

    void solve(long N){
        long i,j,k,m,n,p,sum=0;
        for(p=1;p<=6&&p<=N;p++)
            sum+=(p==3)?2:(p==5)?3:p;
        for(n=p,i=1;p<=N;n=p+=1L<<++i){
            for(k=0,m=2;k<i;m=1+(1L<<++k))
                for(j=0;j<2&&n<=N;j++){
                    if(j==1&&k==i-1){
                        sum+=tri(m+2)-3;
                        if((n+=m-1)>N) sum-=tri(n-N+2)-3;
                        sum%=M;
                        continue;
                    }
                    sum+=((m%M)*((m+1)%M)-2)%M;
                    if((n+=m-1)>N) sum-=(j==0)
                        ?((m%M)*((m+1)%M))%M
                          -(((m-n+N)%M)*((m-n+N+1)%M))%M-2
                        :(((n-N)%M)*((n-N+1)%M))%M-2;
                    sum%=M;
                }
            if(N>n) sum+=8;
        }
        sum=(sum+M)%M;
        System.out.format("S(%d)=%d%n",N,sum);
    }

    long tri(long n){
        long a=n/2;
        if(n%2==0) n++; else a++;
        return (n%M)*(a%M)%M;
    }

    void brute(int N){
        int c=0;
        for(int n=1;n<=N;n++){
            int e=0;
            int[] ma=new int[n];
            for(int i=0;i<n;i++){
                boolean[] a=new boolean[n];
                a[i]=true;
                for(int j=1;;j++){
                    int maxd=0,maxi=0;
                    for(int k=0;k<n;k++){
                        int m,d1=0,d2=0;
                        for(m=k;m>=0&&!a[m];m--) d1++;
                        if(m==-1&&!a[0]) d1+=2*n;
                        for(m=k;m<n &&!a[m];m++) d2++;
                        if(m==n&&!a[n-1]) d2+=2*n;
                        int d=d1<d2?d1:d2;
                        if(d>maxd) {maxd=d;maxi=k;}
                    }
                    if(maxd<2) {ma[i]=j;e=e<j?j:e;break;}
                    a[maxi]=true;
                }
            }
            for(int i=0;i<n;i++)
                if(ma[i]==e){
                    System.out.println(n+" "+(i+1)+" "+ma[i]);
                    c++;
                }
        }
        System.out.println(c);
    }

}