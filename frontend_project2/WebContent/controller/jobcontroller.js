/**
 * 
 */
app.controller('JobController',function($scope,$location,JobService){
	$scope.showdetails=false;
	$scope.message=''
	function getAllJobs(){
	JobService.getAllJobs().then(function(response){
		$scope.jobs=response.data;
	},function(response){
		$scope.message=response.data.message
		$location.path('/login')
	})
	}
	
	$scope.saveJob=function(){
		JobService.saveJob($scope.job).then(function(response){
			$location.path('/getalljobs')
		},function(response){
			$scope.message=response.data.message
			if(response.status==401)
				$location.path('/login')
				if(response.status==500)
			$location.path('/savejob')
		})
	}

	$scope.getJobDetail=function(id){
		$scope.showdetails=true;
		JobService.getJobById(id).then(function(response){
			console.log('entering success function in blogapproved')
			$scope.job=response.data;
		},function(response){
			console.log(response.status);
		})
	}
	getAllJobs();
})