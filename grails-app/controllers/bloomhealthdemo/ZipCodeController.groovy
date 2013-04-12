package bloomhealthdemo

import org.springframework.dao.DataIntegrityViolationException

class ZipCodeController {

    static allowedMethods = []

    def zipCodeDataService;

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [zipCodeInstanceList: ZipCode.list(params), zipCodeInstanceTotal: ZipCode.count()]
    }

    def create() {
        [zipCodeInstance: new ZipCode(params)]
    }

    def createZip(){
        zipCodeDataService.loadZipData()
    }





}

