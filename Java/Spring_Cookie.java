/*
	- 작성자 : 이태호 (Taeho Lee)
	- 이메일 : developdestroyer@gmail.com
	- 작성일 : 2018-10-30

	 - 제목 : 초간단 쿠키만들기~
*/

Boolean isHaveCookie = false;

Cookie[] getCookie = req.getCookies();
if(getCookie != null){
	for(int i=0; i<getCookie.length; i++){
		Cookie c = getCookie[i];
		String name = c.getName(); // 쿠키 이름 가져오기
		String value = c.getValue(); // 쿠키 값 가져오기
		if(value.equals("visitor")) {
			isHaveCookie = true;
			break;
		}
	}
}
