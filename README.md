# api-platform-groovy

This is a repository of Groovy script examples for the Oracle API Platform Cloud Service.

## Example Policies 

- [JSONtoResources.grooby](https://github.com/OracleNATD/api-platform-groovy/blob/master/JSONtoResources.groovy) - Demonstrates how to retrieve values from the message BODY and convert them to a URL. This example was written to demonstrate how existing Level 0 REST APIs can be used to call a more mature RESTful API. 
- [ResourcesToJSON.groovy](https://github.com/OracleNATD/api-platform-groovy/blob/master/ResourcesToJSON.groovy) - Demonstrates how to retrieve URL resources from the PATH and covert them to JSON.  This example was written to support customers using Level 0 REST as defined by the [Richardson Maturity Model](http://restcookbook.com/Miscellaneous/richardsonmaturitymodel/). Using this Groovy script, along with the Method Mapping policy, it is possible to provide a more mature API in front of an immature implementation.
- [SetBody.groovy](https://github.com/OracleNATD/api-platform-groovy/blob/master/SetBody.groovy) - Demonstrates how to set a request body. 

