const active = document.querySelectorAll(".pageList a");

// 카테고리 클릭시 카테고리 색깔 변경
console.log(active);
let tmp = active[0];


tmp.style.backgroundColor = "#8E83A9";
tmp.style.color = "#ffffff";
for (let i = 0; i < active.length; i++) {
    active[i].addEventListener('click', function (e) {
        e.target.style.backgroundColor = "#8E83A9";
        e.target.style.color = "#ffffff";
        tmp.style.backgroundColor = "#f3f3f3";
        tmp.style.color = "#BAAAD7";

        tmp = e.target;
    });
}