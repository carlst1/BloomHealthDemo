package bloomhealthdemo

class ZipCodeDataService {

    def loadZipData() {

        def zips = new XmlSlurper().parse("web-app/postalCodeSearch.xml")

        zips.code.each {
            def code = new ZipCode()
            code.adminCode = it.adminCode2
            code.latitude = it.lat
            code.longitude = it.lng
            code.stateCode = it.adminCode1
            code.stateName = it.adminName1
            code.postalCode = it.postalcode
            code.countyName = it.adminName2
            code.cityName = it.name
            if(code.save(flush: true) == null)
            {
                println "Failed to save "+ code.errors
            }
        }
    }
}

