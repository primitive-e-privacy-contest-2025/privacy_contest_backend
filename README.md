# 개인정보 통합 관리 및 접근 제어 플랫폼 (정보 보안 공모전 MVP)

## 🌟 프로젝트 개요 (Motive & Introduction)

현행 Web2 환경에서는 기업이 사용자의 다양한 개인정보(신원 정보, 활동 내역 등)를 수집하여 서비스 제공 및 광고에 활용합니다. 그러나 이러한 데이터는 유출 시 심각한 피해를 초래할 수 있으며, 기업의 관리 소홀로 인해 제3자에게 무단으로 제공될 위험도 존재합니다.

본 프로젝트는 이러한 문제의식을 바탕으로 **사용자에게 데이터 주권을 돌려주고, 데이터의 보안성과 투명성을 높이는 것**을 목표로 하는 "개인정보 통합 관리 및 접근 제어 플랫폼"의 MVP(Minimum Viable Product)입니다. 궁극적으로는 블록체인 기술을 활용한 탈중앙화된 데이터 관리를 지향하지만, 본 MVP는 Spring Boot 기반의 중앙화된 서버로 핵심 기능을 구현하여 그 가능성을 검증하고자 합니다.

이 아이디어는 **정보 보안 공모전에 제출된 MVP** 버전입니다.

## 💡 핵심 컨셉 (Core Concepts)

* **사용자 중심 데이터 제어:** 사용자는 자신의 개인정보에 대해 어떤 기업의 어떤 서비스가 접근할 수 있는지 직접 제어(승인, 거부, 철회)합니다.
* **동의 기반 기업 접근:** 기업은 사용자의 명시적인 동의 하에만 개인정보 및 활동 이력에 접근할 수 있습니다. 각 기업 서비스는 고유 API 키를 발급받아 인증된 접근을 수행합니다.
* **투명한 접근 이력 로깅:** 기업이 사용자의 데이터에 접근하는 모든 시도는 상세히 기록됩니다 (`APICallLogs`). 사용자는 자신의 정보가 언제, 누구에 의해, 어떤 목적으로 조회되었는지 확인할 수 있습니다.

## ✨ 주요 기능 (Implemented Features - MVP)

* **사용자 관리 (`UserController`):**
    * 일반 사용자 회원가입 및 로그인 (ID/PW 기반).
    * 사용자 정보 조회, 수정 및 탈퇴.
* **기업 사용자 관리 (`CorporateController`):**
    * 기업 회원가입 및 로그인 (ID/PW 기반).
    * 기업 정보 조회, 수정 및 탈퇴.
* **서비스 관리 (`ServiceController`):**
    * 기업 사용자는 자사 서비스를 시스템에 등록하고, 서비스 설명을 추가할 수 있습니다.
    * 서비스 등록 시, 해당 서비스에 대한 고유 **API Key가 자동 생성**되어 발급됩니다. 이 API Key는 기업이 사용자 데이터에 접근 시 인증 수단으로 사용됩니다.
    * 기업은 자신이 등록한 서비스 목록을 조회할 수 있습니다.
* **사용자 서비스 접근 권한 관리 (`UserServiceAccessController`):**
    * 기업(서비스)은 특정 사용자에게 데이터 접근 권한을 요청할 수 있습니다 (상태: `PENDING`).
    * 사용자는 자신에게 들어온 서비스 접근 요청 목록을 확인하고, 각 요청에 대해 **접근을 허가(`GRANTED`)하거나 거부(`DENIED`)**할 수 있습니다.
    * 이미 허가된 접근 권한도 사용자가 원하면 언제든지 철회(실질적으로 `DENIED` 상태로 변경)할 수 있습니다.
* **사용자 활동 이력 관리 (`UserActivityController`):**
    * 기업/서비스는 API를 통해 특정 사용자의 서비스 내 활동 이력(예: 로그인, 상품 조회, 구매 등)을 시스템에 기록(`PUT`)할 수 있습니다.
    * 사용자는 자신이 허용한 서비스에 한해 자신의 활동 이력을 조회(`GET`)할 수 있습니다.
    * 사용자는 자신의 특정 활동 이력을 선택하여 삭제(`DELETE`)할 수 있는 권한을 가집니다.
    * 기업은 **발급받은 API Key와 사용자의 동의**를 바탕으로 해당 사용자의 활동 이력을 조회(`GET .../corp`)할 수 있습니다.
* **API 호출 로그 (`ApiCallLogController`, `ApiCallLogService`):**
    * 기업/서비스가 API Key를 사용하여 사용자의 활동 이력(`UserActivity`)을 조회할 때, 해당 **API 호출 내역(요청 시간, 엔드포인트, 응답 상태, 응답 요약, 소요 시간 등)이 자동으로 기록**됩니다.
    * 사용자는 자신의 `userId`를 기준으로 자신과 관련된 API 호출 로그 목록을 조회하여, 어떤 기업/서비스가 언제 자신의 데이터에 접근했는지 투명하게 확인할 수 있습니다.

## 🛠️ 기술 스택 (Technologies Used)

* **언어:** Java 17+
* **프레임워크:** Spring Boot 3.x
    * Spring Web: RESTful API 개발
    * Spring Data JPA: 데이터베이스 연동 및 ORM
    * Spring Security: 기본적인 보안 설정 (CORS, CSRF 비활성화 등 - MVP 수준)
* **데이터베이스:** 관계형 데이터베이스 (H2, MySQL, PostgreSQL 등 - `application.properties` 설정에 따름)
* **API 문서화:** Swagger (OpenAPI 3.0)
* **빌드 도구:** Gradle 또는 Maven

