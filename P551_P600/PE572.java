package P551_P600;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PE572{

    public static void main(String[] args){
        new PE572().solve(1);
        new PE572().solve(2);
        new PE572().solve(200);
    }

    void solve(int N){
        int C=0;
        Map<Integer,int[][]> M=new HashMap<>();
        for(int a=-N;a<=N;a++)
            for(int e=-N;e<=N;e++)
                for(int i=-N;i<=N;i++){
                    if(a+e+i<0) continue;
                    if(a+e+i>3) continue;
                    int bdcg=a-a*a;
                    int bdfh=e-e*e;
                    int cgfh=i-i*i;
                    int q=bdcg+bdfh+cgfh;
                    if((q&1)!=0) continue;
                    int bd=q/2-cgfh;
                    int fh=q/2-bdcg;
                    int cg=q/2-bdfh;
                    for(int[] bds:div(bd,N,M)){
                        int b=bds[0],d=bds[1];
                        for(int[] fhs:div(fh,N,M)){
                            int f=fhs[0],h=fhs[1];
                            for(int[] cgs:div(cg,N,M)){
                                int c=cgs[0],g=cgs[1];
                                if(a*b+e*b+c*h==b
                                && a*c+i*c+b*f==c
                                && a*d+e*d+f*g==d
                                && c*d+e*f+f*i==f
                                && a*g+i*g+d*h==g
                                && b*g+e*h+h*i==h) C++;
                            }
                        }
                    }
                }
        System.out.format("C(%d)=%d%n",N,C);
    }

    int[][] div(int k,int n,Map<Integer,int[][]> M){
        if(M.containsKey(k)) return M.get(k);
        int[][] dv=new int[4*n+1][];
        int i=0,a=k<0?-k:k;
        for(int d=-n;a==0&&d<=n;d++){
            dv[i++]=new int[]{d,0};
            if(d!=0) dv[i++]=new int[]{0,d};
        }
        for(int d=1;a>0&&d*d<=a&&d<=n;d++)
            if(k%d==0 && d<=n && a/d<=n){
                dv[i++]=new int[]{d,k/d};
                dv[i++]=new int[]{-d,k/-d};
                if(d*d!=a){
                    dv[i++]=new int[]{k/d,d};
                    dv[i++]=new int[]{k/-d,-d};
                }
            }
        dv=Arrays.copyOf(dv,i);
        M.put(k,dv);
        return dv;
    }

}