(function(){
	'use strict';
	
	angular
	.module('app')
	.factory("RegisterBartenderService",RegisterBartenderService);
	
	RegisterBartenderService.$inject = ['$http'];

	function RegisterBartenderService($http){
		var service = {};
		service.CreateBartender = CreateBartender;
		service.CheckEmail = CheckEmail;
		return service;
		
		function CreateBartender(user){
			user.role = {
				      "id": 4,
				      "name": "BARTENDER"
			};
            return $http.put('/bartender', user)
            .then(
            	function(data){
            	});

		}
		
		function CheckEmail(email){
			return $http.post('/bartender/email',email);

		}
	}
}())