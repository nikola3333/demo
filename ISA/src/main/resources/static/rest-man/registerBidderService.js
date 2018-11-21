(function(){
	'use strict';
	
	angular
	.module('app')
	.factory("RegisterBidderService",RegisterBidderService);
	
	RegisterBidderService.$inject = ['$http'];

	function RegisterBidderService($http){
		var service = {};
		service.CreateBidder = CreateBidder;
		service.CheckEmail = CheckEmail;
		return service;
		
		function CreateBidder(user){
			user.role = {
				      "id": 7,
				      "name": "BIDDER"
			};
            return $http.put('/bidder', user)
            .then(
            	function(data){
            	});

		}
		
		function CheckEmail(email){
			return $http.post('/bidder/email',email);

		}
	}
}())