# Payfort SDK for Java (Beta)

The **Payfort SDK For Java (Beta)** enables Java developers to easily integrate with Payfort

You can find more help in following link : https://docs.payfort.com/
## Feature ##
- ##### Call Fort API #####
- ##### Calculate Request Signature #####
- ##### Calculate Response Signature #####
- ##### Validate Response Signature #####
- ##### Convert From/To Currency Decimal Point ISO Standard #####

#### Minimum requirements ####

To run the SDK you will need **Java 1.8+**.

# Getting Started

##### Before you begin, you need an Fort account information 
* Merchant id
* Merchant access code
* SHA request phrase
* SHA response phrase

##### Once you check out the code from GitHub, you can build it using Maven.

```sh
mvn clean install
```


##### Using the SDK Maven modules after local build for maven ####

```xml

  <dependency>
    <groupId>com.payfort</groupId>
    <artifactId>java-sdk</artifactId>
    <version>1.0</version>
  </dependency>

```


#### Full Code Sample

#### Call Fort API

```java
import com.payfort.sdk.FortAccount;
import com.payfort.sdk.FortKeys;
import com.payfort.sdk.FortSDK;
import com.payfort.sdk.parameter.FortParameter;
import com.payfort.sdk.types.FortEnvironment;

public class App {

    public static void main(String[] args) {
        // Replace parameter with your own
        FortAccount configuration = new FortAccount("accessCode", "merchantId", "sharequest", "sharesponse");

        FortSDK fort = new FortSDK(configuration, FortEnvironment.SAND_BOX);

        FortParameter parameter = new FortParameter();
        // Later on you will use token name to complete host to host integration
        parameter.add(FortKeys.TOKEN_NAME, "Token Name");
        // there is utility for generate random merchant refernce fort.generateRandomMerchantReference()
        parameter.add(FortKeys.MERCHANT_REFERENCE, "Merchant refernce");
        parameter.add(FortKeys.RETURN_URL, "Your return url");
        // Your service command is TOKENIZATION
        parameter.add(FortKeys.SERVICE_COMMAND, "TOKENIZATION");
        FortParameter callResponse = fort.callApi(parameter);
    }
}
```


### Currency decimal point util

```java

import com.payfort.sdk.types.FortCurrency;

public class App{
    public static void main(String[] args) {
        double amountAfterAddingDecimalPoint = FortCurrency.USD.fortAmount(50);
        // Whilen USD Currency have 2 decimal point dependence on iso standard
        //      then the value of amountAfterAddingDecimalPoint will be 5000
    }
}
```



