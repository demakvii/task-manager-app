var home = angular.module('Home');

home.directive('homeNavbar',function(){

	return {
		restrict : 'E',
		replace: 'false',
		templateUrl : 'modules/home/templates/homeNavBar.html'
	}
});
