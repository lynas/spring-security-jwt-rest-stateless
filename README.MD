# Stateless web application with Spring MVC and Spring Security with JWT

Run the application with following command. 
From application root run
```
./gradlew bootRun
```

# Make post call 
http://localhost:8080/auth
with body

```
{ 
  "username" : "lynas",
  "password" : "123456"
}
```
CURL example
```
curl -X POST "http://localhost:8080/auth" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"username\": \"lynas\", \"password\": \"123456\"}"
```
you will get result

```
eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJseW5hcyIsImF1ZGllbmNlIjoid2ViIiwiY3JlYXRlZCI6MTU3MTIxMzc1MTEyMywi......
```

#make get call with header Authorization

http://localhost:8080/protected

Authorization : "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJseW5hcyIsImF1ZGllbmNlIjoid2ViIiwiY3JlYXRlZCI6MTU3MTIxMzc1MTEyMywi......"

CURL Sample

```
curl -X GET "http://localhost:8080/protected" -H "accept: */*" -H "Authorization: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJseW5hcyIsImF1ZGllbmNlIjoid2ViIiwiY3JlYXRlZCI6MTU3MTIxMzc1MTEyMywi......"
```
