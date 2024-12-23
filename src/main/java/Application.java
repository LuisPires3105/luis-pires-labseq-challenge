import java.math.BigInteger;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;
import org.eclipse.microprofile.openapi.annotations.Operation;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/")
public class Application {
    private static Hashtable<Integer,BigInteger> values_dictionary = new Hashtable<Integer,BigInteger>();
    
    @Inject
    Functions labseq_funct;
    
    @GET
    @Path("labseq/{n}")
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(
        summary = "calculate labseq(n)",
        description = "Insert value n (n needs to be a non-negative Integer) to get labseq(n)"
    )
    public String hello(int n){
        long startTime = System.nanoTime();

        if(values_dictionary.get(n)!=null){
            long endTime   = System.nanoTime();
            long totalTime = endTime-startTime;
            return "Value for labseq(" + n + ") was already previously calculated:\n" + values_dictionary.get(n) + "\nRuntime to find solution: " + totalTime + " nanoseconds or " + TimeUnit.NANOSECONDS.toSeconds(totalTime) + " seconds.";
        }

        if(n<0){
            throw new WebApplicationException(Response.status(422).entity("Can't calculate labseq(n)!!\nValue is not a non-negative Integer!!").build());
        }
        else{
            BigInteger result_value = labseq_funct.labseq_funct(n,values_dictionary);
            long endTime   = System.nanoTime();
            long totalTime = endTime-startTime;
            return "The Result Value for labseq(" + n + ") is:\n" + result_value +"\nRuntime to find solution: " + totalTime + " nanoseconds or " + TimeUnit.NANOSECONDS.toSeconds(totalTime) + " seconds.";
        }

    }

}
