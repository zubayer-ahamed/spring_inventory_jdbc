var myApp = angular.module("myApp", []);

myApp.controller("appCtrl", function($scope, $http){
    
    $scope.pid = "";
    $scope.price = "";
    $scope.stock = "";
    //get All Product
    $scope.getAllProduct = function(){
        $http({
            method: 'GET',
            url : 'products/allProduct'
        }).then(function(response){
            $scope.products = response.data;
        });
    };
    //call method to get all products
    $scope.getAllProduct();
    
    //getSelected Product
    $scope.clickedProduct = {};
    
   //get All Customer
    $scope.getAllCustomer = function(){
        $http({
            method: 'GET',
            url : 'customers/allCustomer'
        }).then(function(response){
            $scope.customers = response.data;
        });
    };
    //call method to get all Customer
    $scope.getAllCustomer();
    
    //getSelected Customer
    $scope.clickedCustomer = {};
    
    
});


