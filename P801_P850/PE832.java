package P801_P850;

class PE832 {
  
  public static void main(String[] args) {
    System.out.println(new PE832().M(10));
    System.out.println(new PE832().M(1000));
    System.out.println(new PE832().M((long)1e18));
  }

  long m =1000000007;

  long M(long n){
    if (n<1) return 0;
    long q=n/4, r = n&3;
    return (r<1 ? 12*M(q) + 4*M(q-1) + 18*q         :
            r<2 ? 16*M(q)            + 18*q +  6    :
            r<3 ? 12*M(q) + 4*M(q+1) + 18*q +  6    :
                   8*M(q) + 8*M(q+1) + 18*q + 12) % m;
  }

}