# KidEx

1. Get Available Seats

curl --request GET \
  --url 'http://localhost:8080/getAvailableSeats?flightNumber=se'
  
  2. Book Ticket
  
  curl --request POST \
  --url http://localhost:8080/bookSeat \
  --header 'Content-Type: application/json' \
  --data '{
	"username" : "333",
	"flightNumber" : "se"
}'

3. Add a flight 

curl --request POST \
  --url http://localhost:8080/scheduleFlight \
  --header 'Content-Type: application/json' \
  --data '{
	"flightNumber" : "se",
	"numberOfSeats" :5
}'
