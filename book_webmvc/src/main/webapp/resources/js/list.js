/**
 * 
 */
const form = document.querySelector(".justify-content-center");
form.addEventListener("submit",(e)=>{
	e.preventDefault();
	// 검색기준 요소 가져오기
    const sel = document.querySelector(".form-select");
    // 검색어 요소 가져오기
    const keyword = document.querySelector("[name='keyword']");
    
    // select 요소의 선택된 value 가져오기
    const selectedVal = sel.options[sel.selectedIndex].value;
    // console.log(selectedVal);
    // console.log(sel.value);
    
    //    검색기준선택 => writer, title 둘 중의 하나 있게
    if(sel.value === "검색기준선택"){
		alert("검색기준을 확인해 주세요")
		return;
	} else if(keyword.value === ""){
		alert("검색어를 확인해 주세요");
		keyword.select();
		return;
	}
	form.submit();
    // 2) 검색어가 입력되었는지 확인
    //    ==> 둘 다 입력이 된 경우에만 submit
})