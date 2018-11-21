(function(){
	'use strict';
	
	angular
	.module('app')
	.factory("RegisterResService",RegisterResService);
	
	RegisterResService.$inject = ['$http'];

	function RegisterResService($http){
		var service = {};
		service.CreateRes = CreateRes;
		return service;
		
		function CreateRes(restaurant){
            return $http.put('/restaurants', restaurant)
          

		}
		
	}
}())