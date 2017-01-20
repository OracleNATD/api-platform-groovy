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
    params = context.clientRequest.getQueryParameters()
    
    // If paramaters were included, they Map needs to be converted to a string...
    def paramString = "";
    if (params != null) {
        params.eachWithIndex { entry, i ->
            if (i == 0)
                paramString = "$entry.key=$entry.value"
            else
                paramString = "$paramString&$entry.key=$entry.value"    
        }
    }
           
    def resubmitUri = "${backendServiceURL}${apiPath}?${paramString}&minMatch=2<90%"
    
    // Rebuild the Uri to content with spaces in the path...
    URL url = new URL(resubmitUri);
    URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
    url = uri.toURL();
    resubmitUri = url.toString();
    
    /***************************************************************************
    * API Platform Call
    * Resubmit the request to the Backend Service URL
    ***************************************************************************/
    ((ExternalCalloutBuilder) context.createCallout().withRequestUrl(resubmitUri).withRequestMethod("GET")).build().send("storedResponse");    
           
}