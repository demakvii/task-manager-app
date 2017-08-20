var authentication = angular.module('Authentication');

authentication.controller('LoginCtrl', ['$scope','$cookieStore', 'AuthenticationService', '$location', function($scope,$cookieStore ,AuthenticationService, $location) {

    $scope.login = function() {

        AuthenticationService.authenticate($scope.user, function(response) {
            if (response.data.success) {
                 $scope.user = {activeUser : $scope.user};
                 $cookieStore.put('activeUser',$scope.user);   
                $location.path('/home');
            } else {
                $scope.error = true;
                $scope.errorMsg = "Username or password is incorrect";
            }
        });

    }
}]);
