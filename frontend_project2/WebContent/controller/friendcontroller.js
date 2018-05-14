/*
*
*/
app.controller('FriendController',function($scope,$location,FriendService){
	function listOfSuggestedUsers(){
		$scope.suggestedUsers=FriendService.suggestedUsers().then(function(response){
			console.log(response.data)
			$scope.suggestedUsers=response.data;
		},function(response){
			console.log(response.status);
		})
	}

	
	function pendingRequests(){
		$scope.listOfPendingRequests=FriendService.pendingRequests().then(function(response){
			$scope.listOfPendingRequests=response.data;
		},function(response){
			console.log(response.status);
		})
	}
	
	function listOfFriends(){
		$scope.friendsList=FriendService.listOfFriends().then(function(response){
			console.log(response.data)
			$scope.friendsList=response.data;
		},function(response){
			console.log(response.status);
		})
	}
	
	/*
	 * toUsername=user.username
	 */
	
	$scope.friendrequest=function(toUsername){
		FriendService.sendFriendRequest(toUsername).then(function(response){
			alert ("Friendrequest has been sent successfully..")
			listOfSuggestedUsers();
			$location.path('/suggestedusers')
		},function(response){
			console.log(response.status)
		})
	}
	
	$scope.updatePendingRequest=function(fromId,status){
		alert ("entering update pending request")
		FriendService.updatePendingRequest (fromId,status).then(function (response){
			pendingRequests();
			$location.path('/pendingrequests')
		},function(response){
			console.log(response);
		})
	}
	listOfFriends();
	pendingRequests();
	listOfSuggestedUsers();
})