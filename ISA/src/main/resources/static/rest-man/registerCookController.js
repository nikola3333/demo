(function () {
    'use strict';

    angular
        .module('app')
        .controller('RegisterCookController',RegisterCookController);
    
    RegisterCookController.$inject = ['RegisterCookService','$location'];
    function RegisterCookController(RegisterCookService,$location) {
        var vm = this;
        
        vm.register = register;
        vm.checkIfEmailExist = checkIfEmailExist;
        vm.setEmailExist = setEmailExist;
        vm.emailExist = false;
        
        function register(){
        	RegisterCookService.CreateCook(vm.user)
        	.then(function(response){
        		alert("alert");
        	},
        	function(errorResponse){
        		vm.errorMessage = "User with that email already exists"
        	});
        }
        
        function checkIfEmailExist(){
        	RegisterCookService.CheckEmail(vm.user.email)
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