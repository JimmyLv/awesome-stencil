angular.module('App.controllers').

  /* Persons controller */
  controller('personsController', function($scope, ergastAPIservice) {
    $scope.nameFilter = null;
    $scope.personsList = [];
    $scope.searchFilter = function (person) {
        var re = new RegExp($scope.nameFilter, 'i');
        return !$scope.nameFilter || re.test(person.firstName) || re.test(person.lastName);
    };

    ergastAPIservice.getPersons().success(function (response) {
        //Digging into the response to get the relevant data
        $scope.personsList = response;
    });
  });