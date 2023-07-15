package P801_P850;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class PE836 {

  public static void main(String[] args) {
    new PE836().solve();
  }

  void solve() {

    String input = """
      <p>Let $A$ be an <b>affine plane</b> over a <b>radically integral local field</b> $F$ with residual characteristic $p$.</p>
      <p>We consider an <b>open oriented line section</b> $U$ of $A$ with normalized Haar measure $m$.</p>
      <p>Define $f(m, p)$ as the maximal possible discriminant of the <b>jacobian</b> associated to the <b>orthogonal kernel embedding</b> of $U$ <span style="white-space:nowrap;">into $A$.</span></p>
      <p>Find $f(20230401, 57)$. Give as your answer the concatenation of the first letters of each bolded word.</p>
    """;

    String ans      = "";
    String regex    = "<b>(.*?)</b>";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(input);

    while (matcher.find())
      for (String s:matcher.group(1).split(" "))
        ans += s.charAt(0);

    System.out.println(ans);

  }

}