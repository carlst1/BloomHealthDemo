package bloomhealthdemo



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(ZipCode)
class ZipCodeTests {

    void testCreatingZipCodeFailConstraints() {
        def zc = new ZipCode();
        zc.save()
        assert zc.hasErrors()
    }

    void testCreatingZipCodeSuccess(){
        def zc = new ZipCode(postalCode: "74833", stateCode: "MA", stateName: "Massachusetts", cityName: "Boston", countyName: "none")
        def result = zc.save()
        assert !result.hasErrors()
        assert ZipCode.count == 1
    }


}
