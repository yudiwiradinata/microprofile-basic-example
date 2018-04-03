Microprofile basic example

To create an executable jar, run the following command:
mvn package

And to run our microservice, we use this command:
java -jar target/library-service.jar

test the url
curl http://localhost:9080/library/books

[
  {
    "id": "0001-201802",
    "isbn": "1",
    "name": "Building Microservice With Eclipse MicroProfile",
    "author": "baeldung",
    "pages": 420
  }
]
