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
    private static Hashtable<Integer,BigInteger> values_dictionary = new Hashtable<Integer,BigInteger>(); //creating an Hashtable to save values of intermediate calculations for future use;
    
    @Inject
    Functions labseq_funct; //Injecting the function created to apply on the value n
    
    @GET
    @Path("labseq/{n}") //creating the endpoint for n
    @Produces(MediaType.TEXT_PLAIN)
    //Adding Description for user
    @Operation(
        summary = "calculate labseq(n)",
        description = "Insert value n (n needs to be a non-negative Integer) to get labseq(n)"
    )
    public String hello(int n){
        long startTime = System.nanoTime(); //to be used later to calculate execution time performance 

        //check if value has been previously calculated
        if(values_dictionary.get(n)!=null){
            long endTime   = System.nanoTime(); //timestamp to use for execution time performance
            long totalTime = endTime-startTime; //calculating execution time performance
            return "Value for labseq(" + n + ") was already previously calculated:\n" + values_dictionary.get(n) + "\nRuntime to find solution: " + totalTime + " nanoseconds or " + TimeUnit.NANOSECONDS.toSeconds(totalTime) + " seconds.";
            //returning value for labseq(n) as well has time it took to obtain it in nanoseconds/seconds.
        }

        //check if introduced value of n is a non-negative Integer
        if(n<0){
            //return error message
            throw new WebApplicationException(Response.status(422).entity("Can't calculate labseq(n)!!\nValue is not a non-negative Integer!!").build());
        }
        //else call recursive function to obtain the value for labseq(n)
        else{
            BigInteger result_value = labseq_funct.labseq_funct(n,values_dictionary);
            long endTime   = System.nanoTime(); //timestamp to use for execution time performance
            long totalTime = endTime-startTime; //calculating execution time performance
            return "The Result Value for labseq(" + n + ") is:\n" + result_value +"\nRuntime to find solution: " + totalTime + " nanoseconds or " + TimeUnit.NANOSECONDS.toSeconds(totalTime) + " seconds.";
            //returning value for labseq(n) as well has time it took to obtain it in nanoseconds/seconds.
        }

    }

}
