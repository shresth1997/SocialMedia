/**
 *  FriendService
 */
app.factory('FriendService',function($http){
	var friendService={};
	
	friendService.suggestedUsers=function(){
		return $http.get ("http://localhost:9081/backend_project2/suggesteduserslist")
	}
	friendService.sendFriendRequest=function(toUsername){
		return $http.get("http://localhost:9081/backend_project2/friendrequest/"+toUsername);
	}
	friendService.pendingRequests=function(){
		return $http.get("http://localhost:9081/backend_project2/pendingrequests");
	}
	friendService.updatePendingRequest=function(fromId,status){
		return $http.put("http://localhost:9081/backend_project2/updatependingrequest/"+fromId+"/"+status);
	}
	friendService.listOfFriends=function(){
		return $http.get ("http://localhost:9081/backend_project2/listoffriends")
	}
	return friendService;
})