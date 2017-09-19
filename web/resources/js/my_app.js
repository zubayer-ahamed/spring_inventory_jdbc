var myApp = angular.module("myApp", []);

myApp.controller("appCtrl", function ($scope, $http) {

    $scope.oid = "";
    $scope.orderType = "sell";
    $scope.orderDate = new Date();
    $scope.finalTotal = 0;

    //get All Product
    $scope.products = [];
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
            //alert("first insert");
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
        for (var i = 0; i < $scope.cartProduct.length; i++) {
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
        $scope.orderDetails = {'odid': '', 'oid': '', 'pid': '', 'price': '', 'qty': ''};
        $scope.order = {'oid': '', 'cid': '', 'total': '', 'orderType': '', 'orderDate': ''};


        //make order perform
        $scope.order.cid = $scope.clickedCustomer.cid;
        $scope.order.total = $scope.finalTotal;
        $scope.order.orderType = $scope.orderType;
        $scope.order.orderDate = $scope.orderDate;

        $http({
            method: 'POST',
            url: 'orders/order',
            data: angular.toJson($scope.order),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(function (response) {
            //alert("order success");
        }, function (reason) {
            //alert("order fail");
        });


        //make order details perform
        for (var i = 0; i < $scope.cartProduct.length; i++) {
            $scope.orderDetails.oid = $scope.oid;
            $scope.orderDetails.pid = $scope.cartProduct[i].pid;
            $scope.orderDetails.price = $scope.cartProduct[i].price;
            $scope.orderDetails.qty = $scope.cartProduct[i].qty;
            //alert("loop: " + (i + 1));
            $http({
                method: 'POST',
                url: 'orderdetails/orderdetail',
                data: angular.toJson($scope.orderDetails),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(function (response) {
                //alert("detail success");
            }, function (reason) {
                //alert("detail fail");
            });


            //make product update perform
            $scope.updatedProduct = {'pid': '', 'pname': '', 'price': '', 'qty': ''};

            //alert($scope.productsAgain.length);
            for (var j = 0; j < $scope.productsAgain.length; j++) {
                //alert("enter to loop");
                if ($scope.productsAgain[j].pid === $scope.orderDetails.pid) {
                    //alert("product stock: " + $scope.productsAgain[i].qty);
                    //alert("cart qty: " + $scope.orderDetails.qty);
                    if ($scope.orderType === 'sell') {
                        $scope.changedQty = $scope.productsAgain[j].qty - $scope.orderDetails.qty;
                    } else {
                        $scope.changedQty = parseInt($scope.productsAgain[j].qty) + parseInt($scope.orderDetails.qty);
                    }
                    //alert("changable qty: " + $scope.changedQty);
                    $scope.updatedProduct.pid = $scope.productsAgain[j].pid;
                    $scope.updatedProduct.pname = $scope.productsAgain[j].pname;
                    $scope.updatedProduct.price = $scope.productsAgain[j].price;
                    $scope.updatedProduct.qty = $scope.changedQty;
                }
            }

            $http({
                method: 'PUT',
                url: 'products/product',
                data: angular.toJson($scope.updatedProduct),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(function (response) {
                //alert("product update success");
            }, function (reason) {
                //alert("product update fail");
            });

        }

    };

    //safty
    $scope.productsAgain = [];
    $scope.productAgainRequest = function () {
        $http({
            method: 'GET',
            url: 'products/allProduct'
        }).then(function (response) {
            $scope.productsAgain = response.data;
        });
    };
    $scope.productAgainRequest();



});


