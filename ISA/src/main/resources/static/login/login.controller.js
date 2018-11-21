(function () {
    'use strict';

    angular
        .module('app')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$location', 'AuthenticationService'];
    function LoginController($location, AuthenticationService) {
    	//this = $scope because of  controller as 
        var vm = this;

        vm.clearErrorMessage = clearErrorMessage;
        vm.login = login;
        vm.errorMessage = undefined;
        
        (function initController() {
            // reset login status
            AuthenticationService.ClearCredentials();
        })();

        function login() {
            AuthenticationService.Login(vm.email, vm.password, function (response) {
                if (response.status == 200) {
                    AuthenticationService.SetCredentials(vm.email, vm.password);
                    if(response.data.role.name == "GUEST"){
                    	$location.path('/homePage');
                    }
                    if(response.data.role.name == "WAITER"){
                    	$location.path('/waiter');
                    }
                    if(response.data.role.name == "BARTENDER"){
                    	$location.path('/bartender')
                    }
                    if(response.data.role.name == "BIDDER"){
                    	$location.path('/bidder')
                    }
                    if(response.data.role.name == "COOK"){
                    	$location.path('/cook')
                    }
                    if(response.data.role.name == "RESTAURAN_MANAGER"){
                    	$location.path('/manager')
                    }

                    if(response.data.role.name == "SYSTEM_MANAGER"){
                        $location.path('/sysmanager');
                           }
                }
                else {
                	vm.errorMessage = response.data.message;
                }
            });
        };
        
        function clearErrorMessage(){
        	vm.errorMessage = undefined;
        }
    }


})();
