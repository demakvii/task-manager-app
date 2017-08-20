var home = angular.module('Home');

home.controller('PassCtrl', ['$scope','$cookieStore','$location','AuthenticationService', function ($scope,$cookieStore,$location,AuthenticationService) {
    var owner = $cookieStore.get('activeUser');
    if (owner != null) {
        $scope.activeUser = {
            userName: owner.activeUser.userName,
            password: owner.activeUser.password
        };

    } else $location.path('/login');

    $scope.updatePass = function(){
        if($scope.oldpass==$scope.activeUser.password){
            $scope.activeUser.password = $scope.newpass;
            AuthenticationService.changePassword($scope.activeUser,function(response){
                             $cookieStore.remove('activeUser');
                             $location.path('/login');

            },function(response){$location.path('/login')});
        }else{
            $scope.alertOld = "Wrong Password!"
        }
    }

}]);


home.controller('LogoutCtrl',['$cookieStore','$location','AuthenticationService',function($cookieStore,$location,AuthenticationService){

if($cookieStore.get('activeUser')!=null)
            $cookieStore.remove('activeUser');

        AuthenticationService.logout(function(response) {
            if(response.data.success)
            $location.path('/login');
            else $location.path('/login');
        }, function(response) {
            $location.path('/login');
        });
    
}]);

home.controller('DeleteSubTaskCtrl', ['$scope', '$location', '$cookieStore', 'AuthenticationService', 'TaskManagerService', '$routeParams', function($scope, $location, $cookieStore, AuthenticationService, TaskManagerService, $routeParams) {
    var owner = $cookieStore.get('activeUser');
    if (owner != null) {
        $scope.activeUser = {
            userName: owner.activeUser.userName,
            password: owner.activeUser.password
        };

    } else $location.path('/login');
    
    TaskManagerService.deleteSubTaskById($routeParams.taskId, $routeParams.subTaskId, function(response) {
        if (response.data.success) {
            $location.path('/manageTask');
        } 
        else $location.path('/logout');
    }, function(response) {});

}])

home.controller('EditSubTaskCtrl', ['$scope', '$location', '$cookieStore', 'AuthenticationService', 'TaskManagerService', '$routeParams', function($scope, $location, $cookieStore, AuthenticationService, TaskManagerService, $routeParams) {
    var owner = $cookieStore.get('activeUser');
    if (owner != null) {
        $scope.activeUser = {
            userName: owner.activeUser.userName,
            password: owner.activeUser.password
        };

    } else $location.path('/login');

    TaskManagerService.getSubTaskById($routeParams.taskId, $routeParams.subTaskId, function(response) {
        if (response.data.success) {
            $scope.editSubTask = response.data.data;
            $scope.editSubTask.startDate = new Date(response.data.data.startDate);
            $scope.editSubTask.endDate = new Date(response.data.data.endDate);
        }else $location.path('/logout');
    }, function(response) {$location.path('/logout');});

    $scope.updateSubTask = function() {
        TaskManagerService.updateSubTask($routeParams.taskId, $scope.editSubTask, function(response) {
            if (response.data.success) {
                $location.path('/manageTask');
            }else $location.path('/logout');
        }, function(response) {$location.path('/logout');});
    }
   

}]);

home.controller('DeleteTaskCtrl', ['$scope', '$location', '$cookieStore', 'AuthenticationService', 'TaskManagerService', '$routeParams', function($scope, $location, $cookieStore, AuthenticationService, TaskManagerService, $routeParams) {
    var owner = $cookieStore.get('activeUser');
    if (owner != null) {
        $scope.activeUser = {
            userName: owner.activeUser.userName,
            password: owner.activeUser.password
        };

    } else $location.path('/login');
    TaskManagerService.deleteTaskById($routeParams.taskId, function(response) {
        if (response.data.success) {
            console.log(response.data);
            $location.path('/manageTask');
        }else $location.path('/logout');
    }, function(response) {});


}]);

home.controller('EditTaskCtrl', ['$scope', '$location', '$cookieStore', 'AuthenticationService', 'TaskManagerService', '$routeParams', function($scope, $location, $cookieStore, AuthenticationService, TaskManagerService, $routeParams) {
    var owner = $cookieStore.get('activeUser');
    if (owner != null) {
        $scope.activeUser = {
            userName: owner.activeUser.userName,
            password: owner.activeUser.password
        };

    } else $location.path('/login');
    $scope.updateTask = function() {
        TaskManagerService.updateTask($scope.editTask, function(response) {
            console.log(response.data);
            if (response.data.success) {
                $location.path('/manageTask');
            }else $location.path('/logout');
        }, function(response) {});
    }

    TaskManagerService.getTaskById($routeParams.taskId, function(response) {
        if (response.data.success) {
            $scope.editTask = response.data.data;
            $scope.editTask.startDate = new Date(response.data.data.startDate);
            $scope.editTask.endDate = new Date(response.data.data.endDate);

        }else $location.path('/logout');

    }, function(response) {});
 
}]);

home.controller('MainCtrl', ['$scope', '$location', '$cookieStore', 'AuthenticationService', function($scope, $location, $cookieStore, AuthenticationService) {


    var owner = $cookieStore.get('activeUser');
    if (owner != null) {
        $scope.activeUser = {
            userName: owner.activeUser.userName,
            password: owner.activeUser.password
        };

    } else $location.path('/login');
 
}]);

home.controller('TaskCtrl', ['$route', '$scope', '$location', '$cookieStore', 'AuthenticationService', 'TaskManagerService', '$routeParams', function($route, $scope, $location, $cookieStore, AuthenticationService, TaskManagerService, $routeParams) {

    var owner = $cookieStore.get('activeUser');
    if (owner != null) {
        $scope.activeUser = {
            userName:

                owner.activeUser.userName,
            password:

                owner.activeUser.password
        };

        TaskManagerService.getTaskList(function(response) {
             if (response.data.success){
            $scope.taskList = response.data.data;
            }
        
else $location.path('/logout');

        }, function(response) {});
    } else $location.path('/login');


    

    $scope.addTask = function() {
        TaskManagerService.addTask($scope.newTask, function(response) {
            if (response.data.success) {
                $location.path('/manageTask');
            }else $location.path('/logout');


        }, function(response) {});
    }

    $scope.addSubTask = function() {
        TaskManagerService.addSubTask($routeParams.taskId, $scope.newSubTask, function(response) {
            if (response.data.success) {
                $location.path('/manageTask');
            }else $location.path('/logout');


        }, function(response) {});
    }
    $scope.updateTask = function(task) {
        TaskManagerService.updateTask(task, function(response) {
            console.log(response.data);
            if (response.data.success) {
                $location.path('/manageTask');
            }else $location.path('/logout');

        }, function(response) {});
    }
    $scope.updateSubTask = function(taskId, subtask) {
        TaskManagerService.updateSubTask(taskId, subtask, function(response) {
            if (response.data.success) {
                $location.path('/manageTask');
            }else $location.path('/logout');

        }, function(response) {});
    }

    $scope.hideSub = function(taskId,index){
         $('.'+taskId).toggle(500);
        if($('#'+index).hasClass("glyphicon glyphicon-chevron-right")){
            $('#'+index).removeClass("glyphicon glyphicon-chevron-right");
            $('#'+index).addClass('glyphicon glyphicon-chevron-down');
        }else
if($('#'+index).hasClass("glyphicon glyphicon-chevron-down"))
        {
            $('#'+index).removeClass('glyphicon glyphicon-chevron-down');

            $('#'+index).addClass("glyphicon glyphicon-chevron-right");
        }
       
    }
  
}]);
