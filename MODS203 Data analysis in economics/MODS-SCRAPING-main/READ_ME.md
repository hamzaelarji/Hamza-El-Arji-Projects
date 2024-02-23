## StockX API
In the json query, only those 2 variables in header need to be changed :

"x-stockx-device-id": "14bdd41f-f2d3-4f59-8d4e-633b685771ee",
"x-stockx-session-id": "d637e4fb-5520-45b5-bc6c-392b1f8ca262"

Cookies are useless, and other header variable might be useless too

Need to inspect whatever page on stockX, in network fetch/XHR, get a query (named e in chrome) and retrieve this 2 variables using "copy as curl" and past into curlconverter.com

You can use the headerChange function from class queryHandling to change device and session id variables or simply change them manually 

If at some point an error message appear in your CLI, because of a negative query response (" Wrong status code for XXX, might be a problem with the authorization variable in headers. Check on stockX.com if there is a capcha. type "yes" to retry"). You might want to look for a capcha in the stockX website (they ask you to click on a button to check you are not a bot - only works if you canged the device id and session id in the header variable of the json query). After doing that you can type "yes" in the CLI

## Tree
./SALES : All time series are stored there

./INFO_BROSWER : The info retrieved from the browse query

./PRODUCT_ID : result from function getProductId in the browse class

./JSON

    ./query : the json queries used to call the stockX api

    ./response : some json response from different type of queries 
    
./queryClass : each Class will handle each type of useful query and their response