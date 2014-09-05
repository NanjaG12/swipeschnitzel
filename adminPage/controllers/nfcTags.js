(function() {

  var app = angular.module("nfc");

  var nfcTagsController = function($scope, parse) {
    parse.getTags(function(results) {
      $scope.$apply(function(){
        $scope.nfcTags = results; 
      });
    });
  };
  
  app.controller("nfcTagsCtrl", nfcTagsController);

}());