(function(){
	'use strict'
	
	angular
	.module('app')
	.factory("BidderService",BidderService);
	
	BidderService.$inject = ['$http'];
	
	function BidderService($http){
		
		var service = {};
		service.getLoggedUser = getLoggedUser;
		service.logout = logout;
		service.updateBidder = updateBidder;
		
		return service;
		
		function getLoggedUser(){
            return $http.get('/bidder/user');
		}
		
		function logout(){
			return $http.delete('/bidder/user');
		}
		
		function updateBidder(user){
			return $http.post('/bidder',user);
		}
	}
})();
		