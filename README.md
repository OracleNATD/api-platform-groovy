# api-platform-groovy

This is a repository of Groovy script examples for the [Oracle API Platform Cloud Service](https://cloud.oracle.com/en_US/api-platform). If you have policies you'd like to contribute, let us know and we'll add you as a contributor. 

## Example Policies 

### Request Policies

- [JSONtoResources.groovy](https://github.com/OracleNATD/api-platform-groovy/blob/master/JSONtoResources.groovy) - Demonstrates how to retrieve values from the message BODY and convert them to a URL. This example was written to demonstrate how existing Level 0 REST APIs can be used to call a more mature RESTful API.
- [ResourcesToJSON.groovy](https://github.com/OracleNATD/api-platform-groovy/blob/master/ResourcesToJSON.groovy) - Demonstrates how to retrieve URL resources from the PATH and covert them to JSON.  This example was written to support customers using Level 0 REST as defined by the [Richardson Maturity Model](http://restcookbook.com/Miscellaneous/richardsonmaturitymodel/). Using this Groovy script, along with the Method Mapping policy, it is possible to provide a more mature API in front of an immature implementation.
- [SetBody.groovy](https://github.com/OracleNATD/api-platform-groovy/blob/master/SetBody.groovy) - Demonstrates how to set a request body.

### Response Policies
- [Orchestration.groovy](https://github.com/OracleNATD/api-platform-groovy/blob/master/Orchestration.groovy) and [OrchestrationSetResponse.groovy](https://github.com/OracleNATD/api-platform-groovy/blob/master/OrchestrationRetrieveResponse.groovy) - These 2 policies demonstrate how a response can be evaluated and an alternative request submitted if the initial response wasn't as desired. For example, if a catalog search returns zero results, resubmit the search using a relaxed query match.
 

