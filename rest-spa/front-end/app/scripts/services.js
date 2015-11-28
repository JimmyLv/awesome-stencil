angular.module('App.services', [])
  .factory('personAPIservice', function($http) {

    var personAPI = {};

    personAPI.getPersons = function() {
      return $http({
        method: 'GET',
        url: 'http://localhost:8080/persons'
      });
    }

    personAPI.getPersonById = function(id) {
      return $http({
        method: 'GET',
        url: 'http://localhost:8080/person/?id=' + id
      });
    }

    personAPI.getPersonByFirstName = function(firstName) {
      return $http({
        method: 'GET',
        url: 'http://localhost:8080/person/?firstName='+ firstName
      });
    }

    personAPI.createNewPerson = function() {
      return $http.post('http://localhost:8080/saveperson', {msg: 'hello word!'}).
        then(function(response) {
          console.log(response);
          // this callback will be called asynchronously
          // when the response is available
        }, function(err) {
          console.log(err)
          // called asynchronously if an error occurs
          // or server returns response with an error status.
        });
    }

    return personAPI;
  });