(function () {
    'use strict';

    angular
        .module('app')
        .controller('RegisterResControler', RegisterResControler);
    
    RegisterResControler.$inject = ['RegisterResService','$location'];
    function RegisterResControler(RegisterResService,$location) {
        var vm = this;
        
        vm.register = register;
        
        function register(){
        	RegisterResService.CreateRes(vm.restaurant)
        	.then(function(response){
        		alert("done");
        	}
        	);
    
        }
        
        
    }

})();
