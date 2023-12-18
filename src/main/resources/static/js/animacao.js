window.addEventListener("scroll", function(){
    let header = document.querySelector('#navegacao')
    header.classList.toggle('rolagem',window.scrollY > 500)
})
function menuShow(){
    let menuMobile = document.querySelector('.mobile-menu');
    if(menuMobile.classList('open')){
        menuMobile.classList.remove('open');
    }
        else{
            menuMobile.classList.add('open')
        }
    }

