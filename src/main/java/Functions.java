import java.math.BigInteger;
import java.util.Dictionary;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Functions {
    public BigInteger labseq_funct(int n,Dictionary<Integer,BigInteger> dict){
        //check if n belongs to the starter sequence 
        if(n==0||n==2){
            return new BigInteger("0");
        }
        else if(n==1||n==3){
            return new BigInteger("1");
        }
        //else recursively call function to obtain value for labseq(n)
        else{
            if (dict.get(n-4)==null){ //check if value for labseq(n-4) doesn't exist
                dict.put(n-4, labseq_funct(n-4, dict)); //calculate and add to dictionary value for labseq(n-4)
            }
            if(dict.get(n-3)==null){ //check if value for labseq(n-3) doesn't exist
                dict.put(n-3, labseq_funct(n-3, dict)); //calculate and add to dictionary value for labseq(n-3)
            }
            dict.put(n,dict.get(n-3).add(dict.get(n-4))); //calculate and add to dictionary value for labseq(n)
            return dict.get(n); //return value for labseq(n)
        }
    }
}
