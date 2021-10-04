# 나홀로 웹에 코드 저장소
* ! 2021.10.03 요금 문제로 EC2 인스턴스 삭제, RDS 삭제, S3 버킷 삭제 
* 프로젝트 주소 : http://ec2-3-35-129-187.ap-northeast-2.compute.amazonaws.com/
* 개발자 로컬로 접속했을 때에는 구글/ 네이버 로그인 기능에 이상이 없으나, 외부 사용자의 경우 **네이버 로그인은 애플리케이션 개발 검수를 받지 않아 인증이 되지 않는 상황**입니다.
* 외부 사용자의 경우 글쓰기 기능 사용은 가입 신청을 하신 뒤, 개발자가 권한을 부여하면 가능합니다.

## 프로젝트 설명
스프링부트와 AWS로 혼자 구현한 웹 서비스입니다. 다음과 같은 기능이 구현되어 있습니다.

### 1. 머스테치로 화면 구성
* 머스테치는 서버 사이드 템플릿 엔진의 일종이다. 
  * v.s. 클라리언트 템플릿 엔진: 브라우저 위에서 자바 스크립트 코드가 작동한다. 서버에서 Json 혹은 xml 형식의 데이터만 전달하고 클라이언트에서 조립한다. 
* 화면의 역할에 충실하고, 로직 코드를 사용할 수 없어서 View와 서버의 역할이 명확하게 분리된다. 인텔리제이 커뮤니티 버전에서도 설치 가능한 플러그인이 존재한다. 
  * v.s. 타임리프: 스프링 공식 서버 사이드 템플릿 엔진이며 기능이 많지만 문법이 어렵다. 태그에 속성으로 템플릿 기능을 사용한다. 
  * View: 사용자에게 화면을 출력한다.

### 2. 스프링부트에서 Spring Data JPA로 데이터베이스를 작동
* Spring Data JPA: 구현체를 추상화 시킨 모듈, 스프링에서 제공하는 프레임워크
* Hibernate: 인터페이스의 구현체
* JPA: 자바 ORM 기술에 대한 표준 인터페이스로 자바에서 제공.
  * JPA 내부에서 JDBC API를 사용하며 JPA는 어플리케이션과 JDBC 사이에서 동작한다.
  * DAO에게서 Entity를 받아서 분석한 뒤 SQL문을 생성하고 JDBC API로 DB로 날린다.
  * 객체 중심의 개발을 가능하게 한다.
* ORM: SQL 쿼리가 아니라 매서드로 데이터를 조작하도록 하는 것. 객체간 관계를 바탕으로 SQL을 자동으로 생성한다.

### 3. 스프링부트에서 단위 테스트 코드 작성
* 단위 테스트란? TDD의 첫번째 단계인 기능 단위로 테스트 코드를 작성하는 것을 의미한다. 일반적으로 매서드 레벨의 가장 작은 단위의 테스트이다.
  * 왜 하는가? 1. 과도한 설계를 피하고 간결성이 증대된다. 2. 테스트 코드 자체가 동작하는 문서의 역할을 한다. 3. 의존성 관리가 편해진다.
* TDD란? 테스트 코드를 먼저 짜고 그 이후에 코드를 짜는 테스트 주도 개발을 의미한다.
  * 왜 하는가? 1. 테스트 커버리지가 높아진다(미루지 않는다.). 2. 오버 엔지니어링을 방지한다. 3. 설계에 대한 피드백이 빠르다. 

### 4. 스프링 시큐리티와 OAuth 2.0으로 로그인 기능 구현
* 스프링 시큐리티: 스프링 기반의 애플리케이션에서 보안을 위한 표준 프레임워크
  * 도메인에서 사용자 정보를 담당할 User 클래스, 사용자의 권한을 관리할 Role Enum 클래스, User의 CRUD를 책임질 UserRepository 생성 이후 `/config/auth`에 작성
  * 스프링 시큐리티는 하위 필드를 명시할 수 없다.  
    * 네이버 응답값 최상위 필드는 resultCode, message, response이므로, user_name을 response로 지정한 뒤, 자바 코드로 response의 id를 user_name으로 지정한다.
* OAuth2.0: 외부의 서비스 기능을 다른 어플리케이션에서 사용할 수 있도록 하는, 인증을 위한 오픈 스탠다드 프로토콜. 
  * 2.0 변경점: 1. url 주소 필요 없이 클라이언트 인증 정보만 필요 2. Enum으로 설정

### 5. AWS EC2/ RDS
* 클라우드 서비스: 인터넷(클라우드)를 통하여 서버, 스토리지(파일 저장소), 데이터베이스, 네트워크, 소프트웨어, 모니터링 등의 컴퓨팅 서비스를 제공하는 것.
  * Iaas(Infrastructure as a Service): EC2, S3
  * Paas(Platform as a Service): 빈스톡, 헤로쿠
  * Saas(Software as a Service): 구글 드라이브, 드랍박스
