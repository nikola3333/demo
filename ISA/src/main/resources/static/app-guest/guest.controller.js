(function(){
	'use strict';
	
	angular
	.module('app')
	.controller('GuestController',GuestController);
	
	GuestController.$inject = ['$location','$rootScope','GuestService','AuthenticationService','$timeout'];
	
	function GuestController($location,$rootScope,GuestService,AuthenticationService,$timeout){
		var vm = this;
		vm.homeSelected = false;
		vm.restaurantsSelected = false;
		vm.friendsSelected = false;
		vm.profileSelected = false;
		vm.selectedPerson = undefined;
		vm.update = false;
		vm.loggedUser = undefined;//logovani korisnik
		vm.successUpdate = false;
		vm.noneFriends =[];//lista osoba koje nisu u listi prijatelja
		vm.friends = [];
		vm.friendRequests = [];
		vm.searchCondition = "";
		vm.restaurants = [];
		vm.selectedRestaurant = undefined;
		vm.selectedRestaurantIndex = undefined;
		vm.searchRestaurantsCondition = "";
		vm.criterias = [{id : "1", name : "Sort by"},{id:"2",name:"Name"},{id:"3",name:"Type"}]
		vm.sortRestaurantsCriteria = {id : "1", name : "Sort by"}
		vm.reservations = [];
		vm.restaurantsReservations = [];
		vm.history = [];
		
		vm.showHome = showHome;
		vm.showRestaurants = showRestaurants;
		vm.showFriends = showFriends;
		vm.showProfile = showProfile;
		vm.showSelectedPerson = showSelectedPerson;
		vm.showSelectedPersonRequest = showSelectedPersonRequest;
		vm.sendFriendRequest = sendFriendRequest;
		vm.updateAccount = updateAccount;
		vm.saveChanges = saveChanges;
		vm.getLoggedUser = getLoggedUser;
		vm.logout = logout;
		//vm.getNoneFriends = getNoneFriends;
		//vm.getFriends = getFriends;
		//vm.getFriendRequests = getFriendRequests;
	//friends
		vm.removeFromFriendsList = removeFromFriendsList;
		vm.acceptFriendRequest = acceptFriendRequest;
		vm.declineFriendRequest = declineFriendRequest;
		vm.searchNoneFriends = searchNoneFriends;
		vm.searchFriends = searchFriends;
		vm.searchFriendRequests = searchFriendRequests;
	//restaurants
		vm.getAllRestaurants = getAllRestaurants;
		vm.setSelectedRestaurant = setSelectedRestaurant;
		vm.searchRestaurants = searchRestaurants;
		vm.sortCriteriaChanged = sortCriteriaChanged;
		vm.reserve = reserve;
		vm.loadReservations = loadReservations;
		vm.getDate = getDate;
		vm.getRestaurantOfReservation = getRestaurantOfReservation;
		vm.getRestOfRes = getRestOfRes;
		vm.getRestaurant = getRestaurant;
 		vm.getLoggedUser();
 		vm.addToSession = addToSession;
 		vm.cancelReservation = cancelReservation;
 		vm.loadHistory = loadHistory;
 		vm.getRestOfResHistory = getRestOfResHistory;
 		vm.getRestaurantOfReservationHistory = getRestaurantOfReservationHistory;
		vm.loadReservations();
		vm.loadHistory();
		//nalazim logovanog gosta
		function getLoggedUser(){
			GuestService.getLoggedUser()
			.then(function(user){
				vm.loggedUser = user.data;
				vm.update = false;
			},function(httpData){
				vm.loggedUser = undefined;
				console.log(httpData.data.message);
			});
		}		
		function logout(){
			GuestService.logout()
			.then(function(data){
				$rootScope.globals.currentUser = [];
				$location.path('/')
			},
			function(httpData){
				console.log(httpData.data.message);
				
			})
		}
		function showRestaurants(){
			vm.homeSelected = false;
			vm.restaurantsSelected = true;
			vm.friendsSelected = false;
			vm.profileSelected = false;
        	$location.path('/homePage/restaurants');

		}
		function showHome(){
			vm.homeSelected = true;
			vm.restaurantsSelected = false;
			vm.friendsSelected = false;
			vm.profileSelected = false;
        	$location.path('/homePage');

		}		
		function showFriends(){
			vm.homeSelected = false;
			vm.restaurantsSelected = false;
			vm.friendsSelected = true;
			vm.profileSelected = false;
        	$location.path('/homePage/friends');
		}
		
		function showProfile(){
			vm.homeSelected = false;			
			vm.restaurantsSelected = false;
			vm.friendsSelected = false;
			vm.profileSelected = true;		
        	$location.path('/homePage/profile');

		}
		
		function showSelectedPerson(person){
			vm.selectedPerson = person;
		}
		
			
		function showSelectedPersonRequest(index){
			if(vm.friendRequests[index].requestSender.id == vm.loggedUser.id)
				vm.selectedPerson = vm.friendRequests[index].requestResponder;
			else
				vm.selectedPerson = vm.friendRequests[index].requestSender;
		}		
		

		
		//setuj polja za update acount-a
		function updateAccount(){
			vm.update = true;
		}
		
		//izmeni podatke o logovanom korisniku
		function saveChanges(){
			vm.update = false;
			GuestService.updateGuest(vm.loggedUser)
			.then(function(response){
				vm.successUpdate = true; 
		         $timeout(function () { vm.successUpdate = false; }, 3000); 
				AuthenticationService.setCredentials(vm.loggedUser.email,vm.loggedUser.password);
			},
			function(errorResponse){
				vm.successUpdate = false;
			})
		}
		
		//posalji zahtev za prijateljstvo
		function sendFriendRequest(index){
			GuestService.sendFriendRequest(vm.loggedUser.id,vm.noneFriends[index].id)
			.then(function(httpData){
				vm.selectedPerson = {};
				//vm.getNoneFriends();
				vm.searchNoneFriends();
			},
			function(httpData){
				console.log(httpData.data.message);
				vm.searchNoneFriends();
				
			})
		}
		
		//brisanje iz liste prijatelja		
		function removeFromFriendsList(index){
			GuestService.removeFromFriendsList(vm.friends[index].id)
			.then(function(httpData){
				//vm.getNoneFriends();
				vm.searchNoneFriends();
			},function(httpData){
				console.log(httpData.data.message);
				vm.searchNoneFriends();
			})
		}
		
		//prihvatanje zahteva za prijateljstvo
		function acceptFriendRequest(index){
			GuestService.acceptFriendRequest(vm.friendRequests[index].id)
			.then(function(httpData){
				//vm.getNoneFriends();
				vm.searchNoneFriends();
			},
			function(httpData){
				console.log(httpData.data.message);
				vm.searchNoneFriends();
			})
		}
		
		//odbijanje zahteva za prijateljstvo
		function declineFriendRequest(index){
			GuestService.declineFriendRequest(vm.friendRequests[index].id)
			.then(function(httpData){
				//vm.getNoneFriends();
				vm.searchNoneFriends();
			},
			function(httpData){
				console.log(httpData.data.message);
				vm.searchNoneFriends();

			})
		}
		
		function searchNoneFriends(){
			GuestService.searchNoneFriends(vm.searchCondition)
			.then(function(httpData){
				vm.noneFriends = httpData.data;
				vm.searchFriends();
			},
			function(httpData){
				console.log(httpData.data.message)
			})
		}
		//pronadji sve prijatelje, uz odredjeni uslov
		function searchFriends(){
			GuestService.searchFriends(vm.searchCondition)
			.then(function(httpData){
				vm.friends =httpData.data;
				vm.searchFriendRequests();
			},
			function(httpData){
				console.log(httpData.data.message);
			})
		}
		
		function searchFriendRequests(){
			GuestService.searchFriendRequests(vm.searchCondition)
			.then(function(httpData){
				vm.friendRequests = httpData.data;
			},
			function(httpData){
				console.log(httpData.data.message);
			})
		}
		
		function getAllRestaurants(){
			GuestService.getAllRestaurants(vm.sortRestaurantsCriteria.name)
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
				GuestService.searchRestaurants(vm.searchRestaurantsCondition,vm.sortRestaurantsCriteria.name)
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
		function reserve(){
			GuestService.addRestaurantToSession(vm.selectedRestaurant.id)
			.then(function(httpData){
	        	$location.path('/reservation');
			},
			function(httpData){
				console.log(httpData.data.message);
			})
		}
		
		function loadReservations(){
			GuestService.loadReservations()
			.then(function(httpData){
				vm.reservations = httpData.data;
				vm.getRestOfRes();
			},
			function(httpData){
				console.log(httpData.data.message);
			})
		}
		
		function getDate(date){
	    	var splitted = new Date(date).toString().split(" ");
	    	var splittedTime = splitted[4].split(":");

	    	return splitted[1]+ " "+splitted[2]+ " "+ splitted[3] + " "	+splittedTime[0] + ":" + splittedTime[1];		

		}
		function getRestOfRes(){
			for(var i = 0; i < vm.reservations.length;i++){
				var id = vm.reservations[i].id;
				vm.getRestaurantOfReservation(id);
			}
		}
		function getRestaurantOfReservation(id){
			vm.restaurantsReservations = [];
			GuestService.getRestaurantOfReservation(id)
			.then(function(httpData){
				for(var j = 0; j < vm.reservations.length;j++){
					if(vm.reservations[j].id == id){
						vm.reservations[j].restaurant = httpData.data;
					}
				}
				vm.restaurantsReservations.push({reservation  : id, restaurant : httpData.data});
			},
			function(httpData){
				console.log(httpData.data.message);
			})
		}
		
		function getRestaurant(reservationId){
			for(var i = 0; i < vm.restaurantsReservations.length;i++){
				if(vm.restaurantsReservations[i].reservation == reservationId)
					return vm.restaurantsReservations[i].restaurant.name
			}
		}
		
		function addToSession(reservationId){
			var restaurantId = null;
			for(var i = 0; i < vm.restaurantsReservations.length;i++){
				if(vm.restaurantsReservations[i].reservation == reservationId){
					restaurantId =  vm.restaurantsReservations[i].restaurant.id
					break;
				}
			}			
			GuestService.setRestaurantReservation(restaurantId,reservationId)
			.then(function(httpData){
				$location.path("/order");
			},
			function(httpData){
				console.log(httpData.data.message);
			})
		}
		
		function cancelReservation(id){
			GuestService.cancelReservation(id)
			.then(function(httpData){
				vm.loadReservations();
			},
			function(httpData){
				console.log(httpDada.data.message);
			})
		}
		
		function loadHistory(){
			GuestService.loadHistory()
			.then(function(httpData){
				vm.history = httpData.data;
				vm.getRestOfResHistory();
			},
			function(httpData){
				console.log(httpData.data.message);
			})
		}
		
		function getRestOfResHistory(){
			for(var i = 0; i < vm.history.length;i++){
				var id = vm.history[i].id;
				vm.getRestaurantOfReservationHistory(id);
			}
		}
		
		function getRestaurantOfReservationHistory(id){
			GuestService.getRestaurantOfReservation(id)
			.then(function(httpData){
				for(var j = 0; j < vm.history.length;j++){
					if(vm.history[j].id == id){
						vm.history[j].restaurant = httpData.data;
					}
				}
			},
			function(httpData){
				console.log(httpData.data.message);
			})
		}
	}
})();