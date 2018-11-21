(function(){
	'use strict';
	
	angular
	.module('app')
	.controller('ReservationController',ReservationController);
	
	ReservationController.$inject = ['$location','$rootScope','$route','ReservationService','AuthenticationService','$timeout','GuestService']
	
	function ReservationController($location,$rootScope,$route,ReservationService,AuthenticationService,$timeout,GuestService){
		var vm = this;
		vm.selectedRestaurant = undefined;
		vm.reservationDate = undefined;
		vm.selectedTables = [];
	    vm.reservedDate =undefined; 		
	    vm.stayTime = undefined;		
		vm.staySelected = false;
		vm.reservedTimeSelected = false;
	    vm.tablesStatus = [];
	    vm.tableView = false;
	    vm.reservation = $rootScope.reservation;
		vm.friends = [];
		vm.searchCondition;
		vm.invitedFriends = [];
		vm.confirmedInvitation = [];
		vm.registrationCode = null;
		vm.invitedFriendCode = null;
		vm.restoranOfReservation = null;
		
		$route.current.params.code1;		
		vm.logout = logout;
		vm.getNumber = getNumber;
		vm.getTableOznaka = getTableOznaka;
		vm.rowCheckNumberOfTables = rowCheckNumberOfTables;
		vm.addToSelected = addToSelected;
		vm.reloadStatuses = reloadStatuses;
		vm.checkIfAdded  = checkIfAdded;
		vm.removeFromSelected = removeFromSelected;
		vm.showTables = showTables;
		vm.getStatus = getStatus;
		vm.getTable = getTable;
		vm.confirmReservation = confirmReservation;
		vm.getDateFromReservation = getDateFromReservation;
		vm.searchFriends = searchFriends;
		vm.inviteFriend = inviteFriend;
		vm.checkIfSendInvitation = checkIfSendInvitation;
		vm.back = back;
		vm.acceptInvitation = acceptInvitation;
		vm.declineInvitation = declineInvitation;
		vm.checkIfConfirmed = checkIfConfirmed;
		vm.checkIfAccepted = checkIfAccepted;
		vm.getRestaurantOfReservation = getRestaurantOfReservation;
		vm.order = order;
		
		//alert($route.current.params.code1);
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
		
		function back(){
			$location.path('/homePage/restaurants')
		}
		
		(function getInvitesFromReservation(){
			if(typeof $route.current.params.code1 !== "undefined"){
				vm.registrationCode = $route.current.params.code1;
				vm.invitedFriendCode = $route.current.params.code2;
				ReservationService.getReservation(vm.registrationCode,vm.invitedFriendCode)
				.then(function(httpData){
					vm.reservation = httpData.data;
					vm.invitedFriends = vm.reservation.invitedFriends;
					vm.confirmedInvitation = vm.reservation.guests;
					getRestaurantOfReservation();
				},
				function(httpData){
					console.log(httpData.data.message)
				})
			}			
		})();
		
		(function setInvitationFriends(){
			if(vm.reservation != undefined){
				vm.invitedFriends = vm.reservation.invitedFriends;
				vm.confirmedInvitation = vm.reservation.guests;
			}
		})();
		
		(function getSelectedRestaurant(){
			ReservationService.getSelectedRestaurant()
			.then(function(httpData){
				vm.selectedRestaurant = httpData.data;
			},
			function(httpData){
				console.log(httpData.data.message)
			})
		})();
        (function () {
            $('#datetimepicker1').datetimepicker();
            $('#datetimepicker3').datetimepicker({
                format: 'LT'
            });
        })();
        $("#datetimepicker1").on('dp.change',function(e){
        	vm.reservedDate =new Date (e.date);
        	var current = new Date();
        	if(vm.reservedDate.getTime() >= current.getTime()){
        		vm.reservedTimeSelected = true
        	}
        	else{
        		vm.reservedTimeSelected = false;
        	}
        		
        		

        })
        $("#datetimepicker3").on('dp.change',function(e){
        	vm.stayTime = new Date(e.date);
        	var current = new Date();
        	if(vm.stayTime.getTime() > current.getTime()){
        		vm.staySelected = true;
        	}
        	else{
        		vm.staySelectd = false;
        	}
        	//alert(date);
        })
        
        //proanadji sto iz zeljenog segmenta , u zeljenom redu i zeljenoj koloni
        function getTable(regionIndex,row,col){
        	var rez = null;
    		var reg = vm.selectedRestaurant.regions[regionIndex];
    		for(var j = 0; j < reg.tables.length;j++){
    			var table = reg.tables[j];
        		if(table.colNum== col && table.rowNum == row){
        			rez = table;
        		}
    		}
    		return rez;        	
        }
        
        //uzimam oznaku tabele
        function getTableOznaka(regionIndex,row,col){
        	var table = vm.getTable(regionIndex,row,col);
        	var rez = undefined;
        	if(table != null)
        		rez = table.oznakaStola;
        	return rez;
        }
        //vraza niz od 1 do zeljenog broja
        function getNumber(num) {
        	var niz = [];
        	for(var i = 1; i <= num; i++){
        		niz.push(i);
        	}
            return niz;   
        }
        //proveravam broj kolona, ako nema nsita da ne prikazuje red
        function rowCheckNumberOfTables(regionIndex,row){
        	var reg = vm.selectedRestaurant.regions[regionIndex];
        	for(var j = 0; j < reg.tables.length;j++){
        		var table = reg.tables[j];
        		if(table.rowNum == row)
        			return true;
        	}
        	return false;
        }
        //dodavanje sto u listu izabranih za rezervaciju
        function addToSelected(regionIndex,row,col){
        	var added = vm.checkIfAdded(regionIndex,row,col);
        	if(!added)
	    		var table = vm.getTable(regionIndex,row,col);
	    		if(table != null)
	    			vm.selectedTables.push(table);
        }
        //provaeravamo da li je gost izabrao ovaj sto, radi promene izgleda
        function checkIfAdded(regionIndex,row,col){
    		var t = vm.getTable(regionIndex,row,col);
    		if(t != null)
	    		for(var i = 0; i < vm.selectedTables.length;i++){
	    			var table = vm.selectedTables[i];
	    			if(t.id == table.id)
	    				return true;
	    		}
    		return false;
        }
        //brisanje iz liste selektovanih za rezervaciju
        function removeFromSelected(index){
        	vm.selectedTables.splice(index,1);
        }
        //prikazujem tabele i postavljam unete datume rezervacije 
        function showTables(){
        	var dates = []
        	vm.tableView = false;
        	dates.push(vm.reservedDate);
        	dates.push(vm.stayTime);
        	ReservationService.sendDate(dates)
        	.then(function(httpData){
        		vm.stayTime = new Date(httpData.data);
        		alert(vm.stayTime);
        		vm.reloadStatuses();
        	},
        	function(httpData){
        		console.log(httpData.data.message);
        	})
        }
        
        
        
        //na serveru vrsim proveru da li je sto rezervisan, za uneti termin
        function reloadStatuses(regionIndex,row,col){
	        ReservationService.reloadStatuses()
	       	.then(function(httpData){
	       		vm.tablesStatus = httpData.data;
           		vm.tableView = true;

	        	},
	        function(httpData){
	       		console.log(httpData.data.message)
	       	})
        }
        //proveram da li je sto, za uneti termin, vec rezervisan 
        function getStatus(regionIndex,row,col){
        	var rez = undefined;
        	var t = vm.getTable(regionIndex,row,col);
    		if(t != null)
    			if(vm.tablesStatus.hasOwnProperty(t.id)){
    				var status = vm.tablesStatus[t.id];
    				return vm.tablesStatus[t.id];
    				}

    		return false;
        }
        
        function confirmReservation(){
        	ReservationService.confirmReservation(vm.selectedTables)
        	.then(function(httpData){
        		$rootScope.reservation = httpData.data;
            	$location.path('/reservations/friends');
        	},
        	function(httpData){
        		alert(httpData.data.message);
        		vm.selectedTables = [];
        		vm.showTables();
        	})
        }
        function getDateFromReservation(condition){
        	var splitted = new Date(vm.reservation.date).toString().split(" ");
        	var splittedTime = splitted[4].split(":");
        	var splitted1 = new Date(vm.reservation.stay).toString().split(" ");
        	var splittedStay = splitted1[4].split(":");

        	if(condition == "date"){
        		return splitted[1]+ " "+splitted[2]+ " "+ splitted[3]
        	}
        	else if(condition == "startTime"){
        		return splittedTime[0] + ":" + splittedTime[1];
        	}
        	else if(condition == "stayTime"){
        		return splittedStay[0] + ":" + splittedStay[1];
       		
        	}
        	return "";
        }
        
		function searchFriends(){
			GuestService.searchFriends(vm.searchCondition)
			.then(function(httpData){
				vm.friends =httpData.data;
			},
			function(httpData){
				console.log(httpData.data.message);
			})
		}
		
		function inviteFriend(index){
			ReservationService.inviteFriend(vm.friends[index],vm.reservation.id)
			.then(function(httpData){
				vm.reservation = httpData.data;
				vm.invitedFriends = vm.reservation.invitedFriends;
				vm.confirmedInvitation = vm.reservation.guests;
			},
			function(httpData){
				console.log.httpData.data.message;
			})
		}
		
		function checkIfSendInvitation(index){
			var friend = vm.friends[index];
			if(vm.invitedFriends != null)
				for(var i = 0; i < vm.invitedFriends.length;i++){
					if(vm.invitedFriends[i].id == friend.id)
						return false;
				}
			if(vm.confirmedInvitation != null)
				for(var i = 0; i < vm.confirmedInvitation.length;i++){
					if(vm.confirmedInvitation[i].id == friend.id)
						return false;
				}
			return true;
		}
		
		function acceptInvitation(){
			ReservationService.acceptInvitation(vm.reservation.id)
			.then(function(httpData){
				vm.reservation = httpData.data;
				vm.invitedFriends = vm.reservation.invitedFriends;
				vm.confirmedInvitation = vm.reservation.guests;
			},
			function(httpData){
				console.log(httpData.data.message);
			})
		}
		
		function declineInvitation(){
			ReservationService.declineInvitation(vm.reservation.id)
			.then(function(httpData){
				vm.reservation = httpData.data;
				vm.invitedFriends = vm.reservation.invitedFriends;
				vm.confirmedInvitation = vm.reservation.guests;
			},
			function(httpData){
				console.log(httpData.data.message);
			})			
		}
		
		function checkIfConfirmed(){
			for(var i = 0; i < vm.invitedFriends.length;i++){
				var friend = vm.invitedFriends[i];
				if(friend.id == vm.invitedFriendCode.substring(6))
					return true;
			}
			return false;
		}
		//proveravam da li je potvrdio
		function checkIfAccepted(){
			for(var i = 0; i < vm.confirmedInvitation.length;i++){
				var friend = vm.confirmedInvitation[i];
				if(friend.id == vm.invitedFriendCode.substring(6))
					return true;
			}
			return false;
		}
		
		function getRestaurantOfReservation(){
			ReservationService.getRestaurantOfReservation(vm.reservation.id)
			.then(function(httpData){
				vm.restaurantOfReservation = httpData.data;
			},
			function(httpData){
				console.log(httpData.data.message);
			})
		}
		
		function order(){
			ReservationService.setReservation(vm.reservation.id)
			.then(function(httpData){
				$location.path("/order");
			},
			function(httpData){
				console.log(httpData.data.message)
			})
		}
	}
})();