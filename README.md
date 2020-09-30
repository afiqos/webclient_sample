# Spring webclient + kotlin

Practicing spring webclient to consume external api from customer_backend project.

### Notes
- customer_backend must be running first before we can consume its APIs
- currently we are just invoking the request using .subscribe within main function. Supposedly the different 
requests should be invoked using some logic. 


## Learning points

Steps to use consume external API: 
1) Create webclient instance
2) Prepare the request
3) Read response
<br/>
<br/>

It seems that it requires creating similar entity classes of the objects you will e.g. POST or GET. <br/>
E.g. you are sending GET request and expecting a Customer object, that object must be defined here with the same attributes
as defined on the external API side (i.e. customer_backend project).


Not too sure yet the difference between Mono and Flux return types. However, they seem to require at least the 
same type as the one returned by external API. In our case it would be ResponseEntity<T> for the requests. <br/>
Perhaps when consuming external APIs that is not yours it would help to find out what their return types are. 