document.getElementById('trigger').addEventListener('click', () => {
     document.getElementById('files').click();
})

// 실행파일, 이미지파일, 파일 최대 사이즈 정규표현식 작성
const regExp = new RegExp("\.(exe|sh|bat|mis|dll|jar|msi)$");
const maxSize = 1024 * 1024 * 20;

function fileValidation(fileName, fileSize) {
     if (regExp.test(fileName)) {
          return 0;
     } else if (fileSize > maxSize) {
          return 0;
     } else {
          return 1;
     }
}

document.addEventListener('change', (e) => {
     console.log(e.target);
     if (e.target.id === 'files') {
          // input file element 에 저장된 file의 정보를 가져오는 property
          const fileObject = document.getElementById('files').files;
          console.log(fileObject);

          // 한번 disabled 되면 혼자 풀어질 수 없음. 버튼을 원래 상태로 복구
          document.getElementById('regBtn').disabled = false;

          // 첨부파일에 대한 정보를 fileZone에 기록
          let div = document.getElementById('fileZone');
          div.innerHTML = '';
          // ul > li 값 추가
          // <ul class="list-group list-group-flush">
          // <li class="list-group-item">An item</li>

          // 여러 파일에 대한 검증을 모두 통과하기 위해서 각 파일마다 통과 여부를 확인
          let isOk = 1; // 전체 파일 검증 결과
          let ul = `<ul class="list-group list-group-flush">`;
          for (let file of fileObject) {
               // 개별 파일의 검증 결과
               let validResult = fileValidation(file.name, file.size);
               isOk *= validResult;
               ul += `<li class="list-group-item">An item</li>`;
               ul += `<div> ${file.name}`;
               ul += `${validResult ? ` 업로드 가능 ` : ` 업로드 불가능`} `
               ul += `<span class="badge rounded-pill text-bg-${validResult ? 'success' : 'danger'}">${file.size}Byte</span></div>`;
               ul += `</li>`;
          }
          ul += `</ul>`
          div.innerHTML = ul;

          if (isOk == 0) {
               // 하나의 파일이라도 검증에 통과하지 못한다면 버튼 비활성화
               document.getElementById('regBtn').disabled = true;
          }
     }

})



document.getElementById('logoutLinck').addEventListener('click', (e) => {
     e.preventDefault();
     document.getElementById('logoutForm').submit();
})