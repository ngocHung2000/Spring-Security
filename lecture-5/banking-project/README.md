### Test API
```shell
curl -X POST "http://127.0.0.1:8080/register" \
-H "Content-Type: application/json" \
-d '{"email": "join@gmail.com", "pwd": "12345","role": "USER"}'
```