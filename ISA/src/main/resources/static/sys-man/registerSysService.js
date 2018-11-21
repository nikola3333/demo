(function(){
	'use strict';
	
	angular
	.module('app')
	.factory("RegisterSysService",RegisterSysService);
	
	RegisterSysService.$inject = ['$http'];

	function RegisterSysService($http){
		var service = {};
		service.CreateSys = CreateSys;
		service.CheckEmail = CheckEmail;
		return service;
		
		function CreateSys(user){
			user.role = {
				      "id": 3,
				      "name": "SYSTEM_MANAGER"
			};
            return $http.put('/sysmanager', user)
            .then(
            	function(data){
            	});

		}
		
		function CheckEmail(email){
			return $http.post('/sysmanager/email',email);

		}
	}
}())