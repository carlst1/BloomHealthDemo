package bloomhealthdemo



import org.junit.*
import grails.test.mixin.*

@TestFor(ZipCodeController)
@Mock(ZipCode)
class ZipCodeControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/zipCode/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.zipCodeInstanceList.size() == 0
        assert model.zipCodeInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.zipCodeInstance != null
    }

    void testCreateZip() {
        def myController = new ZipCodeController()
        myController.zipCodeDataService = new ZipCodeDataService()
        myController.createZip()

        assert ZipCode.count != 0
    }



}
