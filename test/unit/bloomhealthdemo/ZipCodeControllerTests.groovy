package bloomhealthdemo



import org.junit.*
import grails.test.mixin.*

@TestFor(ZipCodeController)
@Mock(ZipCode)
class ZipCodeControllerTests {

    void testIndex() {
        controller.index()
        assert "/zipCode/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.zipCodeMap.size() == 0
    }

    void testCreateZip() {
        ZipCodeDataService.metaClass.loadAllStates = { ->
            saveZipData(new XmlSlurper().parse("test/integration/postalCodeSearch.xml"))
        }

        def myController = new ZipCodeController()
        myController.zipCodeDataService = new ZipCodeDataService()
        myController.createZip()
        assert "/zipCode/list" == response.redirectedUrl
        assert ZipCode.count == 100
    }

}
