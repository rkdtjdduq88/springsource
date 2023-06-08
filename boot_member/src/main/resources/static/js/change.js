/**
 * 기존 비밀번호, 새 비밀번호, 새 비밀번호 확인 내용 비어있지않도록 확인
 * 새 비밀번호 === 새 비밀번호 확인
 */
const form = document.querySelector("form");
form.addEventListener("submit",(e)=>{
	e.preventDefault();
	
	const crPwd = document.querySelector("#floatingPassword1");
	const newPwd = document.querySelector("#floatingPassword2");
	const cfPwd = document.querySelector("#floatingPassword3");
	
	if(crPwd.value === ""){
		alert("");
		crPwd.select();
		return;		
	}else if(newPwd.value === ""){
		alert("");
		newPwd.select();
		return;
	}else if(cfPwd.value === ""){
		alert("");
		newPwd.select();
		return;
	}
	
	if(newPwd.value !== cfPwd.value){
		alert("새 비밀번호와 새 비밀번호 확인이 일치하지 않습니다.");
		cfPwd.select();
		return;
	}
	
	form.submit();
})