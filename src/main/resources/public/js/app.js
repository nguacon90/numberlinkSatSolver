angular.module('numberlink', []);
angular.module('numberlink').controller('numberlinkController', function($scope, $http) {
	$http.get('numberlink/resolve').success(function(res){
		$scope.cells = res.cells;
	}); 
	
	$scope.getClass = function(cell) {
		var classCss = [];
		var patterns = cell.pattern;
		if(patterns.length > 0) {
			if(patterns.indexOf(1) >= 0 && patterns.indexOf(2) >= 0) {
				classCss.push("class-1-2");
				return classCss;
			}
			if(patterns.indexOf(3) >= 0 && patterns.indexOf(4) >= 0) {
				classCss.push("class-3-4");
				return classCss;
			}
			for (var i = 0; i < patterns.length; i++) {
				 classCss.push("class-" + patterns[i]);
			}
		}
		
		return classCss;
	};
});