import axios from 'axios'
import Element from 'element-ui'
import router from './router'
import store from './store'

axios.defaults.baseURL = "http://localhost:8081"

// 前置攔截
axios.interceptors.request.use(config => {
    return config;
});


// 後置攔截
axios.interceptors.response.use(response => {
    let res = response.data;
    if (res.code === 200) {
        return response;
    } else {
        Element.Message({
            showClose: true,
            message: '發生未知的錯誤，請聯繫管理員。',
            type: 'error',
            duration: 3000
        });
        return Promise.reject(res.message);
    }
}, error => {
    console.log(error);
    if (error.response.data) {
        error.message = error.response.data.message;
    }
    if (error.response.status === 401) {
        store.commit("REMOVE_INFO");
        router.push("./login");
    }
    Element.Message({
        showClose: true,
        message: error.message,
        type: 'error',
        duration: 3000
    });
    return Promise.reject(error);
});