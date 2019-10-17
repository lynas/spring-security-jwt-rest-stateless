# Spring MVC stateless with security using jwt

first create database name `demorest`

Run app

### run following command in db 

```
INSERT INTO appuser (authorities, password, username) 
VALUES ('ROLE_ADMIN, ROLE_EM PLOYEE, ROLE_MANAGER', '$2a$10$aS/lF2c/9JWPUjDHfJ/zTed1ihGBgfX/7xnGTOM5/lW59X4FHalSi', 'lynas');
```

### Make post call at following url

http://localhost:8080/auth

### with body

```
{ 
  "username" : "lynas",
  "password" : "123456"
}
```
### You will get result as following

```
{
  "token": "sasdasdasd"
}
```

### Make get call with header X-Auth-Token

http://localhost:8080/protected

X-Auth-Token : "sasdasdasd"
