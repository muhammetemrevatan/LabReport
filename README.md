---
# Project Basic Information
title: "Laboratory Report Management System"
description: "A comprehensive laboratory reporting system with role-based authentication, built using Spring Boot and PostgreSQL, featuring patient diagnosis and report management"
showcase: true
order: 7

# Technologies
technologies:
  - "Java 17"
  - "Spring Boot"
  - "PostgreSQL"
  - "Thymeleaf"
  - "Bootstrap"
  - "Maven"
  - "JPA"

# Features
features:
  - "Role-based Authentication (Admin/User)"
  - "Patient Diagnosis Management"
  - "Report Generation and Storage"
  - "N-Layer Architecture"
  - "Database Relationship Management"
---

# Özet
Proje, laboratuvar'da konulan tanılar için rapor ekleme uygulamasıdır. Laborant hastaların tanısını koyduktan sonra hastalıkla ilgili raporu sisteme kayıt eder. Proje yetkilendirme mekanizması içermektedir. User yetkisine sahip olan kullanıcı ekleme ve silme yapabilirken güncelleme yapamaz. Admin her ikisini yapabilir. Temel create,update ve delete işlemlerini içerir.

# Başlarken
 
#### Proje nasıl oluşturuldu?
https://start.spring.io/ sayfası üzerinden aşağıdaki şekilde görüldüğü gibi kullanacağımız dependency'leri ekleyip gerekli isimlendirmeleri yaparak projeyi oluşturmamız gerekiyor.

