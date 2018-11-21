(function(){
	'use strict'
	
	angular
	.module('app')
	.controller('OrderController',OrderController);
	
	OrderController.$inject = ['$location','$rootScope','OrderService','AuthenticationService','$timeout','ReservationService'];
	function OrderController($location,$rootScope,OrderService,AuthenticationService,$timeout,ReservationService){
		var vm = this;
		vm.reservation = {};
		vm.restaurant = {};
		vm.guest = {};
		vm.orders = {};

		
		vm.getReservation = getReservation;
		vm.getRestaurant = getRestaurant;
		vm.getGuest = getGuest;
		vm.addFood = addFood;
		vm.addDrink = addDrink;
		vm.findOrders = findOrders;
		vm.deleteDrink = deleteDrink;
		vm.deleteFood = deleteFood;
		
		vm.getReservation();
		function getReservation(){
			OrderService.getReservation()
			.then(function(httpData){
				vm.reservation = httpData.data;
				vm.getRestaurant();

			},
			function(httpData){
				console.log(httpData.data.message);
			})
		}
		
		function getRestaurant(){
			OrderService.getRestaurant()
			.then(function(httpData){
				vm.restaurant = httpData.data;
				vm.getGuest();

			},
			function(httpData){
				console.log(httpData.data.message);
			})
		}
		
		function getGuest(){
			OrderService.getGuest()
			.then(function(httpData){
				vm.guest = httpData.data;
				vm.findOrders();
			},
			function(httpData){
				console.log(httpData.data.message);
			})
		}
		
		function addFood(index){
			OrderService.addFood(vm.restaurant.menu[index])
			.then(function(httpData){
				vm.reservation = httpData.data;
				vm.findOrders()
			},
			function(httpData){
				console.log(httpData.data.message);
			})
		}
		
		function addDrink(index){
			OrderService.addDrink(vm.restaurant.drinks[index])
			.then(function(httpData){
				vm.reservation = httpData.data;
				vm.findOrders();
			},
			function(httpData){
				console.log(httpData.data.message);
			})			
		}
		
		function findOrders(){
			for(var i = 0; i < vm.reservation.orders.length;i++){
				var order = vm.reservation.orders[i];
				if(vm.guest.id ==vm.reservation.orders[i].guest.id){
					vm.orders = vm.reservation.orders[i];
					return;
				}
			}
		}
		
		function deleteDrink(index){
			OrderService.deleteDrink(vm.orders.drinks[index])
			.then(function(httpData){
				vm.reservation = httpData.data;
				vm.findOrders();
			},
			function(httpData){
				console.log(httpData.data.message);
			})			
		}
		
		function deleteFood(index){
			OrderService.deleteFood(vm.orders.foodstuffs[index])
			.then(function(httpData){
				vm.reservation = httpData.data;
				vm.findOrders();
			},
			function(httpData){
				console.log(httpData.data.message);
			})				
		}
	}
})();