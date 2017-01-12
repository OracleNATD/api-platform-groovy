/*******************************************************************************
 * API Platform Call 
 * Getting the response body 
 ******************************************************************************/
body = context.southboundResponse.getBodyAsType(String.class)

// Convert to Groovy Object
def slurper = new groovy.json.JsonSlurper()
def messageBody = (Map) slurper.parseText(body)

if (messageBody['recordSetTotal'] == 0) {
    
    /***************************************************************************
     * API Platform Call 
     * Getting the Backend Service URL
     **************************************************************************/
    backendServiceURL = context.getSouthboundCallout().getRequestUrl();
    
    /***************************************************************************
     * API Platform Call 
     * Getting the API Path
     **************************************************************************/
    apiPath = context.clientRequest.getApiPathInfo()
    
    /***************************************************************************
     * API Platform Call 
     * Getting the request query parameters
     **************************************************************************/
    params = context.clientRequest.getQueryParameters().toString()
    
    // Remove sorrounding brackets or braces
    def cleanParams = params.substring(1, params.length()-1)    
    
    def resubmitUri = "${backendServiceURL}${apiPath}?${cleanParams}&minMatch=2%3C90%25"
    
    /***************************************************************************
    * API Platform Call
    * Resubmit the request to the Backend Service URL
    ***************************************************************************/
    ((ExternalCalloutBuilder) context.createCallout().withRequestUrl(resubmitUri).withRequestMethod("GET")).build().send("storedResponse");               
}