* EC2: AWS에서 제공하는 성능, 용량 등을 유동적으로 사용할 수 있는 서버.
  * 인스턴스: EC2 서비스에 생성된 가상 머신
    * 자바 8 설치: `sudo yum install -y java-1.8.0-openjdk-devel.x86_84` 로 자바 설치 후, `sudo /usr/sbin/alternatives --config java`로 버전 변경 
    * 타임존 변경: `sudo rm /etc/localtime``sudo ln -s /usr/share/zoneinfo/Asia/Seoul /etc/localtime`으로 변경
    * 호스트 네임 변경: `sudo vim /etc/sysconfig/network`에서 HOSTNAME 변경 후, `sudo reboot`로 서버 재부팅. `sudo vim /etc/hosts` 변경된 HOSTNAME 등록.
    * RDS 접속: `sudo yum install mysql`로 MySQL CLI 설치 `mysql -u 계정 -p -h Host주소`로 접속. `show databases;` 쿼리로 확인.
    * 프로젝트 Clone: `sudo yum install git`로 깃 설치. `mkdir ~/app && mkdir ~/app/step1`로 디렉토리 생성 후 `cd ~/app/step1`로 이동 후 git clone 진행.
    * 배포 스크립트 만들기: `vim ~/app/step1/deploy.sh`로 배포 스크립트 작성 후 `./deploy.sh`로 실행 `vim nohup.out`에서 로그 확인.
    * 시큐리티 파일 등록: `vim /home/ec2-user/app/application-oauth.properties`
    * RDS 접속 설정: `vim ~/app/application-real-db.properties`
    * IAM 역할 생성
    * CodeDeploy 에이전트 설치: `aws s3 cp s3://aws-codedeploy-ap-northeast-2/lastest/install .--region api-northeast-2` `chmod +x ./install`로 install 파일에 실행 권한 추가. `sudo ./install auto`로 설치.
    * Travis CI/ S3/ AWS CodeDeploy 연동: `mkdir ~/app/step2 && mkdir ~/app/step2/zip` 디렉토리 생성. `appsepc.yml`과 `.travis.yml`로 연동 설정.
    * 배포 자동화 구성: `/scripts/deploy.sh`에 git pull을 통해 직접 빌드했4던 부분 제거, Jar를 실행하는 코드 추가.
    * CodeDeploy 로그 확인: `/opt/codedeploy-agent/deployment-root`
    * 엔진엑스 보안 그룹 추가
    * 엔진엑스 설치: `sudo yum install nginx`로 설치, `sudo service nginx start`로 실행. `sudo vim /etc/nginx/nginx.conf`의 location/에서 해당 설정 추가.
    * 엔진엑스 설정 수정: `sudo vim /ect/nginx/conf.d/service-url.inc`에 `set $service_url http://127.0.0.1:8080;`입력, `sudo vim /etc/nginx/nginx.conf`에 location / 변경. 재시작: `sudo service nginx restart`
    * 무중단 배포 스크립트 작성: `mkdir ~/app/step3 && mkdir ~/app/step3/zip`르 디렉토리 설정 `appspec
  * pem 키: 비밀 키
    * 인스턴스는 지정된 pem 키와 매칭되는 공개키를 가지고 있어 해당 pem키 외에는 접근을 허용하지 않는다. 
    * /.shh/ 디렉토리로 pem 파일을 옮겨 놓으면 ssh 실행 시 pem 키 파일을 자동으로 읽어 접속을 진행한다.
    * putty(윈도우즈)는 puttygen으로 pem 키를 ppk 파일로 변환한 뒤 사용해야 한다. 
  * EIP(Elastic IP): AWS의 고정 IP
* RDS: AWS에서 제공하는 클라우드 기반 관계형 데이터베이스. 하드웨어 프로비저닝, 데이터베이스 설정, 패치 및 백업 등 자동화. 
  * MariaDB: MySQL기반으로 만든 RDBS. Amazon Aurora로 교체 용이.
  * 타임존, Character Set(uft8mb4), Max Connection(150)
  * 인텔리제이의 Database 플러그인에서 접속이 되지 않아(검색 결과 플러그인 자체의 문제로 보임), 직접 EC2에서 RDS에 접속하여 스키마, 테이블 작성.

### 6. Travis CI/ S3/ AWS CodeDeploy/ Nginx
* CI, CD: Git에 PUSH가 되면 자동으로 테스트와 빌드가 수행되어 안정적인 배포 파일을 만들고, 빌드 결과를 자동으로 운영 서버에 무중단 배포까지 진행되는 과정.
* Travis CI: 깃허브에서 제공하는 CI. 
  * `.travis.yml` YAML 파일에 설정
  * S3에 jar 전달
  * AWS CodeDeploy에 배포 요청
  * Environment Variables로 AWS_ACCESS_KEY, AWS_SECRET_KEY 키 값 등록
* S3: AWS에서 제공하는 파일 서버. 배포 파일들을 관리하는 기능.
  * AWS CodeDeploy에 jar 전달
  * IAM: AWS에서 제공하는 인증 서비스
  * '모든 퍼블릭 액세스 차단'
* AWS CodeDeploy
  * IAM 역할 생성 
* Nginx: 웹 서버, 리버스 프록시, 캐싱, 로드 밸런싱, 미디어 스트리밍 등을 위한 오픈 소스 소프트 웨어.
  * 리버스 프록시: 외부의 요청을 받아 백엔드 서버로 요청을 전달하는 것
  * 하나의 EC2에 엔진엑스 1대(80, 443 포트 할당)와 스프링 부트 Jar 2대(8081, 8082 포트로 실행) 사용
  * ProfileController, ProfileControllerUnitTest, ProfileControllerTest 작성.
  * SecurityConfig 클래스에 `"/profile"` 추가
  * 8081 포트 설정 `application-real1.properties`, 8082 포트 설정은 `application-real2.properties`에서 설정
  * appspec.yml 및 `/scripts`의 배포 스크립트들 수정
  * build.gradle 버전에 `+new Date().format(yyyyMMddHHmmss`) 추가.

## 환경 및 세팅
* openjdk version : "1.8.0_302"
* springBoot version : '2.1.9.RELEASE'
* 그 외 자세한 세팅은 build.gradle 참조
