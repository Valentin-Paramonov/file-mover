const JsonProvider = class {
    static getJson(url) {
        return new Promise((resolve, reject) => {
            let req = new XMLHttpRequest();
            req.onload = e => {
                resolve(JSON.parse(req.response));
            };
            req.open('GET', url);
            req.send();
        });
    }
};
