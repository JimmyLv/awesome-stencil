angular.module('App.controllers').

  /* Person controller */
  controller('personController', function($scope, $routeParams, ergastAPIservice) {
    $scope.id = $routeParams.id;
    $scope.companies = [];
    $scope.person = null;

    ergastAPIservice.getPersonById($scope.id).success(function (response) {
      $scope.person = response;
      $scope.companies = response.companies;
    });

  });