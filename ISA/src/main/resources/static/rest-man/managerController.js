(function(){
	'use strict';
	
	angular
	.module('app')
	.controller('ManagerController',ManagerController);
	
	ManagerController.$inject = ['$location','$rootScope','ManagerService','AuthenticationService','$timeout'];
	
	function ManagerController($location,$rootScope,ManagerService,AuthenticationService,$timeout){
		var vm = this;
		
		vm.selectedPerson = undefined;
				
		vm.searchCondition = "";
		vm.restaurants = [];
		vm.selectedRestaurant = undefined;
		vm.selectedRestaurantIndex = undefined;
		vm.searchRestaurantsCondition = "";
		vm.criterias = [{id : "1", name : "Sort by"},{id:"2",name:"Name"},{id:"3",name:"Type"}]
		vm.sortRestaurantsCriteria = {id : "1", name : "Sort by"}
	

		
	//restaurants
		vm.getAllRestaurants = getAllRestaurants;
		vm.setSelectedRestaurant = setSelectedRestaurant;
		vm.searchRestaurants = searchRestaurants;
		vm.sortCriteriaChanged = sortCriteriaChanged;
		
 		
		
		
			
		
		
		function getAllRestaurants(){
			ManagerService.getAllRestaurants(vm.sortRestaurantsCriteria.name)
			.then(function(httpData){
				vm.restaurants = httpData.data;
			},
			function(httpData){
				console.log(httpData.data.message);
			})
		}
		function setSelectedRestaurant(index){
			vm.selectedRestaurant = vm.restaurants[index];
			vm.selectedRestaurantIndex = index;			
		}
		function searchRestaurants(){
			if(vm.searchRestaurantsCondition.trim() != "")
				ManagerService.searchRestaurants(vm.searchRestaurantsCondition,vm.sortRestaurantsCriteria.name)
				.then(function(httpData){
					vm.restaurants = httpData.data;
				},
				function(httpData){
					console.log(httpData.data.message);
				})
			else{
				vm.getAllRestaurants();
			}
		}
		function sortCriteriaChanged(value){
			vm.sortRestaurantsCriteria = value;
			searchRestaurants();
		}
		
	}
})();