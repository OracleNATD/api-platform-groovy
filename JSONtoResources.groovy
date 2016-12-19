// Sample JSON body for testing...
def messageString =
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

//messageString = context.clientRequest.getBodyAsType(Object)
//println(messageString)

// Convert to Groovy Object
def slurper = new groovy.json.JsonSlurper()
def messageBody = slurper.parseText(messageString)
println(messageBody)
println ""

// The platform's Groovy parser will not accept this format...
// def platformId = messageBody.request[0].platformId

def request = messageBody["request"] ?: "No Request";
println (request)
println("");

// Nor this format...
// platformId = request["platformId"][0];

def platformId = request["platformId"].toString();
platformId = platformId.replace('[', '');
platformId = platformId.replace(']', '');

def clientRequestId = request["clientRequestId"].toString();
clientRequestId = clientRequestId.replace('[', '');
clientRequestId = clientRequestId.replace(']', '');

def domainId = request["data"]["domain"].toString();
domainId = domainId.replace('[', '');
domainId = domainId.replace(']', '');

def accountId = request["data"]["accountId"].toString();
accountId = accountId.replace('[', '');
accountId = accountId.replace(']', '');

// Build the URL string
def urlString = "platforms/" + platformId + "/domains/" + domainId + "/accounts/" + accountId + "?clientRequestId=" + clientRequestId
println (urlString)

