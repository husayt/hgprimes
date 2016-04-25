Primes Calculator Demo using Google App Engine 


## 

Requires [Apache Maven](http://maven.apache.org) 3.1 or greater, and JDK 7+ in order to run.

To build,  first install primelib via 

    mvn install
then run
    mvn package

Building will run the tests, but to explicitly run tests you can use the test target

    mvn test

To start the app, use the [App Engine Maven Plugin](http://code.google.com/p/appengine-maven-plugin/) that is already included in this demo.  Just run the command.

    mvn appengine:devserver
	
To upload to gae

    mvn appengine:update

For further information, consult the [Java App Engine](https://developers.google.com/appengine/docs/java/overview) documentation.

## Further improvements

* Implement more 
* use dependency injection