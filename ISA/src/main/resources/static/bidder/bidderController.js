(function(){
	'use strict';
	
	angular
	.module('app')
	.controller('BidderController',BidderController);
	
	BidderController.$inject = ['$location','$rootScope','BidderService','AuthenticationService','$timeout'];
	
	function BidderController($location,$rootScope,BidderService,AuthenticationService,$timeout){
		var vm = this;
		
		vm.drugoSelected = false;//izmeni posle kad provalis sta ce biti drugi deo 
		vm.profileSelected = false;
		//vm.selectedPerson = undefined;
		vm.update = false;
		vm.loggedUser = undefined;//logovani korisnik
		vm.successUpdate = false;
		vm.updateAccount = updateAccount;
		vm.saveChanges = saveChanges;
		vm.getLoggedUser = getLoggedUser;
		vm.logout = logout;
		vm.showProfile = showProfile;
	
		vm.getLoggedUser();
		
		function getLoggedUser(){
			BidderService.getLoggedUser()
			.then(function(user){
				vm.loggedUser = user.data;
				vm.update = false;
			},function(httpData){
				vm.loggedUser = undefined;
				console.log(httpData.data.message);
			});
		}		
		function logout(){
			BidderService.logout()
			.then(function(data){
				$rootScope.globals.currentUser = [];
				$location.path('/')
			},
			function(httpData){
				console.log(httpData.data.message);
				
			})
		}
		function showProfile(){
			vm.profileSelected = true;		
        	$location.path('/bidder/profile');
        	
		}
		function updateAccount(){
			vm.update = true;
		}
		function saveChanges(){
			vm.update = false;
			BidderService.updateBidder(vm.loggedUser)
			.then(function(response){
				vm.successUpdate = true; 
		         $timeout(function () { vm.successUpdate = false; }, 3000); 
				AuthenticationService.setCredentials(vm.loggedUser.email,vm.loggedUser.password);
			},
			function(errorResponse){
				vm.successUpdate = false;
			})
		}
		
}
})();		
		
		
		
		
		
		
		
		
		