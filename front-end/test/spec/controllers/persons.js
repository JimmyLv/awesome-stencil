describe('Controller: personsController', function () {

  // First, we load the app's module
  beforeEach(module('App'));

  // Then we create some variables we're going to use
  var personsController, scope;

  beforeEach(inject(function ($controller, $rootScope, $httpBackend) {

    // Here, we create a mock scope variable, to replace the actual $scope variable the controller would take as parameter
    scope = $rootScope.$new();

    // Then we create an $httpBackend instance. I'll talk about it below.
    httpMock = $httpBackend;

    // Here, we set the httpBackend standard reponse to the URL the controller is supposed to retrieve from the API
    httpMock.expectJSONP("http://ergast.com/api/f1/2013/personStandings.json?callback=JSON_CALLBACK").respond(
      [
        {
          "id": "55eac586d4c6644555ba865f",
          "firstName": "Jimmy",
          "lastName": "lv",
          "country": null,
          "profession": "Photography",
          "location": [
            119,
            -110
          ],
          "companies": [
            {
              "orgName": "ThoughtWorks",
              "headquarter": "chengdu",
              "joinTime": null
            }
          ]
        },
        {
          "id": "55eac591d4c6644555ba8660",
          "firstName": "Kimmy",
          "lastName": "Liu",
          "country": null,
          "profession": "Photography",
          "location": [
            119,
            -110
          ],
          "companies": [
            {
              "orgName": "ThoughtWorks",
              "headquarter": "chengdu",
              "joinTime": null
            }
          ]
        },
        {
          "id": "55ead8a4d4c6d9f89d619a91",
          "firstName": "Alonso",
          "lastName": "Fernando",
          "country": "Spanish",
          "profession": "Racer",
          "location": [
            300,
            -150
          ],
          "companies": [
            {
              "orgName": "Ferrari",
              "headquarter": "Madrid",
              "joinTime": 2015
            },
            {
              "orgName": "Red Bull",
              "headquarter": "Australian",
              "joinTime": 2013
            }
          ]
        }
      ]
    );

    // Here, we actually initialize our controller, passing our new mock scope as parameter
    personsController = $controller('personsController', {
      $scope: scope
    });

    // Then we flush the httpBackend to resolve the fake http call
    httpMock.flush();

  }));


  // Now, for the actual test, let's check if the personsList is actually retrieving the mock person array
  it('should return a list with three persons', function () {
    expect(scope.personsList.length).toBe(3);
  });

  // Let's also make a second test checking if the persons attributes match against the expected values
  it('should retrieve the family names of the persons', function () {
    expect(scope.personsList[0].firstName).toBe("Jimmy");
    expect(scope.personsList[1].firstName).toBe("Kimmy");
    expect(scope.personsList[2].firstName).toBe("Alonso");
  });

});