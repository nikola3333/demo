(function () {
    'use strict';

    angular
        .module('app')
        .controller('RegisterBartenderController',RegisterBartenderController);
    
    RegisterBartenderController.$inject = ['RegisterBartenderService','$location'];
    function RegisterBartenderController(RegisterBartenderService,$location) {
        var vm = this;
        
        vm.register = register;
        vm.checkIfEmailExist = checkIfEmailExist;
        vm.setEmailExist = setEmailExist;
        vm.emailExist = false;
        
        function register(){
        	RegisterBartenderService.CreateBartender(vm.user)
        	.then(function(response){
        		alert("alert");
        	},
        	function(errorResponse){
        		vm.errorMessage = "User with that email already exists"
        	});
        }
        
        function checkIfEmailExist(){
        	RegisterBartenderService.CheckEmail(vm.user.email)
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