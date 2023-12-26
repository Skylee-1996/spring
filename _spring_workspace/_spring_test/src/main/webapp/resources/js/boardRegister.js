document.getElementById('trigger').addEventListener('click',()=>{
    document.getElementById('file').click();
})

//정규표현식을 사용하여 파일 형식제한 함수 만들기
//실행파일 막기(exe,bat,sh,mis,dll,jar)
//파일 사이즈 체크(20M 사이즈보다 크면 막기)
//이미지 파일만 올리기(jpg,jpeg,gif,png,bmp)

const regExp=new RegExp("\.(exe|sh|bat|mis|dll|jar)$"); //실행파일 패턴 $끝처리
const regExpImg = new RegExp("\.(jpeg|jpg|gif|png|bmp)$"); //이미지 파일 패턴
const maxSize = 1024*1024*20; //20mb 사이즈로 설정

//Validation : 규칙설정
//return 0 / 1 로 리턴
function fileValidation(name, fileSize){
    fileName = name.toLowerCase();
     if(regExp.test(fileName)){ //파일이름을 실행파일인지 확인
        return 0;
    }else if(fileSize>maxSize){
        return 0;
    }else if(!regExpImg.test(fileName)){
        return 0;
    }else{
        return 1;
    }
}


//첨부 파일에 따라 등록가능한지 체크 함수
document.addEventListener('change',(e)=>{
    console.log(e.target);
    if(e.target.id === 'file'){

        const fileObject = document.getElementById('file').files; //여러개의 파일이 배열로 들어옴
        console.log(fileObject);
        //한번 true가 되면 다시 false로 돌아올 수 없음. 버튼 활성화
        document.getElementById('regBtn').disabled = false; //버튼 비활성화


        let div= document.getElementById('fileZone');
        div.innerHTML =''; //기존 추가했던 파일삭제
        let ul = `<ul class="list-group">`;
        //fileValidation 함수의 리턴 여부를 체크
        //모든 파일이 1이여야 가능

         let isOk = 1;  //*로 isOk를 처리하여 모든파일이 1이여야 통과
          for(let file of fileObject){
            let validResult = fileValidation(file.name,file.size);
            isOk *= validResult;
            ul+= `<li class="list-group-item">`;
            //업로드 가능 여부 표시
            ul+= `<div>${validResult ? '업로드 가능' : '업로드 불가능'} </div>`;
            ul+= `${file.name}`;
            ul+=`<span class="badge rounded-pill text-bg-${validResult ? `success` : `danger`}">${file.size}bytes</span>`
            ul+=`</li>`;
          }
          ul+=`</ul>`;
          div.innerHTML = ul;
          if(isOk == 0){
            document.getElementById('regBtn').disabled = true; //버튼 비활성화
          }

    }


})

//fileZone > ul> li(파일 개수만큼 li가 추가되는 형식)