angular.module('Database', []);
angular.module('Authentication', ['Database']);
angular.module('Home', ['Database']);
var taskManagerApp = angular.module('TaskManagerApp', ['Authentication', 'ngRoute', 'Home', 'ngCookies']);


taskManagerApp.config(['$routeProvider', function($routeProvider) {

    $routeProvider
        .when('/login', {
            templateUrl: 'modules/authentication/views/login.html',
            controller: 'LoginCtrl'
        })
        .when('/logout', {
            controller: 'LogoutCtrl',
            templateUrl: 'modules/authentication/views/login.html'


        })
        .when('/home', {
            templateUrl: 'modules/home/views/home.html',
            controller: 'MainCtrl'
        })
        .when('/changePassword',{
            templateUrl:'modules/home/views/changePassword.html',
            controller : 'PassCtrl'
        })
        .when('/manageTask', {
            templateUrl: 'modules/home/views/manageTask.html',
            controller: 'TaskCtrl'
        })
        .when('/addTask', {
            templateUrl: 'modules/home/views/addTask.html',
            controller: 'TaskCtrl'
        })
        .when('/:taskId/addSubTask', {
            templateUrl: 'modules/home/views/addSubTask.html',
            controller: 'TaskCtrl'
        })
        .when('/:taskId/editTask', {
            templateUrl: 'modules/home/views/editTask.html',
            controller: 'EditTaskCtrl'
        })
        .when('/:taskId/deleteTask', {
            templateUrl: 'modules/home/views/manageTask.html',
            controller: 'DeleteTaskCtrl'
        })
        .when('/:taskId/:subTaskId/editSubTask', {
            templateUrl: 'modules/home/views/editSubTask.html',
            controller: 'EditSubTaskCtrl'
        })
        .when('/:taskId/:subTaskId/deleteSubTask', {
            templateUrl: 'modules/home/views/manageTask.html',
            controller: 'DeleteSubTaskCtrl'
        })
        .otherwise({ redirectTo: '/login' });
}]);
