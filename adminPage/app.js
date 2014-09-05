(function(){
  
  var app = angular.module("nfc", ["ngRoute"]);
  
  
  
  app.config(function($routeProvider, $locationProvider){
    $locationProvider.html5Mode(false);
    $routeProvider
      .when("/games", {
        templateUrl: "views/games.html",
        controller: "gamesCtrl"
      })
      .when("/nfcTags", {
        templateUrl: "views/nfcTags.html",
        controller: "nfcTagsCtrl"
      })
      .otherwise({redirectTo:"/games"});
  });
  
  
}());