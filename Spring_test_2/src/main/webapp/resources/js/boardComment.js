console.log("boardComment JS 입성~!");

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
          const ul = document.getElementById('CommentZone');
          if (result.cmtlist.length > 0) {
               ul.innerHTML = '';
               for (let i = 0; i < result.cmtlist.length; i++) {
                    let add = `<li class="list-group-item">`;
                    add += `<div class="mb-3">`;
                    add += `<div class="fw-bold" data-cno="${result[i].cno}">${result[i].writer}  <span class="badge rounded-pill text-bg-success">${result[i].regDate}</span></div>`;
                    add += `${result[i].content}`;
                    add += `</div>`;
                    add += `<button type="button" data-cno="${result[i].cno}" class="btn btn-sm btn-outline-warning cmtModify">Modify</button>`;
                    add += `<button type="button" data-cno="${result[i].cno}" class="btn btn-sm btn-outline-danger cmtRemove">Remove</button>`
                    add += `</li>`;
                    ul.innerHTML += add;
               };

          } else {
               let li = `<li class="list-group-item">Comment List Empty</li>`;
          }
     });
};