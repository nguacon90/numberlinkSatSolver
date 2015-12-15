angular.module('numberlink', []);
angular.module('numberlink').controller('numberlinkController', function($scope, $http) {
	
	$scope.inputs = ['input1', 'input2'];
	$scope.showResult = false;
	
	$scope.load = function(input) {
		$scope.input = input;
		$http.get('numberlink?input=' + input).success(function(res){
			$scope.showResult = false;
			$scope.result = '';
			$scope.times = '';
			$scope.cells = res.cells;
		});
	};
	
	$scope.resolve = function(){
		$http.get('numberlink/resolve?input=' + $scope.input).success(function(res){
			$scope.result =  res.satisfiable == true ? 'SAT' : 'UNSAT';
			if(res.satisfiable) {
				$scope.showResult = true;
				$scope.sat = res.satisfiable;
				$scope.times = res.times;
				$scope.cells = res.cells;
			} 
		});
	};
	 
	$scope.isLeft = function(cell) {
		return cell.pattern.indexOf(1) >= 0;
	};
	$scope.isRight = function(cell) {
		return cell.pattern.indexOf(2) >= 0;
	};
	$scope.isUp = function(cell) {
		return cell.pattern.indexOf(3) >= 0;
	};
	$scope.isDown = function(cell) {
		return cell.pattern.indexOf(4) >= 0;
	};
});