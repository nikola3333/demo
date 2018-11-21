(function () {
    'use strict';

    angular
        .module('app')
        .controller('RegisterSysControler', RegisterSysControler);
    
    RegisterSysControler.$inject = ['RegisterSysService','$location'];
    function RegisterSysControler(RegisterSysService,$location) {
        var vm = this;
        
        vm.register = register;
        vm.checkIfEmailExist = checkIfEmailExist;
        vm.setEmailExist = setEmailExist;
        vm.emailExist = false;
        
        function register(){
        	RegisterSysService.CreateSys(vm.user)
        	.then(function(response){
        		alert("done");
        	},
        	function(errorResponse){
        		vm.errorMessage = "User with that email already exists"
        	});
        }
        
        function checkIfEmailExist(){
        	RegisterSysService.CheckEmail(vm.user.email)
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
