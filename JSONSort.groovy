def timestamp = new Date();
println "\n *** ${timestamp} Begin Request *** \n"

// Sample JSON body for testing...
def body =
'{\
    "recordSetTotal": 977,\
    "resourceName": "categoryview",\
    "resourceId": "qa-api.acme.com/search/resources/store/10151/categoryview/@top?depthAndLimit=-1,-1,-1,-1&catalogId=12003",\
    "recordSetStartNumber": 0,\
    "recordSetComplete": "true",\
    "catalogGroupView":\
    [\
        {\
            "shortDescription": "For Home",\
            "sequence": "12003_-1_1.00000",\
            "resourceId": "15071",\
            "identifier": "Home"\
        },\
        {\
            "shortDescription": "Accessories",\
            "sequence": "12003_-1_7.00000",\
            "resourceId": "15068",\
            "identifier": "Handbags & Acc"\
        },\
        {\
            "shortDescription": "Juniors",\
            "sequence": "12003_-1_3.00000",\
            "resourceId": "15064",\
            "identifier": "Juniors & Guys"\
        },\
        {\
            "shortDescription": "Kids",\
            "sequence": "12003_-1_4.00000",\
            "resourceId": "15065",\
            "identifier": "Kids"\
        }\
    ]\
}'

//body = new File('C:/ProjectsGit/API/node-api-container/routes/bealls.json').text

/*******************************************************************************
 * API Platform Call 
 * Getting the request body 
 ******************************************************************************/
//body = context.clientRequest.getBodyAsType(String.class)

// Convert to Groovy Object
def slurper = new groovy.json.JsonSlurper()
def messageBody = (Map) slurper.parseText(body)
println "*** Original Message ***"
//println messageBody
println ""

//catalogGroupView = messageBody['catalogGroupView']
//catalogGroupView.sort{ a, b -> a.sequence <=> b.sequence}

messageBody['catalogGroupView'] = messageBody['catalogGroupView'].sort{ a, b -> a['sequence'] <=> b['sequence']}

println "*** Sorted Message ***"
println messageBody
println ""

println "\n *** ${timestamp} End Request *** \n"
