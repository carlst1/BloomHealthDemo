package bloomhealthdemo

class ZipCode {

    String postalCode
    def latitude
    def longitude
    String stateCode
    String stateName
    def adminCode
    String cityName
    String countyName

    static constraints = {
        postalCode(nullable: false, unique: true)
        adminCode(nullable: true)
        latitude(nullable: true)
        longitude(nullable: true)
    }
}

