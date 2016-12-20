def timestamp = new Date()
println "\n *** ${timestamp} Begin Request *** \n"
// Sample JSON body for testing...
def body =
'{\
    "clientInfo": {\
        "apiAccessKey": "364735764358"\
    },\
    "requestArray": [\
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



//body = context.clientRequest.getBodyAsType(String.class)
//println "body = ${body}"
/*
def parser= new groovy.json.JsonSlurper()
Map payload = (Map) parser.parseText(body)
Map abc = (Map)payload.abcString
value = abc.def
println "field=${value}"
context.southboundCallout.withHeader("nick", value)
*/

// Convert to Groovy Object
def slurper = new groovy.json.JsonSlurper()
def messageBody = (Map) slurper.parseText(body)
println messageBody
println ""

def requestArray =  (ArrayList) messageBody['requestArray'] ?: "No Request"
println requestArray
println "\n"

def platformId = requestArray['platformId']
def clientRequestId = requestArray['clientRequestId']
def domainId = requestArray['data']['domain']
def accountId = requestArray['data']['accountId']

// Build the URL string
def urlString = "platforms/" + platformId + "/domains/" + domainId + "/accounts/" + accountId + "?clientRequestId=" + clientRequestId
println urlString
println "\n *** ${timestamp} End Request *** \n"
