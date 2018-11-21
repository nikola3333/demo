(function(){
	'use strict';
	
	angular
	.module('app')
	.factory("RegisterService",RegisterService);
	
	RegisterService.$inject = ['$http'];

	function RegisterService($http){
		var service = {};
		service.CreateGuest = CreateGuest;
		service.CheckEmail = CheckEmail;
		return service;
		
		function CreateGuest(user){
			user.role = {
				      "id": 1,
				      "name": "GUEST"
			};
            return $http.put('/guests', user)
            .then(
            	function(data){
            	});

		}
		
		function CheckEmail(email){
			return $http.post('/guests/email',email);

		}
	}
}())