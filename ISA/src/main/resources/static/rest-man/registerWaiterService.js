(function(){
	'use strict';
	
	angular
	.module('app')
	.factory("RegisterWaiterService",RegisterWaiterService);
	
	RegisterWaiterService.$inject = ['$http'];

	function RegisterWaiterService($http){
		var service = {};
		service.CreateWaiter = CreateWaiter;
		service.CheckEmail = CheckEmail;
		return service;
		
		function CreateWaiter(user){
			user.role = {
				      "id": 2,
				      "name": "WAITER"
			};
            return $http.put('/waiter', user)
            .then(
            	function(data){
            	});

		}
		
		function CheckEmail(email){
			return $http.post('/waiter/email',email);

		}
	}
}())