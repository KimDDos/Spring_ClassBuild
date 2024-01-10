document.getElementById('cmtPostBtn').addEventListener('click', () => {
     const cmtText = document.getElementById('cmtText');
     if (cmtText.value == null || cmtText.value == '') {
          alert('댓글을 입력해주세요.');
          cmtText.focus();
          return false;
     } else {
          let cmtData = {
               bno: bnoVal,
               writer: document.getElementById('cmtWriter').innerText,
               content: cmtText.value
          }
          console.log(cmtData);

          postCommentToServer(cmtData).then(result => {
               console.log(result);
               if (result == '1') {
                    alert("댓글 등록 성공~!");
                    cmtText.value = '';
               }
               // 화면에 뿌리기
               getCommentList(bnoVal);
          });
     }
})

async function postCommentToServer(cmtData) {
     try {
          const url = "/comment/post";
          const config = {
               method: 'post',
               headers: {
                    'content-type': 'application/json; charset=utf-8'
               },
               body: JSON.stringify(cmtData)
          };

          const resp = await fetch(url, config);
          const result = await resp.text();
          return result;
     } catch (erroe) {
          console.log(error);
     }
};

async function getCommentListFromServer(bnoVal, page) {
     try {
          const resp = await fetch("/comment/" + bnoVal + "/" + page);
          const result = await resp.json();
          return result;
     } catch (error) {
          console.log(error);
     };
};

function getCommentList(bnoVal, page = 1) {
     getCommentListFromServer(bnoVal, page).then(result => {
          console.log(result);
          if (result.cmtList.length > 0) {
               const ul = document.getElementById('CommentZone');
               // 댓글을 다시 뿌릴때 기존 값을 삭제x 1page 일 경우에만 삭제
               if (page == 1) {
                    ul.innerHTML = '';
               }
               for (let cvo of result.cmtList) {
                    let add = `<li class="list-group-item" data-cno="${cvo.cno}" data-writer="${cvo.writer}">`;
                    add += `<div class="mb-3">`;
                    add += `<div class="fw-bold">${cvo.writer}  <span class="badge rounded-pill text-bg-success">${cvo.regDate}</span></div>`;
                    add += `${cvo.content}`;
                    add += `</div>`;
                    add += `<button type="button" data-cno="${cvo.cno}" class="btn btn-sm btn-outline-warning cmtModify" data-bs-toggle="modal" data-bs-target="#myModal">Modify</button>`;
                    add += `<button type="button" data-cno="${cvo.cno}" class="btn btn-sm btn-outline-danger cmtRemove">Remove</button>`
                    add += `</li>`;
                    ul.innerHTML += add;
               };
               // 더 보기 버튼 코드
               let moreBtn = document.getElementById('moreBtn');
               console.log(moreBtn);
               // moreBtn 표시 조건
               if (result.pgvo.pageNo < result.endPage) {
                    moreBtn.style.visibility = "visible"; // 버튼 표시
                    moreBtn.dataset.page = page + 1;
               } else {
                    moreBtn.style.visibility = "hidden";
               }
          } else {
               let li = `<li class="list-group-item">Comment List Empty</li>`;
               ul.innerHTML = li;
          }
     });
};

document.addEventListener('click', (e) => {
     console.log(e.target);
     if (e.target.id == 'moreBtn') {
          let page = parseInt(e.target.dataset.page);
          getCommentList(bnoVal, page);
     } else if (e.target.classList.contains('cmtModify')) {
          let li = e.target.closest('li');

          // nextSibling : 한 부모안에서 같은(다음) 형제를 찾음
          let cmtText = li.querySelector('.fw-bold').nextSibling;
          console.log(cmtText);
          // 모달창에 기존 댓글 내용을 반영 (수정하기 편하니까!)
          document.getElementById('cmtTextMod').value = cmtText.nodeValue;

          // 수정 => cno, writer, content 
          document.getElementById('cmtModBtn').setAttribute("data-cno", li.dataset.cno);
          document.getElementById('cmtModBtn').setAttribute("data-writer", li.dataset.writer);
     } else if (e.target.id == 'cmtModBtn') {
          let cmtDataMod = {
               cno: e.target.dataset.cno,
               writer: e.target.dataset.writer,
               content: document.getElementById('cmtTextMod').value
          };
          console.log(cmtDataMod);
          editCommentToServer(cmtDataMod).then(result => {
               if (result == "1") { // 수정 성공
                    // 모달창 닫기
                    document.querySelector('.btn-close').click();
               } else {
                    alert("댓글 수정 실패");
                    document.querySelector('.btn-close').click();
               }
               // 수정된 댓글 뿌리기  page=1
               getCommentList(bnoVal);
          })
     } else if (e.target.classList.contains('cmtRemove')) {
          let li = e.target.closest('li');
          let cno = li.dataset.cno;
          let writer = li.dataset.writer;

          deleteCommentToServer(cno, writer).then(result => {
               console.log(result);
               if (result == 1) {
                    alert("댓글 삭제 성공~!");
                    getCommentList(bnoVal);
               }
          })


     }
})

async function editCommentToServer(cmtDataMod) {
     try {
          const url = "/comment/edit";
          const config = {
               method: 'put',
               headers: {
                    "content-type": "application/json; charset=utf-8"
               },
               body: JSON.stringify(cmtDataMod)
          }
          const resp = await fetch(url, config);
          const result = await resp.text();
          return result;
     } catch (error) {
          console.log(error);
     }
}

async function deleteCommentToServer(cno, writer) {
     try {
          const url = "/comment/del/" + cno + "/" + writer;
          const config = {
               method: 'delete',
          }
          const resp = await fetch(url, config);
          const result = await resp.text();
          return result;
     } catch (error) {
          console.log(error);
     }
}