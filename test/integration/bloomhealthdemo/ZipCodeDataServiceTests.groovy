package bloomhealthdemo

import static org.junit.Assert.*
import org.junit.*

class ZipCodeDataServiceTests {

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void testSaveZipData() {

        ZipCodeDataService.metaClass.loadZipData << { ->
            saveZipData(new XmlSlurper().parse("test/integration/postalCodeSearch.xml"))
        }

        def service = new ZipCodeDataService()
        service.loadZipData()
        def num = ZipCode.count()
        println "found: " + num
        assert num == 100
    }
}
