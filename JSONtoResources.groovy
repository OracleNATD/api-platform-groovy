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


/*
def body = context.clientRequest.getBodyAsType(String.class)
println "body = ${body}"

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

def requestArray =  (ArrayList) messageBody["requestArray"] ?: "No Request"
println requestArray
println ""

def request = ""
requestArray.each {
    request = it
    println request
}

// The platform fails validation for
// def platformId = request.platformId

def platformId = request["platformId"]
def clientRequestId = request["clientRequestId"]
def domainId = request["data"]["domain"]
def accountId = request["data"]["accountId"]

// Build the URL string
def urlString = "platforms/" + platformId + "/domains/" + domainId + "/accounts/" + accountId + "?clientRequestId=" + clientRequestId
println urlString

