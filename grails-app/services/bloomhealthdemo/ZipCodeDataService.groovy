package bloomhealthdemo

import groovyx.net.http.HTTPBuilder

/**
 * Service for loading Zip Code data into the database.  Note: the web service fails if recordsets exceed 5000,
 * so calls are broken up by state.
 */
class ZipCodeDataService {

    /**
     * Number of records requested for each web service call
     */
    private static int MAX_RECORDS = 500
    /**
     * Max number of errors before giving up
     */
    private static int MAX_ERRORS = 3
    private static String WEB_SERVICE_URL = 'http://api.geonames.org'
    private List states = ["AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS",
                           "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY",
                           "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"]



    /**
     * Loops through each state, retrieves its zip codes and saves them in the database
     * @return
     */
    def loadAllStates(){
        states.each{loadZipDataForState(it)}
    }

    private def loadZipDataForState(String state){
        def http = new HTTPBuilder(WEB_SERVICE_URL)

        def currentRecord = 0
        def totalRecords = 1
        def errorCount = 0

        while(currentRecord < totalRecords)
        {
            http.get( path : '/postalCodeSearch',
                    contentType : "text/xml",
                    query : [placename: state,
                             username:'carlst1',
                             country: 'US',
                             maxRows: MAX_RECORDS,
                             startRow: currentRecord] ) { resp, reader ->


                if(resp.statusLine.statusCode == 200){
                    if(reader.totalResultsCount.text().isInteger())
                    {
                        totalRecords = reader.totalResultsCount.text().toInteger()
                    }
                    currentRecord += saveZipData(reader)
                    println "CurrentRecord: $currentRecord of $totalRecords"
                }
                else{
                    println "Request failed: " + resp.statusLine
                    errorCount++
                    if(errorCount > MAX_ERRORS){
                        throw new Exception("Web service connection failed: " + resp.statusLine)
                    }
                }
            }
        }
    }

    /**
     * Use the XML to create ZipCode objects and persist them.
     * @param zips: XML slurper containing the zip code information
     * @return Number of records processed
     */
    private int saveZipData(def zips){

        def codeCount = 0
        zips.code.each {

                def code = new ZipCode(
                        adminCode: it.adminCode2,
                        latitude: it.lat,
                        longitude: it.lng,
                        stateCode: it.adminCode1.text(),
                        stateName: it.adminName1.text(),
                        postalCode: it.postalcode.text(),
                        countyName: it.adminName2.text(),
                        cityName: it.name.text() )

                if(code.save() == null)
                {
                    println "Failed to save "+ code.errors
                }
            codeCount++;

        }

        return codeCount
    }



}

