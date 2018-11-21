(function(){
	'use strict';
	
	angular
	.module('app')
	.factory("RegisterCookService",RegisterCookService);
	
	RegisterCookService.$inject = ['$http'];

	function RegisterCookService($http){
		var service = {};
		service.CreateCook = CreateCook;
		service.CheckEmail = CheckEmail;
		return service;
		
		function CreateCook(user){
			user.role = {
				      "id": 5,
				      "name": "COOK"
			};
            return $http.put('/cook', user)
            .then(
            	function(data){
            	});

		}
		
		function CheckEmail(email){
			return $http.post('/cook/email',email);

		}
	}
}())