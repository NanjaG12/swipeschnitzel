(function() {

  var app = angular.module("nfc");

  var GamesController = function($scope, $interval, parse) {
    
    $scope.deleteGame = function(){
      if (confirm('Bist du dir sicher das du das Spiel löschen möchtest (Alle Benutzer und gesacennten Tags werden gelöscht)?')) {
     
      }
    }
    
    parse.getTags(function(results){
      $scope.nfcTags = results;
       $interval(function(){
        parse.getNfcStamps(function(results) {
          $scope.$apply(function(){
          $scope.stamps = results; 
          if($scope.stamps.length >= $scope.nfcTags.length){
            var time  = $scope.stamps[0].createdAt
            
            if($scope.stamps.length >= 2){
              for (index = 1; index < $scope.stamps.length; ++index) {
                time = $scope.stamps[index].createdAt-time;
              }
            }
            $scope.target = time/1000;
          }
          });
        });
        }, 1000);
      
    });
      
    
    
    
    
  };
  
  


  app.controller("gamesCtrl", GamesController);

}());