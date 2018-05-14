/**
* angularjs module
*/
var app=angular.module("myApp",['ngRoute','ngCookies'])
app.config(function($routeProvider){
console.log('entering config function')
$routeProvider
.when('/registration',{
templateUrl:'views/registrationform.html',
controller:'UserController'
})
.when('/login',{
templateUrl:'views/login.html',
controller:'UserController'
})
.when('/savejob',{
templateUrl:'views/jobform.html',
controller:'JobController'
})
.when('/getalljobs',{
templateUrl:'views/jobtitles.html',
controller:'JobController'
})
.when('/saveblogpost',{
	templateUrl:'views/blogpostform.html',
	controller:'BlogPostController'
})
.when('/getallblogs',{
	templateUrl:'views/blogslist.html',
	controller:'BlogPostController'
})
.when('/getBlogForApproval/:id',{
	templateUrl:'views/approvalform.html',
	controller:'BlogDetailController'
})
.when('/getBlogDetail/:id',{
	templateUrl:'views/blogdetail.html',
	controller:'BlogDetailController'
})
.when('/suggestedusers',{
	templateUrl:'views/suggestedusers.html',
	controller:'FriendController'
})
.when('/pendingrequests',{
	templateUrl:'views/pendingrequest.html',
	controller:'FriendController'
})
.when('/listoffriends',{
	templateUrl:'views/listoffriends.html',
	controller:'FriendController'
})
.when('/profilepic',{
	templateUrl: 'views/profilepicture.html'
})
.when('/coverpic',{
	templateUrl: 'views/CoverImage.html'
})
.when('/edituserprofile',{
	templateUrl:'views/updateprofile.html',
	controller: 'UserController'
})
 .when('/chat',{
		templateUrl:'views/chat.html',
		controller:'ChatCtrl'
	})
.otherwise({
templateUrl:'views/home.html'
})
})
app.run(function($rootScope,$location,UserService,$cookieStore){

if($rootScope.currentUser==undefined)
$rootScope.currentUser=$cookieStore.get("currentUser")


$rootScope.logout=function(){
UserService.logout().then(function(response){
alert ("Loggedout Successfully..")
delete $rootScope.currentUser;
$cookieStore.remove("currentUser")
$location.path('/login')
},function(response){
console.log(response.status)
$rootScope.message=response.data.message
$location.path('/login')
})
}
})