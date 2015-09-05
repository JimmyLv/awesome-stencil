angular.module('App.controllers', []);

angular.module('App', [
  'App.services',
  'App.controllers',
  'ngRoute'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.
  when("/about", {templateUrl: "views/about.html", controller: "aboutController"}).
  when("/persons", {templateUrl: "views/persons.html", controller: "personsController"}).
  when("/persons/:id", {templateUrl: "views/person.html", controller: "personController"}).
  otherwise({redirectTo: '/persons'});
}]);