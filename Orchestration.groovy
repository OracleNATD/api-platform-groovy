def timestamp = new Date();
println "\n *** ${timestamp} Begin Request *** \n"

// Sample JSON body for testing...
def body ='{"recordSetTotal": 0}'

// Sample URI for testing...
def uri = 'https://nodeapicontainer-gse00001975.apaas.em2.oraclecloud.com/bealls/catalog?queryString=Florida%20Gator%20Pants'
def params = 'queryString=Florida%20Gator%20Pants'

/*******************************************************************************
 * API Platform Call 
 * Getting the response body 
 ******************************************************************************/
//body = context.southboundResponse.getBodyAsType(String.class)
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
     * Getting the request path
     **************************************************************************/
    //def pathInfo = context.clientRequest.getApiPathInfo()
    //println "receivedPath = ${pathInfo}"
    
    /***************************************************************************
     * API Platform Call 
     * Getting the request query parameters
     **************************************************************************/
    params = context.clientRequest.getQueryParameters()
    println "receivedParams = ${params}"
    
    /***************************************************************************
     * API Platform Call 
     * Getting the request URI
     **************************************************************************/
    //def uri = context.clientRequest.getRequestURI()
    //println "uri = ${uri}"
    
    //def resubmitUri = "${uri}&queryScope=All"
    //println resubmitUri
    
    /***************************************************************************
    * API Platform Call
    * Setting the request path and query string 
    ***************************************************************************/
    //context.southboundCallout.withPathInfo(urlString)
    context.southboundCallout.withQueryString($receivedParams}&QueryScope='All')
    
    
        
}

println "\n *** ${timestamp} End Request *** \n"
