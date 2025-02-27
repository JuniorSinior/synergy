document.addEventListener('DOMContentLoaded', function () {
    const submenuParents = document.querySelectorAll('.submenu-parent');

    submenuParents.forEach(parent => {
        parent.addEventListener('mouseenter', function () {
            const submenu = this.querySelector('.submenu');
            submenu.classList.add('active');
            submenu.style.display = 'block';
        });

        parent.addEventListener('mouseleave', function () {
            const submenu = this.querySelector('.submenu');
            submenu.classList.remove('active');
            submenu.style.display = 'none';//안내려가는거 해결!!!!!!
        });

        // 서브메뉴 항목 클릭 이벤트 처리
        const submenuLinks = parent.querySelectorAll('.submenu a');
        submenuLinks.forEach(link => {
            link.addEventListener('click', function (event) {
                // 기본 이벤트(링크 이동) 방지
                event.preventDefault();
                // 링크가 가리키는 페이지로 이동
                window.location.href = this.getAttribute('href');
            });
        });
    });
});
