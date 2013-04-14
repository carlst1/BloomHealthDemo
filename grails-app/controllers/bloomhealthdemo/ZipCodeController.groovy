package bloomhealthdemo

class ZipCodeController {

    static allowedMethods = []

    def zipCodeDataService;

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        // Get the count of each state's zip codes and then multiply it by the weighting factor which produces
        // a range of 8-24pt font
        def zipCodes = ZipCode.list().groupBy{it.stateName}.each{it.value = (it.value.size() * 0.01)}.sort()
        [zipCodeMap: zipCodes]
    }

    def delete() {
        ZipCode.executeUpdate("delete from ZipCode")
        redirect(action: "list", params: params)
    }


    def createZip(){
        zipCodeDataService.loadAllStates()
        redirect(action: "list", params: params)
    }

}

