(function(){
	'use strict'
	
	angular
	.module('app')
	.factory("ReservationService",ReservationService);
	
	ReservationService.$inject = ['$http'];
	
	function ReservationService($http){
		var service = {};
		service.getSelectedRestaurant = getSelectedRestaurant;
		service.sendDate = sendDate;
		service.reloadStatuses = reloadStatuses;
		service.confirmReservation = confirmReservation;
		service.inviteFriend = inviteFriend;
		service.getReservation = getReservation;
		service.acceptInvitation = acceptInvitation;
		service.declineInvitation = declineInvitation;
		service.getRestaurantOfReservation = getRestaurantOfReservation;
		service.setReservation = setReservation;
		return service;
		
		function getSelectedRestaurant(){
			return $http.get('/restaurants/session');
		}
		
		function sendDate(dates){
			return $http.post('/reservations/date',dates);
		}
		
		function reloadStatuses(){
			return $http.get("/reservations/all/");
		}
		
		function confirmReservation(tables){
			return $http.post("/reservations",tables);
		}
		
		function inviteFriend(friend,reservationId){
			return $http.post("/reservations/friend/"+reservationId,friend);
		}
		
		function getReservation(reservationCode,invitedFriendCode){
			return $http.get('reservations/confirmation/'+reservationCode + '/' + invitedFriendCode);
		}
		
		function acceptInvitation(reservationId){
			return $http.post('reservations/invitation/'+reservationId);
		}
		
		function declineInvitation(reservationId){
			return $http.delete('reservations/invitation/'+ reservationId);
		}
		
		function getRestaurantOfReservation(reservationId){
			return $http.get('/reservations/restaurant/'+reservationId);
		}
		
		function setReservation(id){
			return $http.post('/reservations/'+id);
		}
	}
})();