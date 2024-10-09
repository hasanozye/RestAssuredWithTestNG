# Path Parameters vs. Query Parameters

## Path Parameters
Path parametreleri, URL'de belirli bir kaynağı işaret eder. 
URL'nin ana kısmında bulunur ve `?` kullanılmadan eklenir.

**Örnek:**
`https://reqres.in/api/users/2`
- Burada `2`, belirli bir kullanıcıya ulaşmak için kullanılan bir path parametresidir.

## Query Parameters
Query parametreleri URL'nin sonunda, `?` işareti ile başlar ve isteğe bağlı ek bilgiler sağlar. 
Genellikle veriyi filtrelemek veya sayfalama yapmak için kullanılır.

**Örnek:**
`https://reqres.in/api/users?page=2`
- Burada `page=2`, kullanıcı listesinin ikinci sayfasını almak için kullanılan bir query parametresidir.

## Farklar
1. **Konum**: Path parametreleri URL'nin ana parçasında, query parametreleri URL'nin sonunda yer alır.
2. **Zorunluluk**: Path parametreleri genellikle zorunludur, query parametreleri isteğe bağlıdır.

## Örnek Kod
```java
String baseUrl = "https://reqres.in/api/users";
String pathParam = "/2";
String queryParam = "?page=2";
String fullUrl = baseUrl + pathParam + queryParam;
System.out.println("URL: " + fullUrl);
