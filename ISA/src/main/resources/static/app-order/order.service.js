(function(){
	'use strict'
	
	angular
	.module('app')
	.factory("OrderService",OrderService);
	
	OrderService.$inject = ['$http'];	
	
	function OrderService ($http){
		var service = {};
		service.getReservation = getReservation;
		service.getRestaurant = getRestaurant;
		service.getGuest = getGuest;
		service.addDrink = addDrink;
		service.addFood = addFood;
		service.deleteDrink = deleteDrink;
		service.deleteFood = deleteFood;
		return service;
		
		function getReservation(){
			return $http.get('/reservations')
		}
		
		function getRestaurant(){
			return $http.get('/restaurants/session');
		}
		
		function addDrink(drink){
			return $http.post('/reservations/orders/drink',drink);
		}

		function addFood(food){
			return $http.post('/reservations/orders/food',food);
		}
		
		function getGuest(){
			return $http.get('/reservations/orders/guest');
		}
		
		function deleteDrink(drink){
			return $http.delete('/reservations/orders/drink/'+drink.id);
		}
		
		function deleteFood(food){
			return $http.delete('/reservations/orders/food/'+food.id)
		}
	}
	
})();