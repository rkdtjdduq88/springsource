/**
 *
 */
document.querySelector("#uploadFile").addEventListener("change", () => {
  // FormData 객체 생성
  const formData = new FormData();

  // file 요소 가져오기
  let inputFiles = document.querySelector("#uploadFile").files;
  console.log(inputFiles);

  // 가져온 file 요소를 formData 추가
  for (let i = 0; i < inputFiles.length; i++) {
    formData.append("uploadFile", inputFiles[i]);
  }

  // 비동기 - formData 전송
  fetch("/uploadAjax", {
    method: "post",
    body: formData,
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("파일 업로드 실패");
      }
      return response.json();
    })
    .then((data) => {
      console.log(data);
      showUploadedFile(data);
    })
    .catch((error) => console.log(error));
});

function showUploadedFile(uploadResultArr) {
  // 도착한 데이터를(파일 업로드 정보) 에서 파일 이름을 li 태그로 만들어서 보여주기
  let str = "";
  uploadResultArr.forEach((item) => {
    // fileType 이 true 라면 image 파일이라면 썸네일 이미지 보여주기
    if (item.fileType) {
      // 썸네일 이미지 경로 생성
      let fileCallPath = encodeURIComponent(
        item.uploadPath + "\\s_" + item.uuid + "_" + item.fileName
      );
      //str += "<li><img src='/display?fileName=" + fileCallPath + "'></li>";

      // 썸네일 이미지 클릭 ==> 원본 이미지 보여주기
      let orifileCallPath = encodeURIComponent(
        item.uploadPath + "\\" + item.uuid + "_" + item.fileName
      );

      str += "<li data-path='" + item.uploadPath + "' data-uuid='" + item.uuid + "' ";
      str += "data-filename='" + item.fileName + "'data-type='" + item.fileType + "'>";
      str += "<a href='/display?fileName=" + orifileCallPath + "' data-lightbox='image'>";
      str +=
        "<div class='text-center'><img src='/display?fileName=" + fileCallPath + "'></a></div>";
      str += "<small>" + item.fileName + "</small> ";
      str +=
        "<button type='button' class='btn btn-sm btn-circle btn-warning' data-file='" +
        fileCallPath +
        "' data-type='image'> X </button>";
      str += "</li>";
    } else {
      // txt 파일 경로 생성
      let fileCallPath = encodeURIComponent(
        item.uploadPath + "\\" + item.uuid + "_" + item.fileName
      );

      str += "<li data-path='" + item.uploadPath + "' data-uuid='" + item.uuid + "' ";
      str += "data-filename='" + item.fileName + "'data-type='" + item.fileType + "'>";
      str += "<a href='/download?fileName=" + fileCallPath + "'>";
      str += "<div class='text-center'><img src='/resources/img/txt-file.png'></div>";
      str += "<small>" + item.fileName + "</small></a> ";
      str +=
        "<button type='button' class='btn btn-sm btn-circle btn-warning' data-file='" +
        fileCallPath +
        "' data-type='file'> X </button>";
      str += "</li>";
    }
  });
  console.log("파일첨부 ", str);
  document.querySelector(".uploadResult ul").insertAdjacentHTML("beforeend", str);
}

// x 클릭 시 alert() 창 띄우기
document.querySelector(".uploadResult").addEventListener("click", (e) => {
  // 자식한테 이벤트가 일어나면 부모에게 전파 ==> 이벤트 전파
  // 실제 이벤트가 발생한 대상이 : 자식 ==> e.target
  // 이벤트를 감지한 부모 ==> e.currentTarget
  //  1) 화면에서 첨부 목록 정리
  //  2) 서버 폴더에 저장된 파일 정리
  //      이미지 : 원본, 썸네일 이미지 제거
  //      text : 파일 제거

  // data- 에 있는 값 가져오기
  // data-file , data-type
  const targetFile = e.target.dataset.file;
  const type = e.target.dataset.type;
  console.log(targetFile, type);

  // x 가 눌러진 li 가져오기
  const li = e.target.closest("li");

  // script 에서 <form> 태그 작성
  const formData = new FormData();
  formData.append("fileName", targetFile);
  formData.append("type", type);

  // /deleteFile?fileName=2023/05/20/test.jpg&type=image => 주소줄이 이렇게 나오고 싶다면 URLSearchParams 쓰면됨
  //const data = new URLSearchParams(formData);

  fetch("/deleteFile", {
    method: "post",
    body: formData,
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("파일 제거 실패");
      }
      return response.text();
    })
    .then((data) => {
      console.log(data);
      li.remove();
    })
    .catch((error) => console.log(error));
});