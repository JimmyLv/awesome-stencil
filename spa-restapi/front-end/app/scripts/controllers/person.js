angular.module('App.controllers').

  /* Person controller */
  controller('personController', function($scope, $routeParams, personAPIservice) {
    $scope.id = $routeParams.id;
    $scope.companies = [];
    $scope.person = null;

    personAPIservice.getPersonById($scope.id).success(function (response) {
      $scope.person = response;
      $scope.companies = response.companies;
    });

  });