(function() {

  var app = angular.module(["ngRoute"]);

  var navBarCtrl = function($scope, $location) {
        $scope.isActive = function (viewLocation){
            return viewLocation === $location.path();
        };
    };
    
  
  app.controller("navBarCtrl", navBarCtrl);

}());