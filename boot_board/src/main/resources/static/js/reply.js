/**
 * 댓글 처리 자바스크립트 모듈
 */

// read.js 의 replyService.add({ bno: 4124, reply: "댓글 작성", replyer: "test2" }); 넘어옴
// 넘어온 read.js가 function add(reply)에 담김 (reply라는 변수)
let replyService = (function () {
  // reply : 댓글 작성 자바스크립트 객체
  // callback : function

  function add(reply, callback) {
    console.log("add 함수");

    fetch("/replies/new", {
      method: "post",
      headers: {
        "content-type": "application/json",
        "X-CSRF-TOKEN": csrfToken,
      },
      body: JSON.stringify(reply),
    })
      .then((Response) => {
        // replyController 의 "success"(200번)가 Response 에 담기게 됨 만약 입력 오류가 난다면 .catch를 호출함, 입력오류가 없다면 Response.text() 리턴되어 다음 then으로 넘어감
        if (!Response.ok) {
          // 결과가 도착하게 되면 자동 호출(비동기호출)
          throw new Error("입력 오류");
        }
        return Response.text(); // success
      })
      .then((data) => {
        // 처음 then()이 return을 하면 호출됨
        // 넘겨받은 함수를 호출하게 됨
        if (callback) {
          callback(data);
        }
      })
      .catch((error) => console.log(error));
  } // add 종료

  function getList(param, callback) {
    let bno = param.bno;
    let page = param.page;

    fetch("/replies/pages/" + bno + "/" + page)
      .then((response) => {
        if (!response.ok) {
          throw new Error("리스트 없음");
        }
        return response.json();
      })
      .then((data) => {
        console.log("리스트와 개수");
        console.log(data);
        //data 가 도착해서 함수가 호출되면 넘겨받은 함수 호출
        if (callback) {
          callback(data.replyCnt, data.list);
        }
      })
      .catch((error) => console.log(error));
  } // getList 종료

  function displayTime(timeVal) {
    const today = new Date(); // 오늘날짜

    // 오늘 날짜 - 댓글작성날짜
    let gap = today.getTime() - timeVal; // 오늘날짜 - 작성날짜 => 밀리세컨 단위로 나옴

    // 댓글작성날짜 Date 객체 생성
    let dateObj = new Date(timeVal);

    let str = "";
    // 작성날짜를 보여줄 때 24시간 안에 작성했느냐? 넘었느냐?
    // 24시간 안이라면 시분초, 넘었다면 년/월/일 형태로 보여주기
    // 1000 * 60 * 60 * 24 : 하루 24시간을 밀리세컨 단위로 만들기
    if (gap < 1000 * 60 * 60 * 24) {
      let hh = dateObj.getHours(); // 1~9시 10~12시
      let mi = dateObj.getMinutes();
      let ss = dateObj.getSeconds();

      return [
        (hh > 9 ? "" : "0") + hh, // 시간이 두자리면 그냥 넘어가고 한자리면 앞자리에 "0" 붙이기
        ":",
        (mi > 9 ? "" : "0") + mi,
        ":",
        (ss > 9 ? "" : "0") + ss,
      ].join("");
    } else {
      const yy = dateObj.getFullYear();
      const mm = dateObj.getMonth() + 1; //월은 0부터 시작이기때문에 +1
      const dd = dateObj.getDate();
      return [
        yy,
        "/",
        (mm > 9 ? "" : "0") + mm, // 월이 두자리면 그냥 넘어가고 한자리면 앞자리에 "0" 붙이기
        "/",
        (dd > 9 ? "" : "0") + dd,
      ].join("");
    }
  } // 댓글 시간넣기 종료

  function get(rno, callback) {
    fetch("/replies/" + rno)
      .then((response) => {
        if (!response.ok) {
          throw new Error("가져올 댓글 없음");
        }
        return response.json();
      })
      .then((data) => {
        if (callback) {
          callback(data);
        }
      })
      .catch((error) => console.log(error));
  } // 댓글 하나 불러오기 종료

  function update(reply, callback) {
    fetch("/replies/" + reply.rno, {
      method: "put",
      headers: {
        "content-type": "application/json",
        "X-CSRF-TOKEN": csrfToken,
      },
      body: JSON.stringify(reply),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("수정 실패");
        }
        return response.text();
      })
      .then((data) => {
        if (callback) {
          //data 도착하면 콜백 호출해줘
          callback(data);
        }
      })
      .catch((error) => console.log(error));
  } // 댓글 수정 종료

  function remove(rno, replyer, callback) {
    const reply = { replyer: replyer };
    fetch("/replies/" + rno, {
      method: "delete",
      headers: {
        "X-CSRF-TOKEN": csrfToken,
        "content-type": "application/json",
      },
      body: JSON.stringify(reply),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("삭제 불가");
        }
        return response.text();
      })
      .then((data) => {
        if (callback) {
          callback(data);
        }
      })
      .catch((error) => console.log(error));
  } /// 댓글 삭제 종료

  // 외부에서 접근 가능한 함수 지정
  return {
    add: add,
    getList: getList,
    displayTime: displayTime,
    get: get,
    update: update,
    remove: remove,
  };
})();
