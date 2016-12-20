def timestamp = new Date()
println "\n *** ${timestamp} Begin Request *** \n"

// Sample JSON body for testing...
def body =
'{\
    "clientInfo": {\
        "apiAccessKey": "364735764358"\
    },\
    "request": [\
        {\
            "platformId": "NSI",\
            "action": "Query",\
            "clientRequestId": "2371264761-23891273",\
            "objectType": "Account",\
            "data": {\
                "domain": "somedomain.com",\
                "accountId": "34159006"\
            }\
        }\
    ]\
}'

/* 
 * API Platform Call 
 *  Getting the request body 
 */
body = context.clientRequest.getBodyAsType(String.class)

// Convert to Groovy Object
def slurper = new groovy.json.JsonSlurper()
def messageBody = (Map) slurper.parseText(body)
println messageBody
println ""

def requestArray =  (ArrayList) messageBody['request'] ?: "No Request"
println requestArray
println "\n"

def platformId = requestArray['platformId'][0]
def clientRequestId = requestArray['clientRequestId'][0]
def domainId = requestArray['data']['domain'][0]
def accountId = requestArray['data']['accountId'][0]

// Build the URL string
def urlString = "platforms/" + platformId + "/domains/" + domainId + \
"/accounts/" + accountId + "?clientRequestId=" + clientRequestId
println urlString

/*******************************************************************************
 *  API Platform Call
 *  Setting the request path and query string 
 ******************************************************************************/
context.southboundCallout.withPathInfo(urlString)
context.southboundCallout.withQueryString('clientRequestId?{$clientRequestId}')

println "\n *** ${timestamp} End Request *** \n"
