# Opet API Uygulaması

Bu proje, Opet API'si aracılığıyla akaryakıt fiyatlarını çekmek ve belirli bir ilçeye ait fiyatları ekrana yazdırmak için Spring Boot kullanarak geliştirilmiştir. Uygulama, belirtilen bir il ve ilçeye ait akaryakıt fiyatlarını JSON formatında alır ve bu fiyatları ekrana yazdırır.

## Özellikler

- **RestTemplate** kullanarak Opet API'sinden verileri çeker.
- Belirtilen ilçeye ait akaryakıt fiyatlarını JSON formatında işler.
- Fiyat bilgilerini ekrana yazdırır.

## Teknolojiler

- **Java 11+**
- **Spring Boot**
- **RestTemplate** (Veri almak için HTTP istekleri yapar)
- **Jackson** (JSON işleme)
  
## Proje Yapısı

- `OpetapiApplication.java`: Ana uygulama sınıfı. API'den veri çeker ve işleme yapar.
  - `istek()`: API'den veri çeker.
  - `veriisleme()`: Çekilen veriyi işler.
  - `ilcebulma()`: İlgili ilçenin verilerini bulur.
  - `veriyazdır()`: Veriyi ekrana yazdırır.

## Kullanım Talimatları

1. **Proje Bağımlılıkları**:
   - Bu proje, Spring Boot kullanılarak geliştirildiği için bağımlılıkları `pom.xml` dosyasına ekleyebilirsiniz.
   - Proje başlatıldığında, `RestTemplate` ve `ObjectMapper` sınıfları otomatik olarak projeye dahil edilecektir.

2. **API İsteği**:
   - API URL'si `ProvinceCode` (il kodu) parametreleriyle şekillenir.
   - Örneğin, `İstanbul` (34 plaka kodu) için, API şu şekilde sorgulanır: 
     ```
     https://api.opet.com.tr/api/fuelprices/prices?ProvinceCode=34&IncludeAllProducts=true
     ```

3. **Çalıştırma**:
   - `OpetapiApplication` sınıfındaki `main` metodunu çalıştırarak uygulamayı başlatabilirsiniz.
   - Uygulama, belirtilen ilçedeki akaryakıt fiyatlarını çekip ekrana yazdıracaktır.

4. **Kullanıcı Parametreleri**:
   - `ilkodu`: İl plaka kodu, örneğin İstanbul için `"34"`.
   - `ilceadi`: İlçe adı, örneğin `"KARTAL"`.
## Uygulamanın Yanıtı

Uygulama, şu şekilde bir yanıt verir:

```
Kurşunsuz Benzin 95 : 45.17
Gazyağı : 38.26
Motorin UltraForce : 47.13
Motorin EcoForce : 47.13
Kalorifer Yakıtı : 32.37
Fuel Oil : 29.08
Yüksek Kükürtlü Fuel Oil : 25.65
```

## API Yanıtı

Uygulama, şu şekilde bir yanıt alır:

```json
[
  {
    "provinceCode": 34,
    "provinceName": "İSTANBUL ANADOLU",
    "districtCode": "034007",
    "districtName": "KARTAL",
    "prices": [
      {
        "id": "e2127693-6f71-4c5e-8b57-ac720100bfe6",
        "productName": "Kurşunsuz Benzin 95",
        "productShortName": "KURS",
        "amount": 45.17,
        "productCode": "A100"
      },
      {
        "id": "f35023b4-321f-4029-9705-ac720100bfe6",
        "productName": "Gazyağı",
        "productShortName": "GY",
        "amount": 38.26,
        "productCode": "A110"
      },
      {
        "id": "c8fdea87-788d-4c02-b465-ac720100bfe6",
        "productName": "Motorin UltraForce",
        "productShortName": "MT_ULT",
        "amount": 47.13,
        "productCode": "A121"
      },
      {
        "id": "659dbdcd-394f-4061-9408-ac720100bfe6",
        "productName": "Motorin EcoForce",
        "productShortName": "MT_ECO",
        "amount": 47.13,
        "productCode": "A128"
      },
      {
        "id": "e0ffc8a1-da57-4695-9334-ac720100bfe6",
        "productName": "Kalorifer Yakıtı",
        "productShortName": "Kalori",
        "amount": 32.37,
        "productCode": "A201"
      },
      {
        "id": "8a37b6a8-29c9-46ea-933c-ac720100bfe6",
        "productName": "Fuel Oil",
        "productShortName": "F. Oil",
        "amount": 29.08,
        "productCode": "A212"
      },
      {
        "id": "5c215408-dd3b-478d-a13d-ac720100bfe6",
        "productName": "Yüksek Kükürtlü Fuel Oil",
        "productShortName": "YKFO",
        "amount": 25.65,
        "productCode": "A218"
      }
    ]
  }
]
