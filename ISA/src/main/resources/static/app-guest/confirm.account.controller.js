(function(){
	'use strict';
	
	angular
	.module('app')
	.controller('ConfirmAccountController',ConfirmAccountController);
	
	ConfirmAccountController.$inject = ['$location','$rootScope','$route','AuthenticationService','$timeout','GuestService']

	function ConfirmAccountController($location,$rootScope,$route,AuthenticationService,$timeout,GuestService){
		var vm = this;
		(function activateAccount(){
			if(typeof $route.current.params.code1 !== "undefined"){
				vm.code = $route.current.params.code1;
				GuestService.activateAccount(vm.code)
				.then(function(httpData){
					alert("Account activated");

				},
				function(httpData){
					console.log(httpData.data.message)
				})
			}			
		})();		
	}
})();