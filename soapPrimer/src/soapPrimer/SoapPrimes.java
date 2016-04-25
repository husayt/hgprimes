package soapPrimer;
import ox.calc.PrimesService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 * Created by huseyn on 21/04/2016.
 */
@WebService()
public class SoapPrimes {
  @WebMethod
  public String sayHelloWorldFrom(String from) {
    String result = "Hello, world, from " + from;
    System.out.println(result);
    return result;
  }
  @WebMethod
  public String isPrime(long number, String methodname) {
    try {
      final Boolean prime = PrimesService.getGenerator(methodname).isPrime(number);
      return prime.toString();
    } catch (Exception x) {
      return "Error: "+x.getMessage();
    }
  }
  public static void main(String[] argv) {
    Object implementor = new SoapPrimes();
    String address = "http://localhost:9000/";
    Endpoint.publish(address, implementor);
  }
}
