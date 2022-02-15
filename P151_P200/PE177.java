package P151_P200;

import static java.lang.Math.*;
import java.util.HashSet;

public class PE177 {

    public static void main(String[] args) {
        new PE177().solve();
    }

    void solve() {
        
        double[] sin=new double[180];
        for(int i=0;i<180;i++){
            sin[i]=sin(Math.PI*i/180);
        }
        
        int a,b,c,d,e,f,g,h,j,k;
        HashSet<Long> set=new HashSet<>();        
        for(a=1;a<180;a++){
            for(b=1;a+b<180;b++){
                c=180-a-b; d=180-b;
                for(e=1;d+e<180;e++){
                    f=180-d-e;
                    for(g=1;b+g<180;g++){
                        h=180-b-g;                        
                        for(j=1;d+j<180;j++){
                            k=180-d-j;
                            double s1=sin[a]*sin[f]*sin[g]*sin[j];
                            double s2=sin[c]*sin[e]*sin[h]*sin[k];
                            if(abs(s1-s2)<1e-9){
                                long t=asLong(k,a,c,f,e,g,h,j);
                                t=min(t,asLong(c,f,e,g,h,j,k,a));
                                t=min(t,asLong(e,g,h,j,k,a,c,f));
                                t=min(t,asLong(h,j,k,a,c,f,e,g));
                                t=min(t,asLong(j,h,g,e,f,c,a,k));
                                t=min(t,asLong(g,e,f,c,a,k,j,h));
                                t=min(t,asLong(f,c,a,k,j,h,g,e));
                                t=min(t,asLong(a,k,j,h,g,e,f,c));                                
                                set.add(t);
                                break;
                            }
                        }                    
                    }
                }
            }
        }
        System.out.println(set.size());
    }
    
    long asLong(int... a){
        long rv=0;
        for(int i:a) rv=(rv<<8)+i;
        return rv;
    }

}