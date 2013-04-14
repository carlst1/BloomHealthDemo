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
        def zc = new ZipCode(postalCode: "74833", stateCode: "MA", stateName: "Massachusetts", cityName: "Boston", countyName: "none")
        def result = zc.save()
        assert !result.hasErrors()
        assert ZipCode.count == 1

        def controller = new ZipCodeController();
        controller.delete()
        assert "/zipCode/list" == controller.response.redirectedUrl
        assert ZipCode.count == 0
    }
}
