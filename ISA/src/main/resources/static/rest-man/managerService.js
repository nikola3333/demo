(function(){
	'use strict'
	
	angular
	.module('app')
	.factory("ManagerService",ManagerService);
	
	ManagerService.$inject = ['$http'];
	
	function ManagerService($http){
		
		var service = {};
		
	
		service.getAllRestaurants = getAllRestaurants;
		service.searchRestaurants = searchRestaurants;
		service.addRestaurantToSession = addRestaurantToSession;
		return service;
	
		function getAllRestaurants(sortCriteria){
			return $http.get('/restaurants/find/'+sortCriteria);
		}
		function searchRestaurants(condition,sortCriteria){
			return $http.get('/restaurants/find/'+sortCriteria+'/'+condition)
		}
		function addRestaurantToSession(id){
			return $http.post('/restaurants/session/'+id);
		}
	}
})();