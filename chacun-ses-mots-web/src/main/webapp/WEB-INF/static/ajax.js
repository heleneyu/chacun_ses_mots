//class Ajax {
//    constructor(uri) {
//        this.uri = uri;
//    }
//
//    async get(path) {
//        const req = {
//            method: "GET",
//            mode: 'cors',
//            cache: 'default'
//        }
//        const newUri = this.uri + path;
//        const response = await fetch(newUri, req);
//        return await response.json();
//    }
//
//    async post(path, data) {
//        const req = {
//            method: "POST",
//            mode: 'cors',
//            cache: 'default',
//            body: JSON.stringify(data),
//            headers: {
//                "Content-type": "application/json; charset=UTF-8"
//            }
//        }
//        const newUri = this.uri + path;
//        const response = await fetch(newUri, req);
//        return await response.json();
//    }
//
//    delete(path) {
//        const req = {
//            method: "DELETE",
//        }
//        const newUri = this.uri + path;
//        return fetch(newUri, req);
//    }
//}
//
////const ajax = new Ajax('https://jsonplaceholder.typicode.com');
////const data = {
////    userId: 1,
////    title: "Par toutatis",
////    body: "Ils sont fous ces romains"
////}
//
//
////ajax.post('/posts', data).then(json => console.log(json));
////ajax.delete('/posts/1').then(response => response.json()).then(json => console.log(json));