## 📄 API 엔드포인트 (API Endpoints)

본 서버는 RESTful API를 제공하며, 상세한 API 명세는 Swagger UI를 통해 확인할 수 있습니다.
서버 실행 후, 기본적으로 `/swagger-ui/index.html` 경로에서 API 문서를 참조할 수 있습니다. (서버 URL은 `SwaggerConfiguration.java` 참조)

주요 API 리소스 그룹은 다음과 같습니다:
* `/user/**`: 일반 사용자 관련 API
* `/corporate/**`: 기업 사용자 관련 API
* `/services/**`: 기업 서비스 등록 및 조회 API
* `/user/{userId}/services/{serviceId}/activity/**`: 사용자 활동 이력 관리 API
* `/access/user/{userId}/services/**`: 사용자 서비스 접근 권한 관리 API
* `/AccessLog/user/{userId}`: 기업의 사용자 데이터 접근 로그 조회 API

## 📁 데이터베이스 스키마 (Entities)

주요 JPA 엔티티는 다음과 같습니다:
* `UserPersonalInfo`: 일반 사용자 정보
* `CorporateUsers`: 기업 사용자 정보
* `Services`: 기업이 등록한 서비스 정보 (API Key 포함)
* `UserServiceAccess`: 사용자의 서비스별 접근 허용/거부 상태
* `UserActivity`: 사용자의 서비스 내 활동 이력
* `APICallLogs`: 기업/서비스의 사용자 데이터 접근(API 호출) 로그

(상세 필드는 제공된 JPA Entity 코드 참조)

## ⚙️ 설정 및 실행 (Setup & Running the Server)

### 사전 준비 사항

1.  **JDK**: Java 17 이상 설치
2.  **빌드 도구**: Gradle 또는 Maven 설치
3.  **데이터베이스**: MySQL, PostgreSQL, H2 등 관계형 데이터베이스 서버 준비 (또는 H2 인메모리 사용 가능)

### 설정

1.  **데이터베이스 연결**:
    * `src/main/resources/application.properties` (또는 `application.yml`) 파일에 사용하는 데이터베이스 종류에 맞춰 연결 정보를 설정합니다. (URL, username, password, driver 등)
    ```properties
    # 예시 (MySQL)
    # spring.datasource.url=jdbc:mysql://localhost:3306/your_db_name?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8
    # spring.datasource.username=your_db_user
    # spring.datasource.password=your_db_password
    # spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

    # spring.jpa.hibernate.ddl-auto=update # 개발 환경: update | 운영 환경: validate 또는 none
    # spring.jpa.show-sql=true
    # spring.jpa.properties.hibernate.format_sql=true
    ```

### 실행

1.  **프로젝트 빌드**:
    * Gradle: `./gradlew build`
    * Maven: `./mvnw package`
2.  **서버 실행**:
    * 빌드된 JAR 파일 실행: `java -jar build/libs/privacy_contest-0.0.1-SNAPSHOT.jar` (JAR 파일명은 실제 빌드 결과에 따라 다를 수 있습니다.)
    * 또는 IDE(IntelliJ, Eclipse STS 등)에서 Spring Boot 애플리케이션 직접 실행.

실행 후, `SwaggerConfiguration.java`에 설정된 서버 URL (예: `https://primitive-backend.run.goorm.site/` 또는 로컬 실행 시 `http://localhost:8080/`)의 `/swagger-ui/index.html` 경로에서 API 문서를 확인할 수 있습니다.

## 🚀 향후 발전 방안 (Future Enhancements - From Proposal)

본 MVP는 핵심 기능을 중심으로 구현되었으며, 제안서에 담긴 아이디어를 바탕으로 다음과 같은 방향으로 발전시킬 수 있습니다:

* **블록체인 기술 통합:** 데이터 접근 이력 및 권한 상태의 일부를 블록체인에 기록하여 탈중앙화, 위변조 방지, 투명성 강화.
* **고급 사용자 인증:** OAuth2 (Google 로그인), 통신사 PASS, SMS OTP 등 다양한 인증 방식 도입.
* **데이터 수익화 모델 구현:** 사용자가 자신의 데이터를 기업에 제공하고 보상을 받는 메커니즘 개발.
* **파일 저장 및 관리 기능:** 개인정보 관련 파일(예: 동의서, 증명서 등)을 안전하게 저장하고 관리하는 기능 (`FileStorages` 테이블 활용).
* **세분화된 데이터 접근 제어:** 정보 항목별 접근 권한 제어 등 더욱 상세한 프라이버시 설정 기능.
* **프로덕션 환경을 위한 보안 강화:** `SecurityConfig`의 `permitAll` 정책 수정, HTTPS 적용, API Key 및 세션 토큰 관리 강화.

## 🔐 보안 고려 사항 (Security Considerations - MVP)

* 본 MVP의 `SecurityConfig`는 개발 편의를 위해 모든 요청을 허용하고 CORS 설정을 광범위하게 적용했습니다. 실제 운영 환경에서는 엔드포인트별 접근 제어, HTTPS 강제, 보다 엄격한 CORS 정책 등 보안 강화가 필수적입니다.
* 비밀번호는 제안서에 언급된 바와 같이 SHA-256 등의 안전한 해시 함수로 암호화하여 저장해야 합니다 (구현 시 확인 필요).
* API Key는 서비스별로 발급되며, 기업의 중요한 인증 정보이므로 안전하게 관리되어야 합니다.
