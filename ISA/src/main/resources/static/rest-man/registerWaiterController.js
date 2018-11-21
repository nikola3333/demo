(function () {
    'use strict';

    angular
        .module('app')
        .controller('RegisterWaiterController',RegisterWaiterController);
    
    RegisterWaiterController.$inject = ['RegisterWaiterService','$location'];
    function RegisterWaiterController(RegisterWaiterService,$location) {
        var vm = this;
        
        vm.register = register;
        vm.checkIfEmailExist = checkIfEmailExist;
        vm.setEmailExist = setEmailExist;
        vm.emailExist = false;
        
        function register(){
        	RegisterWaiterService.CreateWaiter(vm.user)
        	.then(function(response){
        		alert("alert");
        	},
        	function(errorResponse){
        		vm.errorMessage = "User with that email already exists"
        	});
        }
        
        function checkIfEmailExist(){
        	RegisterWaiterService.CheckEmail(vm.user.email)
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