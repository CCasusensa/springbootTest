import router from './router';

router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.requireAuth)) { // 判斷該路由是否需要登入權限
        const token = localStorage.getItem('token');
        if (token) { // 如果當前token存在的話
            if (to.path !== '/login') { // 如果目標路由不是登入頁面的話
                next();
            }
        } else {
            next({
                path: '/login'
            });
        }
    } else {
        next();
    }
});