![springiniti](https://user-images.githubusercontent.com/44151025/181365946-f86a7763-3b43-4ecf-bddb-aebcb701ef0a.PNG)

#### Neden lombok?
Getter ve setter'ları bizim için otomatik olarak reflaction yöntemi ile hallediyor. Boş ve dolu constructer oluşturmamıza yardımcı oluyor. Daha az ve temiz kod yazmak için bu projeye severek kullandığım lombok kod üretme kütüphanesini ekledim.

# Projede kullanılan teknoloji ve metotlar
Spring, Tyhmeleaf, JPA, Maven, Bootstrap teknolojileri kullanılarak oluşturulmuştur. N-Layer Architecture (Katmanlı Mimari) anlayışı kullanılmıştır. Proje Entities, dataAccess, core, business ve api.controllers katmanlarından oluşuyor.

![nlayered](https://user-images.githubusercontent.com/44151025/181367943-61cee045-d3d5-488c-9150-a689b21f10fe.PNG)

> - Entities Katmanı : Varlıkları yapılandırdığımız katman. Veritabanımızda bulunan tabloların karşılığı olabilecek özellik ve operasyon tutan sınıfları buraya kodlarız. Yardımcı bir katmandır.
> - dataAccess Katmanı : Veriye erişim sağladığımız katman. Biraz daha açmak gerekirse bu projede kullanıdığımız postgresql veritabanına bağlanmak ve oradan verileri çekmek için kullandığımız katman. Bu katmanda veriye erişim dışında asla bir kod yazılmaz.
> - Core Katmanı : Projelerde kullanılabilecek farklı yapıları bu katmanda kodlarız. Örneğin mernis sistemi eklememiz gerekirse bu katmanda kodlayabiliriz.
> - Business Katmanı : İş kurallarını yazdığımız katman. DataAccess katmanı ile api katmanı arasında iki yönlü bir bağlantı kurar. DataAccess üzerinden aldığı verileri isterlere göre şartlandırarak  api.controllers katmanına taşır.
> - Api.Controllers Katmanı : Kullanıcı ile etkileşim kurduğumuz katman. Burada sadece arayüze gidecek olan kodlar yazılır.

#### Database

Bu proje küçük çaplı bir proje olduğu için ilişkisel veritabanı kullanmak daha doğru olacaktır. Postgresql son zamanlarda popülerlik kazandığı için bu veritabanını kullanma kararı aldım. Aşağıdaki şekilde görüldüğü gibi fiziksel rapora ait bir resim, 7 haneli hastane kimlik numarası gibi isterler yerine getirilmiştir. Ayrıca bir rapor yalnızca bir laborant tarafından tanımlanmış olmalı. Bir laborant ise n tane rapor tanımlayabilir.
Proje diyagramı aşağıda gösterişmiştir.

![DatabaseDiagram](https://user-images.githubusercontent.com/44151025/181372506-f265f1a6-7a56-4232-a358-7c2473d4c60a.PNG)

Sonradan dahil etmeyi düşündüğüm authentication işlemleri için login tablosu oluşturdum. 
###### Not: 
Projenin çalıştırılabilmesi için pgAdmin 14 indirilip kurulması ve ardından aşğıdaki script kullanılarak lapreport isimli bir database oluşturulması gereklidir.
#### Database Script


>SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;
>
>SET default_tablespace = '';
>
>SET default_table_access_method = heap;
>
>CREATE TABLE public.laborant (
    id integer NOT NULL,
    full_name character varying(50),
    hospital_identity_no character varying(7)
);
>
>ALTER TABLE public.laborant OWNER TO postgres;
>
>ALTER TABLE public.laborant ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.laborant_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
>
>CREATE TABLE public.login (
    id integer NOT NULL,
    username character varying(20),
    password character varying(20),
    laborant_id integer
);
>
>ALTER TABLE public.login OWNER TO postgres;
>
>ALTER TABLE public.login ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.login_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
>
>CREATE TABLE public.patient (
    id integer NOT NULL,
    full_name character varying(50),
    patient_tc character varying(11)
);
>
>ALTER TABLE public.patient OWNER TO postgres;
>
>ALTER TABLE public.patient ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.patient_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
>
>CREATE TABLE public.report_definition (
    id integer NOT NULL,
    diagnosis_detail character varying(200),
    diagnosis_title character varying(50),
    report_date character varying(50),
    report_image character varying(200),
    laborant_id integer,
    patient_id integer
);
>
>ALTER TABLE public.report_definition OWNER TO postgres;
>
>ALTER TABLE public.report_definition ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.report_definition_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
>
>ALTER TABLE ONLY public.laborant
    ADD CONSTRAINT laborant_pkey PRIMARY KEY (id);
>
>ALTER TABLE ONLY public.login
    ADD CONSTRAINT login_pkey PRIMARY KEY (id);
>
>ALTER TABLE ONLY public.patient
    ADD CONSTRAINT patient_pkey PRIMARY KEY (id);
>
>ALTER TABLE ONLY public.report_definition
    ADD CONSTRAINT report_definition_pkey PRIMARY KEY (id);
>
>ALTER TABLE ONLY public.login
    ADD CONSTRAINT login_laborant_id_fkey FOREIGN KEY (laborant_id) REFERENCES public.laborant(id);
>
>ALTER TABLE ONLY public.report_definition
    ADD CONSTRAINT report_definition_laborant_id_fkey FOREIGN KEY (laborant_id) REFERENCES public.laborant(id);
>
>ALTER TABLE ONLY public.report_definition
    ADD CONSTRAINT report_definition_patient_id_fkey FOREIGN KEY (patient_id) REFERENCES public.patient(id);




# Gereklilikler
Bu projede java 17 ve maven 3.8 versiyonlarını kullandım. Olası bir uyumsuzluk olmaması için projeyi çalıştırmadan önce versiyonları kontrol etmek iyi olacaktır.

![mvnandjavaversion](https://user-images.githubusercontent.com/44151025/181373477-c0719afa-f5f9-49fb-ac78-afbe7157e748.PNG)

# Nasıl kurulur?

İlk önce projenin kaynak dosyalarını indirmemiz gerkiyor. İsterseniz git kullanarak veya doğrudan https://github.com/muhammetemrevatan/LabReport adresi üzerinizden aşağıdaki görselde 'Download ZIP' yazısına tıklayarak projeyi bilgisayara indirebilirsiniz. 

![gitpath](https://user-images.githubusercontent.com/44151025/181375132-a3f970c9-e5b7-49c2-86a6-47a4a3f9e8b2.PNG)

İndirilen dosya bir rar dosyası olacağı için dosayayı indirdikten sonra zip içerisinden çıkarmamız gerekecektir.

![klosereayikla](https://user-images.githubusercontent.com/44151025/181375832-2e340731-a34c-428e-8eae-2647684e0e60.png)

Bu işlemlerden sonra projeyi istediğiniz bir workspace üzerine, bilgisayarınzı herhangi bir klasörüne taşıyabilir veya bu şekilde bırakabilirsiniz.
Ayrıca bilgisayarınızda bir maven kurulu değilse https://maven.apache.org/download.cgi bu adresi kullanarak bir maven kurmanız gerekmektedir. 

![maven](https://user-images.githubusercontent.com/44151025/181376384-d290c186-b610-418d-ab2a-55f53a3309f4.PNG)

İşaretlemiş olduğum zip uzantılı dosyayı bilgisayarınıza indirip yukarı göstermiş olduğum gibi zip içerisinden çıkarttıntan sonra bilgisayarınızda  C:\Program Files dizinine  apache-maven-3.8.6 isimli klasörü ekleyebilirsiniz.
Projeyi IDE'siz yürütebilmek adına cmd üzerinden bazı kodlar yazmamız gerekiyor. Compile etmeden önce projeyi indirdiğimiz dizine gitmeliyiz. 
> - cd C:\Users\vatan\Desktop\report

![location](https://user-images.githubusercontent.com/44151025/181377479-e3f6658e-8251-4acb-9d72-f6a9a0e850b8.PNG)

Bu dizin içerisinden maven dosyalarına erişimi kontrol etmek için tekrar versiyon kontrolü yapabiliriz. 
> - mvn -v

![mavencontrol](https://user-images.githubusercontent.com/44151025/181377685-a3c700cc-eb12-432a-85cf-b8ce0700dd77.PNG)

Projeyi temiz bir şekilde çalıştırabilmek için projeyi build etmemiz önemlidir. Bu yüzden komut satırına mvn install yazıyoruz.
> - mvn install

![build](https://user-images.githubusercontent.com/44151025/181378311-46c9aab8-217c-4e89-a674-ba56a533b40b.PNG)

Projeyi çalıştırmak için jar dosyasının bulunduğu dizine gitmeliyiz. cd target yazarak target klasörünün içerisine giriyoruz.
> - cd target

![gototargetfile](https://user-images.githubusercontent.com/44151025/181378736-0c01a7a1-6d78-4298-b1e9-bff48a0854a2.PNG)

Artık projeyi çalıştırabiliriz bunu için yapmamız gereken son şey aşağıdaki kodu yazıp projeyi koşturmak.
> - java -jar report-0.0.1-SNAPSHOT.jar

![runnig](https://user-images.githubusercontent.com/44151025/181379030-1a29a645-d506-4f50-a342-b7bcc82113c2.PNG)

Ve artık tamam. Proje başarılı bir şekilde çalışıyor. localhost:8080 üzerinde yayında.
# Proje kullanımı esnasında
>Browser'ın URL kısmına http://localhost:8080/api/reportdefinition/reportdetailspage yazarak bağlanıyoruz. Bir yetkilendirme mekanızması olduğu için admin veya user olarak giriş yapmamız gerekiyor.
> - admin için : Kullanıcı Adı: **admin** / Şifre: **admin**
> - user için : Kullanıcı Adı: **user** / Şifre: **user**  
>
Bu şekide sisteme giriş yaparak projeyi deneyebilirsiniz. Eklemek istedikleriniz ve eleştirileriniz benim için çok kıymetlidir. Zaman ayırdığınız için teşekkürler.

Projeyi hazırlayan : Muhammet Emre VATAN
- Linkedin : https://www.linkedin.com/in/muhammetemrevatan
- Mail : <muhammetemrevatan@gmail.com>

# Kaynaklar

> - mvnrepository.com
> - spring.io
> - thymeleaf.org/documentation.html
> - swagger.io/docs
> - w3schools.com
> - tackoverflow.com
> - docs.spring.io/spring-data/jpa/docs/current/reference/html/
> - baeldung.com/?s=hibernate
> - dzone.com/articles/compiling-and-running-java
