(function () {
    'use strict';

    angular
        .module('app')
        .controller('RegisterController', RegisterController);
    
    RegisterController.$inject = ['RegisterService','$location'];
    function RegisterController(RegisterService,$location) {
        var vm = this;
        
        vm.register = register;
        vm.checkIfEmailExist = checkIfEmailExist;
        vm.setEmailExist = setEmailExist;
        vm.emailExist = false;
        
        function register(){
        	RegisterService.CreateGuest(vm.user)
        	.then(function(response){
				$location.path('/');
        	},
        	function(errorResponse){
        		vm.errorMessage = "User with that email already exists"
        	});
        }
        
        function checkIfEmailExist(){
        	RegisterService.CheckEmail(vm.user.email)
        	.then(
        			function(response){
        				if(reponse.data != null)
        					vm.emailExist = true;
        				else
        					vm.emailExist = false;
        			},
        			function(errorResponse){
        				vm.emailExist = false;
        			});
        }
        
        function setEmailExist(){
        	vm.emailExist = false;
        }
        
    }

})();
