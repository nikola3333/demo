(function(){
	'use strict';
	
	angular
	.module('app')
	.factory("RegisterManService",RegisterManService);
	
	RegisterManService.$inject = ['$http'];

	function RegisterManService($http){
		var service = {};
		service.CreateManager = CreateManager;
		service.CheckEmail = CheckEmail;
		return service;
		
		function CreateManager(user){
			user.role = {
				      "id": 6,
				      "name": "RESTAURAN_MANAGER"
			};
            return $http.put('/manager', user)
            .then(
            	function(data){
            	});

		}
		
		function CheckEmail(email){
			return $http.post('/manager/email',email);

		}
	}
}())