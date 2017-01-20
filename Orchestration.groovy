def timestamp = new Date();
println "\n *** ${timestamp} Begin Request *** \n"

// Sample JSON body for testing...
def body ='{"recordSetTotal": 0}'

// Sample API path for testing
def apiPath='catalog'

// Sample parameters for testing...
def params = '{queryString=Florida%20Gator%20Pants}'
//def params = ['queryString':'Florida%20Gator%20Pants']

//Sample Backend Service URL for testing...
def backendServiceURL = 'https://nodeapicontainer-gse00001975.apaas.em2.oraclecloud.com/bealls'

/*******************************************************************************
 * API Platform Call 
 * Getting the response body 
 ******************************************************************************/
body = context.southboundResponse.getBodyAsType(String.class)
println "bodyReceived"

// Convert to Groovy Object
def slurper = new groovy.json.JsonSlurper()
def messageBody = (Map) slurper.parseText(body)
println "*** Original Message ***"
println messageBody
println ""

println messageBody['recordSetTotal']

if (messageBody['recordSetTotal'] == 0) {
    
    println "No records found, resubmitting based on subset of search terms"
    
    /***************************************************************************
     * API Platform Call 
     * Getting the Backend Service URL
     **************************************************************************/
    backendServiceURL = context.getSouthboundCallout().getRequestUrl();
    println "Backend Service URL = ${backendServiceURL}"
    
    /***************************************************************************
     * API Platform Call 
     * Getting the API Path
     **************************************************************************/
    apiPath = context.clientRequest.getApiPathInfo()
    println "API Path = ${apiPath}"    
    
    /***************************************************************************
     * API Platform Call 
     * Getting the request query parameters
     **************************************************************************/
    params = context.clientRequest.getQueryParameters().toString()
    println "receivedParams = ${params}"
   
    // Remove sorrounding brackets or braces
    def cleanParams = params.substring(1, params.length()-1)    
    println "cleanParams = ${cleanParams}"    
    
    //def resubmitUri = "${backendServiceURL}${apiPath}?${params}&minMatch=2%3C90%25"
    def resubmitUri = "${backendServiceURL}${apiPath}?${params}&minMatch=2<90%"
    println "resubmitUri = ${resubmitUri}"
    
    // Rebuilt the Uri to content with spaces in the path...
    URL url = new URL(resubmitUri);
    URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
    println "url.getRef() = ${url.getRef()}"
    println "uri = {$uri}"
    url = uri.toURL();
    resubmitUri = url.toString();
    println "resubmitUri = ${resubmitUri}"
    
    /***************************************************************************
    * API Platform Call
    * Resubmit the request to the Backend Service URL
    ***************************************************************************/
    ((ExternalCalloutBuilder) context.createCallout().withRequestUrl(resubmitUri).withRequestMethod("GET")).build().send("storedResponse");    
           
}

println "\n *** ${timestamp} End Request *** \n"
