# luis-pires-labseq-challenge

This project uses Quarkus, the Supersonic Subatomic Java Framework.

## Running the application in dev mode

To run the application in dev mode that enables live coding use:

```shell script
./mvnw quarkus:dev
```

To access the application through the Swagger UI REST API Documentation it should be located at: <http://localhost:8080/q/swagger-ui/>.

To access the application's result for labseq(n): <http://localhost:8080/labseq/{n}/> where {n} should be replaced by the value of n for which you want the result.

The Java code developed for the problem's solution is located in the src/main/java/ folder with comments explaining what was used to reach the target