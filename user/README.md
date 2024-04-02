
# USERS API



### ping

```http
  GET /user/v1/ping
```
**responses**

| code | description     | 
| :-------- | :------- | 
| `200`      | `ok` | 

```
pong
``` 

### Login

```http
  POST /login/user
```

**Required body**

```
{
  "mail": "user@example.com",
  "password": "string"
}
```
**responses**

| code | description     | 
| :-------- | :------- | 
| `200`      | `ok` |

```
{
    "id": "Cedula1",
    "name": "Usuario1",
    "last_name": "ApellidoUsuario1",
    "phone_number": "Numero1",
    "mail": "usuario1@example.com",
    "type": "Tipo1"
}
``` 

| code | description     | 
| :-------- | :------- | 
| `404`      | `email not found` |

```
{
    "code": 404,
    "message": "user not found. Please, check mail"
}
```

| code | description     | 
| :-------- | :------- | 
| `401`      | `wrong password` |

```
{
    "code": 401,
    "message": "invalid password"
}
```

### Get all users

```http
  GET /user/v1/get/users
```

**responses**

| code | description     | 
| :-------- | :------- | 
| `200`      | `ok` |

```
[
    {
        "id": "Cedula1",
        "name": "Usuario1",
        "last_name": "ApellidoUsuario1",
        "phone_number": "Numero1",
        "mail": "usuario1@example.com",
        "type": "Tipo1"
    },
    {
        "id": "Cedula2",
        "name": "Usuario2",
        "last_name": "ApellidoUsuario2",
        "phone_number": "Numero2",
        "mail": "usuario2@example.com",
        "type": "Tipo2"
    },
    {
        "id": "Cedula3",
        "name": "Usuario3",
        "last_name": "ApellidoUsuario3",
        "phone_number": "Numero3",
        "mail": "usuario3@example.com",
        "type": "Tipo3"
    }
]
``` 

### Create user

```http
  POST /user/v1/create/user
```

**Required body**

```

{
  "id":"1234567",
  "name":"test",
  "last_name":"Test",
  "phone_number":"nst1231211",
  "mail":"test@gmail.com",
  "type":1
}

```

**responses**

| code | description     | 
| :-------- | :------- | 
| `200`      | `ok` |

```
{
    "id": "1234567",
    "name": "test",
    "last_name": "Test",
    "mail": "test@gmail.com"
}
``` 


| code | description     | 
| :-------- | :------- | 
| `400`      | `This email address is already being used` |

```
{
    "code": 400,
    "message": "This email address is already being used"
}
```


| code | description     | 
| :-------- | :------- | 
| `400`      | `User is already registered` |

```
{
    "code": 400,
    "message": "User is already registered"
}
```

| code | description     | 
| :-------- | :------- | 
| `400`      | `phone_number is already being used` |

```
{
    "code": 400,
    "message": "phone_number is already being used"
}
```

### Delete user

```http
  DELETE /user/v1/delete/user/{user_id}
```

| param | description     | required|
| :-------- | :------- | :------- | 
| `user_id`      | `user id` |`true` |

**responses**

| code | description     | 
| :-------- | :------- | 
| `404`      | `user not found. Please, check id` |

```
{
    "code": 404,
    "message": "user not found. Please, check id"
}
```

| code | description     | 
| :-------- | :------- | 
| `200`      | `deleted succesfully` |

```
{
    "code": 200,
    "message": "deleted succesfully"
}
```