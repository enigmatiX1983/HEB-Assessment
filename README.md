# HEB-Assessment
Implementing the features HEB requested

To start this application, simply run in a terminal: 

<code>spring-boot:run</code>

To test this application, swagger is available at:

http://localhost:8080/swagger-ui/#/

For this exercise, you are able to give each API endpoint a combined cart-items and coupons JSON object, e.g.: 

```json
{
  "items": [
    {
      "itemName": "H-E-B Two Bite Brownies",
      "sku": 85294241,
      "isTaxable": false,
      "ownBrand": true,
      "price": 3.61
    },
    {
      "itemName": "Halo Top Vanilla Bean Ice Cream",
      "sku": 95422042,
      "isTaxable": false,
      "ownBrand": false,
      "price": 3.31
    },
    {
      "itemName": "H-E-B Select Ingredients Creamy Creations Vanilla Bean Ice Cream",
      "sku": 64267055,
      "isTaxable": true,
      "ownBrand": true,
      "price": 9.83
    },
    {
      "itemName": "Aveeno Daily Moisturizing Body Wash",
      "sku": 12821859,
      "isTaxable": true,
      "ownBrand": false,
      "price": 5.11
    },
    {
      "itemName": "Tofurky",
      "sku": 61411728,
      "isTaxable": true,
      "ownBrand": false,
      "price": 8.15
    }
  ],
  "coupons": [
    {
      "couponName": "Brownie Discount",
      "appliedSku": 85294241,
      "discountPrice": 0.79
    },
    {
      "couponName": "Tofurky Discount",
      "appliedSku": 61411728,
      "discountPrice": 1.01
    }
  ]
}
```

In an attempt to make the return value look more like a receipt the customer would receive, a set of ReceiptItems are returned (including the coupon discount information if applied), as well as the fields required in the Assessment instructions, e.g. (Feature 4 response to the above JSON):

```json
{
  "receiptItemList": [
    {
      "itemName": "H-E-B Two Bite Brownies",
      "price": 3.61,
      "discount": 0.79,
      "discountedPrice": 2.82
    },
    {
      "itemName": "Halo Top Vanilla Bean Ice Cream",
      "price": 3.31
    },
    {
      "itemName": "H-E-B Select Ingredients Creamy Creations Vanilla Bean Ice Cream",
      "price": 9.83
    },
    {
      "itemName": "Aveeno Daily Moisturizing Body Wash",
      "price": 5.11
    },
    {
      "itemName": "Tofurky",
      "price": 8.15,
      "discount": 1.01,
      "discountedPrice": 7.14
    }
  ],
  "subtotalBeforeDiscounts": 30.01,
  "discountTotal": 1.8,
  "subtotalAfterDiscounts": 28.21,
  "taxableSubtotalAfterDiscounts": 22.08,
  "taxTotal": 1.82,
  "grandTotal": 30.03
}
```
There are two exceptions currently in this application: For each feature's API, a Cart Exception with a code of '01' will be thrown if the cart is empty, and will return a message to the API caller, with a JSON input of:

```json
{
  "items": [
  ],
  "coupons": [
    {
      "couponName": "Brownie Discount",
      "appliedSku": 85294241,
      "discountPrice": 0.79
    },
    {
      "couponName": "Tofurky Discount",
      "appliedSku": 61411728,
      "discountPrice": 1.01
    }
  ]
}
```

.. producing a response (even with the items property totally missing as well): 

```json
{
  "errorTypesAndMessagesList": [
    {
      "errorCd": "01",
      "errorMessage": "Cart cannot be empty."
    }
  ]
}
```

... and in the case where there is a discount that is too large for the applied item (produces value less than zero), a set of errors for each affected product will be returned with an error code of '02', with this example JSON (a slight modification of the first one):

```json
{
  "items": [
    {
      "itemName": "H-E-B Two Bite Brownies",
      "sku": 85294241,
      "isTaxable": false,
      "ownBrand": true,
      "price": 3.61
    },
    {
      "itemName": "Halo Top Vanilla Bean Ice Cream",
      "sku": 95422042,
      "isTaxable": false,
      "ownBrand": false,
      "price": 3.31
    },
    {
      "itemName": "H-E-B Select Ingredients Creamy Creations Vanilla Bean Ice Cream",
      "sku": 64267055,
      "isTaxable": true,
      "ownBrand": true,
      "price": 9.83
    },
    {
      "itemName": "Aveeno Daily Moisturizing Body Wash",
      "sku": 12821859,
      "isTaxable": true,
      "ownBrand": false,
      "price": 5.11
    },
    {
      "itemName": "Tofurky",
      "sku": 61411728,
      "isTaxable": true,
      "ownBrand": false,
      "price": 8.15
    }
  ],
  "coupons": [
    {
      "couponName": "Brownie Discount",
      "appliedSku": 85294241,
      "discountPrice": 10.79
    },
    {
      "couponName": "Tofurky Discount",
      "appliedSku": 61411728,
      "discountPrice": 11.01
    }
  ]
}
```
... producing a response of: 

```json
{
  "errorTypesAndMessagesList": [
    {
      "errorCd": "02",
      "errorMessage": "H-E-B Two Bite Brownies has a final price of less than zero."
    },
    {
      "errorCd": "02",
      "errorMessage": "Tofurky has a final price of less than zero."
    }
  ]
}
```

... which is an exception which will require the corrected coupon information to be re-sent through an additional API call/transaction.

