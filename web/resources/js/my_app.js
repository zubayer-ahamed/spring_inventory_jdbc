var myApp = angular.module("myApp", []);

myApp.controller("appCtrl", function ($scope, $http) {

    $scope.oid = "";
    $scope.orderType = "";
    $scope.orderDate = "";
    $scope.finalTotal = 0;

    //get All Product
    $scope.getAllProduct = function () {
        $http({
            method: 'GET',
            url: 'products/allProduct'
        }).then(function (response) {
            $scope.products = response.data;
        });
    };
    //call method to get all products
    $scope.getAllProduct();

    //getSelected Product
    $scope.clickedProduct = {};

    //get All Customer
    $scope.getAllCustomer = function () {
        $http({
            method: 'GET',
            url: 'customers/allCustomer'
        }).then(function (response) {
            $scope.customers = response.data;
        });
    };
    //call method to get all Customer
    $scope.getAllCustomer();

    //getSelected Customer
    $scope.clickedCustomer = {};


    //order logic control
    $scope.qty = 0;
    $scope.total = 0;


    //add to cart
    $scope.cartProduct = [];
    $scope.addToCart = function () {
        $scope.clickedProduct.qty = $scope.qty;
        $scope.status = true;
        
        if ($scope.cartProduct.length === 0) {
            $scope.cartProduct.push($scope.clickedProduct);
            $scope.status = false;
            alert("first insert");
        } else if ($scope.cartProduct.length > 0) {
            for (var i = 0; i < $scope.cartProduct.length; i++) {
                if ($scope.cartProduct[i].pid === $scope.clickedProduct.pid) {
                    alert("Item already available in Cart");
                    $scope.status = false;
                    break;
                }
            }
        }

        if ($scope.status === true) {
            $scope.cartProduct.push($scope.clickedProduct);
        }
        
        $scope.finalTotal = 0;
        for (var i = 0; i <  $scope.cartProduct.length; i++) {
            $scope.finalTotal = $scope.finalTotal + ($scope.cartProduct[i].price * $scope.cartProduct[i].qty);
        }

        $scope.qty = 0;
        $scope.clickedProduct = {};
    };

    //remove from cart
    $scope.rCkickedProduct = {};
    $scope.removeProduct = function (product) {
        $scope.rClickedProduct = product;
        $scope.value = -1;
        alert($scope.cartProduct.indexOf($scope.rClickedProduct));
        $scope.value = $scope.cartProduct.indexOf($scope.rClickedProduct);
        if ($scope.value >= 0) {
            $scope.cartProduct.splice($scope.value, 1);
        }
    };


    //CheckOut
    $scope.checkOut = function () {
        alert(
                "order No: " + $scope.oid +
                "\n Customer ID: " + $scope.clickedCustomer.cid +
                "\n Order Type: " + $scope.orderType +
                "\n Order Date: " + $scope.orderDate +
                "\n Final Total: " + $scope.finalTotal 
                );

        for (var i = 0; i < $scope.cartProduct.length; i++) {
            alert(
                    "PID: " + $scope.cartProduct[i].pid +
                    "\nPName: " + $scope.cartProduct[i].pname +
                    "\nPrice: " + $scope.cartProduct[i].price +
                    "\nQty: " + $scope.cartProduct[i].qty

                    );
        }
    };

    



});


