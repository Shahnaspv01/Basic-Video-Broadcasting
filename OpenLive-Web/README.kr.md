웹용 라이브 열기

이 자습서는 다음을 사용하여 라이브 앱을 빠르게 만드는 방법을 보여줍니다 Agora Web SDK.

전제 조건
Node.js 6.9.1+
지원하는 웹 서버 SSL (https)

빠른 시작
이 섹션에서는 샘플 응용 프로그램을 준비, 빌드 및 실행하는 방법을 보여줍니다.

획득 App ID
샘플 애플리케이션을 빌드하고 실행하려면App ID:

1.에서 개발자 계정 만들기 agora.io.가입 절차를 마치면 대시 보드로 리디렉션됩니다.
    2.새 프로젝트를 만들어 저장해야합니다 App ID.
2.왼쪽의 대시 보드 트리에서 Projects > Project List.
3.을 살리다 App ID 나중에 사용할 수 있도록 대시 보드에서.
4.임시 생성 Access Token 채널 이름이 지정된 대시 보드 페이지에서 (24 시간 동안 유효) 나중에 사용할 수 있도록 저장하십시오.
5.를 엽니 다 src/utils/Settings.js 파일.파일 맨 아래에서 <#YOUR APP ID#> 와 더불어 App ID,대시 보드에서 생성 된 임시 액세스 토큰으로 토큰 변수를 지정하십시오.
6.노트:두는 App ID/Token 작은 따옴표 또는 큰 따옴표 내.

   const 내보내기 APP_ID_LIVE = <#YOUR APP ID#>;
   // 활성화하지 않은 경우 토큰을 null로 할당 app certificate.
   const 내보내기 Token = "<#YOUR TEMP TOKEN HERE#>";

종속성 설치 및 Agora Video SDK 통합
1.터미널 앱을 사용하여 프로젝트 디렉토리에 install 명령을 입력하십시오. 이 명령은 샘플 애플리케이션을 실행하는 데 필요한 라이브러리를 설치합니다.

# 의존성 설치
npm install

2.run dev 또는 run build 명령을 입력하여 애플리케이션을 시작하십시오. run dev 명령은 개발 목적입니다.

# 핫 리로드로 봉사하다 localhost:8080
npm run dev

그만큼 run build 명령은 생산 목적을위한 것이며 코드를 축소합니다.

# 최소화로 생산을위한 구축
npm run build

3.기본 브라우저가 열리고 샘플 응용 프로그램이 표시됩니다. 참고 : 경우에 따라 브라우저를 열고 입력해야 할 수도 있습니다 http://localhost:8080 as the URL.

자원
Document Center에서 전체 API 문서를 찾을 수 있습니다.
문제가되는이 데모에 대한 버그를 제기 할 수 있습니다

특허
The MIT License (MIT)
