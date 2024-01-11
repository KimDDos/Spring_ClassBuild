console.log("boardModify 입장~!");

document.addEventListener('click', (e) => {
     console.log(e.target);
     if (e.target.classList.contains('fileDel')) {
          const uuid = e.target.dataset.uuid;
          console.log(uuid);
          deleteFileToServer(uuid).then(result => {
               if (result === '1') {
                    alert("첨부파일 삭제 성공~!");
                    e.target.closest('li').remove();
               }
          })
     }

})

async function deleteFileToServer(uuid) {
     try {
          const url = "/board/fileDelete/" + uuid;
          const config = {
               method: "delete"
          }

          const resp = await fetch(url, config);
          const result = await resp.text();
          return result;
     } catch (error) {
          console.log(error);
     }
}