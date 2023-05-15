/**
 * 
 */
document.querySelector(".btn-primary").addEventListener("click",()=>{
	location.href = listPath;
})
document.querySelector(".btn-danger").addEventListener("click",()=>{
	location.href = removePath+"?code="+code;
})
const form = document.querySelector("form");
form.addEventListener("submit",(e)=>{
	e.preventDefault;
	const code = document.querySelector("#code");
	const price = document.querySelector("#price");
	
	if(code.value === "" || code.value.length !== 4){
		alert("코드를 확인해 주세요");
		code.select();
		return;
	}else if(price.value === "" || isNaN(price.value)){
		alert("가격을 확인해 주세요");
		price.select();
		return;
	}
	form.submit();
})