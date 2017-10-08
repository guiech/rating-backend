# Rating App

## Endpoints
### Users
#### oauth/token
Get token
- type: POST
- headers: 
-- Content-Type: application/json
-- Authorization: 'Basic ' + <encoded client:secret string>
- parameters: `{username: <string>, password: <string>, grant_type: 'password'}`
- returns: `{access_token: <string>}`

### users
Register user

### Products
#### product/byName/<product name:string>
Get product data by product name. Ignore case sensitive and search by containing text
- type: GET
- url parameters: `product name search:string`
- returns: `[{product1},{product2},...]`
