import java.math.BigInteger;
import java.util.Dictionary;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Functions {
    public BigInteger labseq_funct(int n,Dictionary<Integer,BigInteger> dict){
        if(n==0||n==2){
            return new BigInteger("0");
        }
        else if(n==1||n==3){
            return new BigInteger("1");
        }
        else{
            if (dict.get(n-4)==null){
                dict.put(n-4, labseq_funct(n-4, dict));
            }
            if(dict.get(n-3)==null){
                dict.put(n-3, labseq_funct(n-3, dict));
            }
            dict.put(n,dict.get(n-3).add(dict.get(n-4)));
            return dict.get(n);
        }
    }
}
