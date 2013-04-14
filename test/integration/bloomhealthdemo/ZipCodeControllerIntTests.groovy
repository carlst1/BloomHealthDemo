package bloomhealthdemo

import static org.junit.Assert.*
import org.junit.*

class ZipCodeControllerIntTests {

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void testDelete(){
        def controller = new ZipCodeController();
        new ZipCodeDataService().loadZipData()
        assert ZipCode.count != 0
        controller.delete()
        assert "/zipCode/list" == controller.response.redirectedUrl
        assert ZipCode.count == 0
    }
}
